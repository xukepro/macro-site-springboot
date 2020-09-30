package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.dto.UserFriendDetail;

import java.util.List;

/**
 * Created by xuke on 2020/9/28
 */
public interface UserFriendDao {
    List<UserFriendDetail> getUserFriend(Integer uid);

    Integer updateRemark(Integer fid, String remark);

    UserFriend add(UserFriend friend);

    Integer delete(Integer uid, Integer fid);
}
