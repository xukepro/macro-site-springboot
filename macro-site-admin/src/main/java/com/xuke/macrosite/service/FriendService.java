package com.xuke.macrosite.service;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.vo.AddFriendParams;
import com.xuke.macrosite.pojo.vo.UpdateFriendParams;
import com.xuke.macrosite.pojo.vo.UserFriendVO;

import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
public interface FriendService {
    /*获得好友*/
    List<UserFriendVO> getUserFriend(Integer uid);

    /*添加好友*/
    List<UserFriendVO> addUserFriend(AddFriendParams friend);

    /*修改好友备注*/
    boolean updateFriendRemark(UpdateFriendParams updateFriendParams);

    /*删除好友*/
    boolean deleteFriend(Integer uid, Integer fid);
}
