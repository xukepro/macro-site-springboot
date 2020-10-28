package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.service.FriendChatService;
import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.vo.AddFriendParams;
import com.xuke.macrosite.pojo.vo.UpdateFriendParams;
import com.xuke.macrosite.pojo.vo.UserFriendVO;
import com.xuke.macrosite.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
@Api(tags = "FriendController")
@RestController
@RequestMapping("friend")
public class FriendController {
    @Resource
    private FriendService friendService;

    @Resource
    private FriendChatService friendChatService;

    @ApiOperation(value = "获得用户的朋友列表")
    @GetMapping("{uid}")
    public List<UserFriendVO> getFriends(@PathVariable("uid") Integer uid) {
        return friendService.getUserFriend(uid);
    }

    @ApiOperation(value = "添加一个朋友")
    @PostMapping
    public List<UserFriendVO> addFriend(@RequestBody @Validated AddFriendParams addFriendParams) {
        return friendService.addUserFriend(addFriendParams);
    }

    @ApiOperation(value = "修改好友备注")
    @PutMapping
    public boolean updateRemark(@RequestBody @Validated UpdateFriendParams updateFriendParams) {
        return friendService.updateFriendRemark(updateFriendParams);
    }

    @ApiOperation(value = "删除一个朋友")
    @DeleteMapping("{uid}/{fid}")
    public boolean deleteFriend(@PathVariable("uid") Integer uid, @PathVariable("fid") Integer fid) {
        return friendService.deleteFriend(uid, fid);
    }

    @ApiOperation(value = "将好友消息置为已读")
    @PutMapping("/chat/read")
    public Integer updateRead(@RequestBody @Validated UserFriend userFriend) {
        return friendChatService.updateRead(userFriend.getUid(), userFriend.getFid());
    }

    @ApiOperation(value = "获得更多聊天记录")
    @GetMapping("chat/more")
    public List<FriendChatDocument> getMoreChatMsg(@RequestParam("uid") Integer uid, @RequestParam("fid") Integer fid, @RequestParam("createAt") long createdAt) {
        Pageable pageable = PageRequest.of(0, 10);
        return friendChatService.getMoreChatMsg(uid, fid, createdAt, pageable);
    }
}
