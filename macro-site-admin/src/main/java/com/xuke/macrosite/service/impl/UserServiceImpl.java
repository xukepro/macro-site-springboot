package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.UserDao;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<UserDetail> getMyUserInfo(Integer uid) {
        return userDao.getMyUserInfo(uid);
    }

    @Override
    public List<UserDetail> getAllUserInfo() {
        return userDao.getAllUserInfo();
    }
}
