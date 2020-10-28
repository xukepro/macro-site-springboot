package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.dto.GroupUserDetail;
import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.common.mongodb.service.GroupChatService;
import com.xuke.macrosite.dao.GroupDao;
import com.xuke.macrosite.entity.Group;
import com.xuke.macrosite.entity.GroupUser;
import com.xuke.macrosite.pojo.vo.AddGroupParams;
import com.xuke.macrosite.pojo.vo.UserGroupVO;
import com.xuke.macrosite.service.GroupService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by xuke on 2020/10/20
 */
@Service
@Component
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupDao groupDao;

    @Resource
    private GroupChatService groupChatService;

    @Override
    public List<GroupDetail> getGroupUserDetail() {
        return groupDao.getGroupUserDetail();
    }

    @Override
    public GroupDetail getGroupUserDetailByGid(Integer gid) {
        return groupDao.getGroupUserDetailByGid(gid);
    }

    @Override
    public List<UserGroupVO> getUserGroupVOByUid(Integer uid) {
        List<UserGroupVO> userGroupVOList = groupDao.getUserGroupVOByUid(uid);
        Pageable pageable = PageRequest.of(0, 10);

        /* 将userList转化为userMap, 方便前端直接调用 */
        for (UserGroupVO group : userGroupVOList) {
            System.out.println(group);
            HashMap<Integer, GroupUserDetail> map = new HashMap<>();
            List<GroupUserDetail> userList = group.getUserList();
            for (GroupUserDetail groupUserDetail : userList) {
                map.put(groupUserDetail.getUid(), groupUserDetail);
            }
            group.setUserMap(map);

            /* recentMessage */
            List<GroupChatDocument> groupChatDocumentList = groupChatService.findByGidOrderByCreatedAtDesc(group.getGid(), pageable);
            group.setRecentMessage(groupChatDocumentList);

            /* unReadMsgCount */
            Integer count = groupChatService.countByGidAndUidAndRead(group.getGid(), uid, false);
            group.setUnReadMsgCount(count);

            /* 设置这条数据最近修改时间 */
            List<GroupChatDocument> recentMessage = group.getRecentMessage();
            if (recentMessage.size() > 0 && recentMessage.get(0).getCreatedAt() > group.getUpdatedAt() * 1000) {
                group.setUpdatedAt(recentMessage.get(0).getCreatedAt());
            } else {
                group.setUpdatedAt(group.getUpdatedAt() * 1000);
            }
        }

        Collections.sort(userGroupVOList);

        return userGroupVOList;
    }

    @Override
    @Transactional
    public GroupDetail createGroup(AddGroupParams group) {
        /* 检测群名 */
        checkGroupName(group.getName());

        groupDao.createGroup(group);

        /* 将群的创建者加入群成员表中 */
        GroupUser groupUser = new GroupUser();
        groupUser.setGid(group.getId());
        groupUser.setUid(group.getUid());
        groupUser.setRemark(null);
        groupUser.setRank(1);
        addGroupUser(groupUser);

        return getGroupUserDetailByGid(group.getId());
    }

    @Override
    @Transactional
    public GroupDetail updateGroup(Group group) {
        /* 检测群名 */
        checkGroupName(group.getName());

        groupDao.updateGroup(group);
        return getGroupUserDetailByGid(group.getId());
    }

    @Override
    @Transactional
    public boolean deleteGroup(Integer gid) {
        /* 删除群组所有成员 */
        groupDao.deleteGroupUser(gid, null);

        /* 删除群组所有聊天记录 */
        groupChatService.deleteByGid(gid);

        /* 删除这个群信息 */
        return groupDao.deleteGroup(gid) > 0;
    }

    public void checkGroupName(String groupName) {
        Boolean groupNameExisted = groupDao.isGroupNameExisted(groupName);
        if (groupNameExisted) {
            throw new BusinessException(ResCode.BAD_REQUEST, "群名已存在");
        }
    }

    /*-----------------------------group-user------------------------------------------*/

    @Override
    @Transactional
    public GroupDetail addGroupUser(GroupUser groupUser) {
        Boolean groupUserExisted = groupDao.isGroupUserExisted(groupUser.getGid(), groupUser.getUid());
        if (groupUserExisted) {
            throw new BusinessException(ResCode.BAD_REQUEST, "用户已经在群中");
        }

        groupDao.addGroupUser(groupUser);
        return getGroupUserDetailByGid(groupUser.getGid());
    }

    @Override
    @Transactional
    public GroupDetail updateGroupUser(GroupUser groupUser) {
        groupDao.updateGroupUser(groupUser);
        return getGroupUserDetailByGid(groupUser.getGid());
    }

    @Override
    @Transactional
    public boolean deleteGroupUser(Integer gid, Integer uid) {
        return groupDao.deleteGroupUser(gid, uid) > 0;
    }
}
