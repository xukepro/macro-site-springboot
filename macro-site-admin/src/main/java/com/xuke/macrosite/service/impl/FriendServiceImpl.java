package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.service.FriendChatService;
import com.xuke.macrosite.dao.UserFriendDao;
import com.xuke.macrosite.entity.User;
import com.xuke.macrosite.pojo.vo.AddFriendParams;
import com.xuke.macrosite.pojo.vo.UpdateFriendParams;
import com.xuke.macrosite.pojo.vo.UserFriendVO;
import com.xuke.macrosite.service.FriendService;
import com.xuke.macrosite.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Resource
    private UserFriendDao userFriendDao;

    @Resource
    private FriendChatService friendChatService;

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public List<UserFriendVO> getUserFriend(Integer uid) {
        List<UserFriendVO> friendList = userFriendDao.getUserFriend(uid);
        Pageable pageable = PageRequest.of(0, 10);
        for (UserFriendVO friend : friendList) {
            /*获得最近的10条聊天记录*/
            List<FriendChatDocument> chatDocumentList = friendChatService.findByUidAndFidOrderByCreatedAtDesc(uid, friend.getFid(), pageable);
            friend.setRecentMessage(chatDocumentList);

            /*获得未读的数据个数*/
            Integer count = friendChatService.countByUidAndFidAndRead(uid, friend.getFid(), false);
            friend.setUnReadMsgCount(count);

            /*设置这条信息的最近修改时间*/
            List<FriendChatDocument> recentMessage = friend.getRecentMessage();
            if (recentMessage.size() > 0 && recentMessage.get(0).getCreatedAt() > friend.getUpdatedAt() * 1000) {
                friend.setUpdatedAt(recentMessage.get(0).getCreatedAt());
            } else {
                friend.setUpdatedAt(friend.getUpdatedAt() * 1000);
            }
        }
        Collections.sort(friendList);
        return friendList;
    }

    @Override
    @Transactional
    public List<UserFriendVO> addUserFriend(AddFriendParams addFriendParams) {
        check(addFriendParams.getUid(), addFriendParams.getFid());

        userFriendDao.add(addFriendParams);
        return getUserFriend(addFriendParams.getUid());
    }

    @Override
    @Transactional
    public boolean updateFriendRemark(UpdateFriendParams updateFriendParams) {
        check(updateFriendParams.getUid(), updateFriendParams.getFid());
        return userFriendDao.updateRemark(updateFriendParams) > 0;
    }

    @Override
    @Transactional
    public boolean deleteFriend(Integer uid, Integer fid) {
        /* 删除用户和好友聊天记录 */
        friendChatService.deleteByUidAndFid(uid, fid);
        friendChatService.deleteByUidAndFid(fid, uid);

        return userFriendDao.delete(uid, fid) > 0 && userFriendDao.delete(fid, uid) > 0;
    }

    public void check(Integer uid, Integer fid) {
        /* 检查uid与fid是否相同 */
        if (uid.equals(fid)) {
            throw new BusinessException(ResCode.BAD_REQUEST, "uid和fid不能相等");
        }
        /* 检查id是否存在 */
        checkId(uid, fid);
        /* 检查是否已经是好友 */
        checkFriend(uid, fid);
    }

    public void checkFriend(Integer uid, Integer fid) {
        Boolean friendExisted = userFriendDao.isFriendExisted(uid, fid);
        if (friendExisted) {
            throw new BusinessException(ResCode.BAD_REQUEST, "已经是好友");
        }
    }

    public void checkId(Integer uid, Integer fid) {
        User user = new User();
        user.setId(uid);
        if (!userService.checkIsExisted(user)) {
            throw new BusinessException(ResCode.BAD_REQUEST, "uid不存在");
        }
        User friend = new User();
        friend.setId(fid);
        if (!userService.checkIsExisted(friend)) {
            throw new BusinessException(ResCode.BAD_REQUEST, "fid不存在");
        }
    }
}
