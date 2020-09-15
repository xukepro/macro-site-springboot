package com.xuke.macrosite.dao;

import com.xuke.macrosite.pojo.dto.UserDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
public interface UserDao {
    List<UserDetail> getMyUserInfo(@Param("uid") Integer uid);

    List<UserDetail> getAllUserInfo();
}
