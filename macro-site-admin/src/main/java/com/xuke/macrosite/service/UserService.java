package com.xuke.macrosite.service;

import com.xuke.macrosite.pojo.dto.UserDetail;

import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
public interface UserService {
    List<UserDetail> getMyUserInfo(Integer uid);

    List<UserDetail> getAllUserInfo();
}
