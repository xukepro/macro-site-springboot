package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.Role;
import com.xuke.macrosite.entity.UserRole;

/**
 * Created by xuke on 2020/9/11
 */
public interface RoleDao {

    Integer addUserRole(Integer uid, Integer rid);

    Role getRoleId(String role);
}
