package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.vo.AddFriendParams;
import com.xuke.macrosite.pojo.vo.UpdateFriendParams;
import com.xuke.macrosite.pojo.vo.UserFriendVO;

import java.util.List;

/**
 * Created by xuke on 2020/9/28
 */
public interface UserFriendDao {

    List<UserFriendVO> getUserFriend(Integer uid);

    Integer add(AddFriendParams addFriendParams);

    Integer updateRemark(UpdateFriendParams updateFriendParams);

    Integer delete(Integer uid, Integer fid);

    Boolean isFriendExisted(Integer uid, Integer fid);
}
