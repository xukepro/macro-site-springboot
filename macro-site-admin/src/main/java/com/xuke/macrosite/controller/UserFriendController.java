package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.entity.UserFriend;
import com.xuke.macrosite.pojo.dto.UserFriendDetail;
import com.xuke.macrosite.service.UserFriendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
@RestController
@RequestMapping("friend")
public class UserFriendController {
    @Resource
    private UserFriendService friendService;

    @ApiOperation(value = "获得用户的朋友列表")
    @GetMapping("{uid}")
    public ResResult<List<UserFriendDetail>> getFriends(@PathVariable("uid") Integer uid) {
        return ResResult.success(friendService.getUserFriend(uid));
    }

    @ApiOperation(value = "添加一个朋友")
    @PostMapping
    public ResResult<UserFriend> addFriend(@RequestBody UserFriend friend) {
        return ResResult.success(friendService.addUserFriend(friend));
    }

    @ApiOperation(value = "修改好友备注")
    @PutMapping
    public ResResult<Boolean> updateRemark(@RequestParam("fid") Integer fid, @RequestParam("remark") String remark) {
        return ResResult.success(friendService.updateFriendRemark(fid, remark));
    }

    @ApiOperation(value = "删除一个朋友")
    @DeleteMapping("{uid}/{fid}")
    public ResResult<Boolean> deleteFriend(@PathVariable("uid") Integer uid, @PathVariable("fid") Integer fid) {
        return ResResult.success(friendService.deleteFriend(uid, fid));
    }
}
