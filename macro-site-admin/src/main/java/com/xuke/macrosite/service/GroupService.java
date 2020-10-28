package com.xuke.macrosite.service;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.entity.Group;
import com.xuke.macrosite.entity.GroupUser;
import com.xuke.macrosite.pojo.vo.AddGroupParams;
import com.xuke.macrosite.pojo.vo.UserGroupVO;

import java.util.List;

/**
 * Created by xuke on 2020/10/20
 */
public interface GroupService {
    List<GroupDetail> getGroupUserDetail();

    GroupDetail getGroupUserDetailByGid(Integer gid);

    List<UserGroupVO> getUserGroupVOByUid(Integer uid);

    GroupDetail createGroup(AddGroupParams group);

    GroupDetail updateGroup(Group group);

    boolean deleteGroup(Integer gid);

    GroupDetail addGroupUser(GroupUser groupUser);

    GroupDetail updateGroupUser(GroupUser groupUser);

    boolean deleteGroupUser(Integer gid, Integer uid);
}
