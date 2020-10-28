package com.xuke.macrosite.controller;

import com.xuke.macrosite.pojo.vo.UserFriendVO;
import com.xuke.macrosite.pojo.vo.UserGroupVO;
import com.xuke.macrosite.service.GroupService;
import com.xuke.macrosite.service.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xuke on 2020/10/14
 */
//@RestController
//@RequestMapping("chat")
//@Api(tags = "ChatController")
//public class ChatController {
//
//    @Resource
//    private FriendService friendService;
//
//    @Resource
//    private GroupService groupService;
//
//    @ApiOperation("获得所有好友与群组信息")
//    @GetMapping("info/{uid}")
//    public void getInfo(@PathVariable("uid") Integer uid) {
//        List<UserFriendVO> userFriendVOList = friendService.getUserFriend(uid);
//        List<UserGroupVO> userGroupVOList = groupService.getUserGroupVOByUid(uid);
//        LinkedList<Object> res = new LinkedList<>();
//        for (UserFriendVO userFriendVO : userFriendVOList) {
//            res.add(userFriendVO);
//        }
//        new PriorityQueue<Object>()
//    }
//}
