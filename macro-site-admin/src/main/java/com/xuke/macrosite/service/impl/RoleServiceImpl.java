package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.RoleDao;
import com.xuke.macrosite.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/18
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public Integer addUserRole(Integer uid, String role) {
        return roleDao.addUserRole(uid, roleDao.getRoleId(role).getId());
    }
}
