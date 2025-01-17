package com.xuke.macrosite.service;

import com.xuke.macrosite.entity.User;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.vo.LoginParams;
import com.xuke.macrosite.pojo.vo.RegisterParams;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.pojo.vo.UpdateUserParams;

import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
public interface UserService {
    UserDetail getUserInfo(Integer uid);

    List<UserDetail> getAllUserInfo();

    LoginInfo login(LoginParams params);

    LoginInfo getLoginInfo(String username);

    User register(RegisterParams params);

    String sendAuthCode(String email);

    boolean verifyAuthCode(String email, String authCode);

    boolean checkIsExisted(User user);

    Integer addUser(User user);

    UserDetail update(UpdateUserParams params);
}
