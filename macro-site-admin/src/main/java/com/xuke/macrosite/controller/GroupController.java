package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.common.mongodb.service.GroupChatService;
import com.xuke.macrosite.entity.Group;
import com.xuke.macrosite.entity.GroupUser;
import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.vo.AddGroupParams;
import com.xuke.macrosite.pojo.vo.UserGroupVO;
import com.xuke.macrosite.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/10/20
 */
@RestController
@Slf4j
@RequestMapping("group")
@Api(tags = "GroupController", description = "聊天群")
public class GroupController {
    @Resource
    private GroupService groupService;

    @Resource
    private GroupChatService groupChatService;

    @ApiOperation(value = "获得所有群和群成员信息")
    @GetMapping
    public List<GroupDetail> getGroupUserDetail() {
        return groupService.getGroupUserDetail();
    }

    @ApiOperation(value = "获得一个群信息")
    @GetMapping("/{gid}")
    public GroupDetail getGroupUserDetailByGid(@PathVariable("gid") Integer gid) {
        return groupService.getGroupUserDetailByGid(gid);
    }

    @ApiOperation(value = "获得用户所有的群信息")
    @GetMapping("byUser/{uid}")
    public List<UserGroupVO> getUserGroupVOByUid(@PathVariable("uid") Integer uid) {
        return groupService.getUserGroupVOByUid(uid);
    }

    @ApiOperation(value = "创建群")
    @PostMapping
    public GroupDetail createGroup(@RequestBody @Validated AddGroupParams group) {
        return groupService.createGroup(group);
    }

    @ApiOperation(value = "修改群信息")
    @PutMapping
    public GroupDetail updateGroup(@RequestBody @Validated Group group) {
        return groupService.updateGroup(group);
    }

    @ApiOperation(value = "删除群")
    @DeleteMapping("{gid}")
    public boolean deleteGroup(@PathVariable("gid") Integer gid) {
        return groupService.deleteGroup(gid);
    }

    @ApiOperation(value = "添加群成员")
    @PostMapping("user")
    public GroupDetail addGroupUser(@RequestBody @Validated GroupUser groupUser) {
        return groupService.addGroupUser(groupUser);
    }

    @ApiOperation(value = "修改群成员信息")
    @PutMapping("user")
    public GroupDetail updateGroupUser(@RequestBody @Validated GroupUser groupUser) {
        return groupService.updateGroupUser(groupUser);
    }

    @ApiOperation(value = "删除群成员")
    @DeleteMapping("/{gid}/user/{uid}")
    public boolean deleteGroupUser(@PathVariable("gid") Integer gid, @PathVariable("uid") Integer uid) {
        return groupService.deleteGroupUser(gid, uid);
    }

    @ApiOperation(value = "将群消息置为已读")
    @PutMapping("/chat/read")
    public Integer updateRead(@RequestBody @Validated GroupUser groupUser) {
        return groupChatService.updateRead(groupUser.getGid(), groupUser.getUid());
    }

    @ApiOperation(value = "获得更多群聊记录")
    @GetMapping("chat/more")
    public List<GroupChatDocument> getMoreChatMsg(@RequestParam("gid") Integer gid, @RequestParam("createAt") long createdAt) {
        Pageable pageable = PageRequest.of(0, 10);
        return groupChatService.getMoreChatMsg(gid, createdAt, pageable);
    }
}
