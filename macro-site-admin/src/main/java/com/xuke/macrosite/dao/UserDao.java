package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.User;
import com.xuke.macrosite.entity.UserRole;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.dto.UserAdmin;
import com.xuke.macrosite.pojo.dto.UserDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
public interface UserDao {
    List<UserDetail> getUserInfo(@Param("uid") Integer uid);

    List<UserDetail> getAllUserInfo();

    List<UserAdmin> getAllRoles(String username);

    List<LoginInfo> getLoginInfo(String username);

    User checkIsExisted(User user);

    Integer addUserSelective(User user);

    Integer updateByPrimaryKeySelective(User user);
}
