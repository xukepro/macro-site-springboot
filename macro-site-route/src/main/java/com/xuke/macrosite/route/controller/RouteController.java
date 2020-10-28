package com.xuke.macrosite.route.controller;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.api.route.LoginVO;
import com.xuke.macrosite.common.api.route.OffLineVO;
import com.xuke.macrosite.common.api.route.RouteApi;
import com.xuke.macrosite.common.api.route.RouteInfo;
import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.common.route.algorithm.RouteHandle;
import com.xuke.macrosite.common.util.RouteInfoUtil;
import com.xuke.macrosite.route.cache.RouteCache;
import com.xuke.macrosite.route.config.AppConfiguration;
import com.xuke.macrosite.route.service.AccountService;
import com.xuke.macrosite.route.service.RouteService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuke on 2020/10/16
 */
@RestController
@Slf4j
@RequestMapping("route")
public class RouteController implements RouteApi {

    @Resource
    private RouteCache routeCache;

    @Resource
    private RouteHandle routeHandle;

    @Resource
    private RouteService routeService;

    @Resource
    private AccountService accountService;

    @Resource
    private AppConfiguration app;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("聊天")
    @PostMapping("chat")
    @Override // 方法名必须和路由名一直，否则反射会报错
    public Boolean chat(@RequestBody @Validated ChatMsgVO chatMsgVO) {
        Integer type = chatMsgVO.getType();
        Integer senderId = chatMsgVO.getUid();
        log.info("收到聊天消息" + chatMsgVO);

        if (type == ChatMsgType.FRIEND) {
            Integer receiverId = chatMsgVO.getChatContent().getRid();
            log.info("用户[{}]对用户[{}]说: {}", senderId, receiverId, chatMsgVO.getChatContent().getContent());
            /* 获取接收消息用户所在路由信息 */
            RouteInfo friendRoute = routeService.getUserRoute(receiverId);

            // 保存到mongodb中
            routeService.saveFriendChatMsg(chatMsgVO, friendRoute);

            if (friendRoute != null) {
                log.info("用户[{}]在线, 发送到[{}:{}]", receiverId, friendRoute.getIp(), friendRoute.getHttpPort());
                routeService.pushChatMessage(chatMsgVO, friendRoute);
            } else {
                log.info("用户[{}]不在线", receiverId);
            }
        } else if (type == ChatMsgType.GROUP) {
            Integer groupId = chatMsgVO.getChatContent().getGid();
            log.info("用户[{}]在群[{}]中说: {}", senderId, groupId, chatMsgVO.getChatContent().getContent());
            /* 获得群所有成员路由列表 */
            HashMap<Integer, RouteInfo> userRouteMap = routeService.getGroupRoute(groupId);

            // 保存到mongodb中
            routeService.saveGroupChatMsg(chatMsgVO, userRouteMap);

            /* 分别向每个服务器发送信息 */
            for (Map.Entry<Integer, RouteInfo> infoEntry : userRouteMap.entrySet()) {
                Integer receiverId = infoEntry.getKey();
                /* 不需要发送给自己 */
                if (receiverId.equals(chatMsgVO.getUid())) {
                    continue;
                }
                RouteInfo routeInfo = infoEntry.getValue();
                if (routeInfo != null) {
                    log.info("用户[{}]在线，发送到[{}:{}]", receiverId, routeInfo.getIp(), routeInfo.getHttpPort());
                    chatMsgVO.getChatContent().setRid(receiverId); /* 设置fid用于服务器选择用户推送数据，只有gid无法实现 */
                    routeService.pushChatMessage(chatMsgVO, routeInfo);
                } else {
                    log.info("用户[{}]不在线", receiverId);
                }
            }
        }
        return true;
    }

    @ApiOperation("登录并选择一个服务器")
    @PostMapping("login")
    @Override
    public RouteInfo login(@RequestBody @Validated LoginVO loginVO) {
        List<String> chatServerList = routeCache.getServerList();
        String serverString = routeHandle.routeServer(chatServerList, String.valueOf(loginVO.getUid()));
        RouteInfo routeInfo = RouteInfoUtil.parse(serverString);

        /* 将用户和选择的服务器信息保存到redis中 */
        String key = app.getRoutePrefix() + loginVO.getUid();
        redisTemplate.opsForValue().set(key, routeInfo);

        log.info("用户[{}]上线，选择服务器[{}]", loginVO.getUid(), serverString);
        return routeInfo;
    }

    @ApiOperation("下线并删除与服务器的联系")
    @PostMapping("offLine")
    @Override
    public Boolean offLine(@RequestBody @Validated OffLineVO offLineVO) {
        return accountService.offLine(offLineVO);
    }

