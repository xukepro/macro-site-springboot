package com.xuke.macrosite.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.xuke.macrosite.dao.UserFriendDao;
import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.mongodb.repository.FriendChatRepository;
import com.xuke.macrosite.pojo.dto.UserFriendDetail;
import com.xuke.macrosite.service.UserFriendService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
@Service
public class UserFriendServiceImpl implements UserFriendService {
    @Resource
    private UserFriendDao userFriendDao;
    @Resource
    private FriendChatRepository friendChatRepository;

    @Override
    @Transactional
    public List<UserFriendDetail> getUserFriend(Integer uid) {
        List<UserFriendDetail> friendList = userFriendDao.getUserFriend(uid);
        Pageable pageable = PageRequest.of(0, 10);
        for (UserFriendDetail friend : friendList) {
            /*获得最近的10条聊天记录*/
            List<FriendChatDocument> chatDocumentList = friendChatRepository.findByUidAndFidOrderByCreatedAtDesc(uid, friend.getFid(), pageable);
            friend.setRecentMessage(chatDocumentList);

            /*获得未读的数据个数*/
            Integer count = friendChatRepository.countByUidAndFidAndState(uid, friend.getFid(), 0);
            friend.setUnReadMsgCount(count);
        }
        return friendList;
    }

    @Override
    public boolean updateFriendRemark(Integer fid, String remark) {
        return userFriendDao.updateRemark(fid, remark) > 0;
    }

    @Override
    public UserFriend addUserFriend(UserFriend friend) {
        userFriendDao.add(friend);
        return null;
    }

    @Override
    public boolean deleteFriend(Integer uid, Integer fid) {
        return userFriendDao.delete(uid, fid) > 0;
    }
}
