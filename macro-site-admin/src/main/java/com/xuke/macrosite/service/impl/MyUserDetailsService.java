package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.UserDao;
import com.xuke.macrosite.pojo.dto.UserAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xuke on 2020/5/26
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 查询用户
        List<UserAdmin> list = userDao.getAllRoles(username);
        System.out.println(list);
        UserAdmin user = list.get(0);
        if (user == null) {
            //这里找不到必须抛异常
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }
        LOGGER.info(user.toString());

        // 2. 设置角色
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoles().stream().forEach(
                r -> {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r.getRole());
                    grantedAuthorities.add(grantedAuthority);
                    System.out.println();
                });
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
//        grantedAuthorities.add(grantedAuthority);

        return new org.springframework.security.core.userdetails.User(username,
                user.getPassword(), grantedAuthorities);
    }
}
