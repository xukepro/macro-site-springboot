package com.xuke.macrosite.dao;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.entity.Group;
import com.xuke.macrosite.entity.GroupUser;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.vo.AddGroupParams;
import com.xuke.macrosite.pojo.vo.UserGroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuke on 2020/10/20
 */
public interface GroupDao {

    List<GroupDetail> getGroupUserDetail();

    GroupDetail getGroupUserDetailByGid(Integer gid);

    List<UserGroupVO> getUserGroupVOByUid(Integer uid);

    Integer createGroup(AddGroupParams group);

    Integer updateGroup(Group group);

    Integer deleteGroup(Integer gid);

    Integer addGroupUser(GroupUser groupUser);

    Integer updateGroupUser(GroupUser groupUser);

    Integer deleteGroupUser(@Param("gid") Integer gid, @Param("uid") Integer uid);

    Boolean isGroupNameExisted(String groupName);

    Boolean isGroupUserExisted(Integer gid, Integer uid);
}
