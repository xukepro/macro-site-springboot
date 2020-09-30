package com.xuke.macrosite.service;

import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.dto.UserFriendDetail;

import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
public interface UserFriendService {
    /*获得好友*/
    List<UserFriendDetail> getUserFriend(Integer uid);

    /*修改好友备注*/
    boolean updateFriendRemark(Integer fid, String remark);

    /*添加好友*/
    UserFriend addUserFriend(UserFriend friend);

    /*删除好友*/
    boolean deleteFriend(Integer uid, Integer fid);

}