    @ApiOperation("申请加好友")
    @PostMapping("friendAsk")
    @Override
    public Object friendAsk(ChatMsgVO chatMsgVO) {
        if (chatMsgVO.getType().equals(ChatMsgType.FRIEND_ASK)) {
            throw new BusinessException(ResCode.TYPE_NOT_MATCH);
        }
        log.info("收到申请加好友消息" + chatMsgVO);
        Integer senderId = chatMsgVO.getUid();
        Integer receiverId = chatMsgVO.getChatContent().getRid();

        log.info("用户[{}]申请添加用户[{}]为好友: {}", senderId, receiverId, chatMsgVO.getChatContent().getContent());

        RouteInfo friendRoute = routeService.getUserRoute(receiverId);

        // 保存到mongodb中
        routeService.saveFriendAskMsg(chatMsgVO, friendRoute);

        if (friendRoute != null) {
            log.info("用户[{}]在线, 发送到[{}:{}]", receiverId, friendRoute.getIp(), friendRoute.getHttpPort());
            routeService.pushChatMessage(chatMsgVO, friendRoute);
        } else {
            log.info("用户[{}]不在线", receiverId);
        }
        return true;
    }

    @ApiOperation("同意加好友")
    @PostMapping("friendAccept")
    @Override
    public Object friendAccept(ChatMsgVO chatMsgVO) {
        if (chatMsgVO.getType().equals(ChatMsgType.FRIEND_ACCEPT)) {
            throw new BusinessException(ResCode.TYPE_NOT_MATCH);
        }
        log.info("收到同意加好友信息" + chatMsgVO);
        Integer senderId = chatMsgVO.getUid();
        Integer receiverId = chatMsgVO.getChatContent().getRid();

        log.info("用户[{}]同意添加用户[{}]为好友: {}", senderId, receiverId, chatMsgVO.getChatContent().getContent());

        RouteInfo friendRoute = routeService.getUserRoute(receiverId);

        // 保存到mongodb中
        routeService.markFriendAskMsg(chatMsgVO, friendRoute);

        if (friendRoute != null) {
            log.info("用户[{}]在线, 发送到[{}:{}]", receiverId, friendRoute.getIp(), friendRoute.getHttpPort());
            routeService.pushChatMessage(chatMsgVO, friendRoute);
        } else {
            log.info("用户[{}]不在线", receiverId);
        }
        return true;
    }

    @ApiOperation("申请入群")
    @PostMapping("groupAsk")
    @Override
    public Object groupAsk(ChatMsgVO chatMsgVO) {
        if (chatMsgVO.getType().equals(ChatMsgType.GROUP_ASK)) {
            throw new BusinessException(ResCode.TYPE_NOT_MATCH);
        }
        log.info("收到申请入群消息" + chatMsgVO);
        Integer senderId = chatMsgVO.getUid();
        Integer groupId = chatMsgVO.getChatContent().getGid();
        Integer leaderId = routeService.getGroupLeaderId(groupId);

        log.info("用户[{}]申请入群[{}], 群主[{}]: {}", senderId, groupId, leaderId, chatMsgVO.getChatContent().getContent());

        RouteInfo leaderRoute = routeService.getUserRoute(leaderId);

        /* 设置群主id, 用于服务器向群主推送消息， 以及群主上线时获取申请信息 */
        chatMsgVO.getChatContent().setRid(leaderId);
        // 保存到mongodb中
        routeService.saveGroupAskMsg(chatMsgVO, leaderRoute);

        if (leaderRoute != null) {
            log.info("群主[{}]在线, 发送到[{}:{}]", leaderId, leaderRoute.getIp(), leaderRoute.getHttpPort());
            routeService.pushChatMessage(chatMsgVO, leaderRoute);
        } else {
            log.info("群主[{}]不在线", leaderId);
        }
        return true;
    }

    @ApiOperation("同意入群")
    @PostMapping("groupAccept")
    @Override
    public Object groupAccept(ChatMsgVO chatMsgVO) {
        if (chatMsgVO.getType().equals(ChatMsgType.GROUP_ACCEPT)) {
            throw new BusinessException(ResCode.TYPE_NOT_MATCH);
        }
        log.info("收到同意入群消息" + chatMsgVO);
        Integer senderId = chatMsgVO.getUid();
        Integer groupId = chatMsgVO.getChatContent().getGid();
        Integer receiverId = chatMsgVO.getChatContent().getRid();

        log.info("群主[{}]同意用户[{}]入群[{}]: {}", senderId, receiverId, groupId, chatMsgVO.getChatContent().getContent());

        RouteInfo receiverRoute = routeService.getUserRoute(receiverId);

        // 保存到mongodb中
        routeService.markGroupAskMsg(chatMsgVO, receiverRoute);

        if (receiverRoute != null) {
            log.info("用户[{}]在线, 发送到[{}:{}]", receiverId, receiverRoute.getIp(), receiverRoute.getHttpPort());
            routeService.pushChatMessage(chatMsgVO, receiverRoute);
        } else {
            log.info("用户[{}]不在线", receiverId);
        }
        return true;
    }
}
