package com.xuke.macrosite.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.common.service.MailService;
import com.xuke.macrosite.common.service.RedisService;
import com.xuke.macrosite.dao.UserDao;
import com.xuke.macrosite.entity.User;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.vo.LoginParams;
import com.xuke.macrosite.pojo.vo.RegisterParams;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.pojo.vo.UpdateUserParams;
import com.xuke.macrosite.service.RoleService;
import com.xuke.macrosite.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.xuke.macrosite.security.utils.JwtTokenUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by xuke on 2020/9/15
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Resource
    private RoleService roleService;
    @Resource
    private MyUserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private RedisService redisService;
    @Resource
    private MailService mailService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final String ORDINARY_USER_ROLEE = "USER";

    @Override
    public UserDetail getUserInfo(Integer uid) {
        List<UserDetail> list = userDao.getUserInfo(uid);
        if (list.size() == 0) {
            return null;
        } else if (list.size() == 2) {
            LOGGER.error("getUserInfo" + "uid查出了多个用户");
        }
        return list.get(0);
    }

    @Override
    public List<UserDetail> getAllUserInfo() {
        return userDao.getAllUserInfo();
    }

    @Override
    @Transactional
    public ResResult login(LoginParams params) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(params.getUsername());
            if (!passwordEncoder.matches(params.getPassword(), userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }

        if (token == null) {
            return ResResult.validateFailed("用户名或密码错误");
        }
        LoginInfo loginInfo = getLoginInfo(params.getUsername());
        loginInfo.setToken(token);

        /*更新最近登录时间*/
        User user = new User();
        user.setId(loginInfo.getId());
        user.setLastLoginDate(new Date(System.currentTimeMillis()));
        userDao.updateByPrimaryKeySelective(user);

        return ResResult.success(loginInfo);
    }

    @Override
    public LoginInfo getLoginInfo(String username) {
        return userDao.getLoginInfo(username).get(0);
    }

    @Override
    @Transactional
    public ResResult register(RegisterParams params) {
        boolean result = verifyAuthCode(params.getEmail(), params.getAuthCode());
        if(!result) {
            return ResResult.badRequest("验证码错误");
        }

        User user = new User();
        user.setUsername(params.getUsername());
        //检查用户是否存在
        boolean isExisted = checkIsExisted(user);
        if (isExisted) {
            return ResResult.badRequest("用户已存在");
        }

        user.setPassword(passwordEncoder.encode(params.getPassword()));
        user.setUsername(params.getUsername());
        /*昵称默认为用户名*/
        user.setNickname(params.getUsername());
        user.setEmail(params.getEmail());
        addUser(user);

        /*首次注册为普通用户*/
        roleService.addUserRole(user.getId(), ORDINARY_USER_ROLEE);

        return ResResult.success("注册成功", user);
    }

    @Override
    @Transactional
    public ResResult sendAuthCode(String email) {
        //检查邮箱是否已绑定
        User user = new User();
        user.setEmail(email);
        boolean isExisted = checkIsExisted(user);
        if(isExisted){
            return ResResult.badRequest("邮箱已存在");
        }

        //生成验证码
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        String authCode = sb.toString();

        //发送邮件
        new Thread(() -> {
            try {
                LOGGER.error("1");
                mailService.sendMail(email, authCode);
                LOGGER.error("2");
                redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + email, authCode);
                redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + email, AUTH_CODE_EXPIRE_SECONDS);
            } catch (MessagingException e) {
                e.printStackTrace();
//                ResResult.failed(HttpCode.HTTP_INTERNAL_ERROR, "发送验证码失败");
                LOGGER.error("发送验证码失败");
            }
        }, "sendMail").start();

        return ResResult.success(null, "发送验证码成功");
    }

    /* 检查用户是否存在 */
    @Override
    public boolean checkIsExisted(User user) {
        return userDao.checkIsExisted(user) != null;
    }

    @Override
    public boolean verifyAuthCode(String email, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + email);
        return authCode.equals(realAuthCode);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUserSelective(user);
    }

    @Override
    @Transactional
    public UserDetail update(UpdateUserParams params) {
        User user = new User();
        BeanUtil.copyProperties(params, user);
        /* 修改头像 */
        if (params.getAvatarUrl() != null) {
            //
        }
        if (params.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(params.getPassword()));
        }
        userDao.updateByPrimaryKeySelective(user);
        return getUserInfo(params.getId());
    }
}
