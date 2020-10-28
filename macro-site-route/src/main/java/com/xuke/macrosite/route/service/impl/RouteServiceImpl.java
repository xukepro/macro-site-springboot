package com.xuke.macrosite.route.service.impl;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.api.chat.ServerApi;
import com.xuke.macrosite.common.api.route.RouteInfo;
import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.common.mongodb.service.FriendChatService;
import com.xuke.macrosite.common.mongodb.service.GroupChatService;
import com.xuke.macrosite.common.proxy.ProxyManager;
import com.xuke.macrosite.common.service.DubboService;
import com.xuke.macrosite.route.config.AppConfiguration;
import com.xuke.macrosite.route.service.RouteService;
import com.xuke.macrosite.route.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuke on 2020/10/18
 */
@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    @Resource
    private AppConfiguration app;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private OkHttpClient okHttpClient;

    @Reference
    private FriendChatService friendChatService;

    @Reference
    private GroupChatService groupChatService;

    @Reference
    private DubboService dubboService;

    @Override
    public RouteInfo getUserRoute(Integer fid) {
        return (RouteInfo) redisUtil.get(app.getRoutePrefix() + fid);
    }

    @Override
    public HashMap<Integer, RouteInfo> getGroupRoute(Integer gid) {
        List<Object> uidList = redisUtil.lGet(app.getGroupUserPrefix() + gid, 0, -1);
        HashMap<Integer, RouteInfo> userRouteMap = new HashMap<>();
        for (Object uid : uidList) {
            if (uid instanceof Integer) {
                Integer id = (Integer)uid;
                userRouteMap.put(id, getUserRoute(id));
            }
        }
        return userRouteMap;
    }

    @Override
    public void pushChatMessage(ChatMsgVO chatMsgVO, RouteInfo routeInfo) {
        String url = "http://" + routeInfo.getIp() + ":" + routeInfo.getHttpPort();
        log.info("向[{}]发送请求[{}]", url, chatMsgVO);

        ServerApi serverApi = new ProxyManager<>(ServerApi.class, url, okHttpClient).getInstance();
        Response response = null;
        try {
            response = (Response) serverApi.chat(chatMsgVO);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.body().close();
        }
    }

    @Override
    public void saveFriendChatMsg(ChatMsgVO chatMsgVO, RouteInfo friendRoute) {
        FriendChatDocument friendChatDocument = FriendChatDocument.builder()
                .uid(chatMsgVO.getUid())
                .fid(chatMsgVO.getChatContent().getRid())
                .type(chatMsgVO.getChatContent().getType())
                .content(chatMsgVO.getChatContent().getContent())
                .read(friendRoute != null)
                .createdAt(chatMsgVO.getSendTime().getTime())
                .build();

        friendChatService.save(friendChatDocument);
    }

    @Override
    public void saveGroupChatMsg(ChatMsgVO chatMsgVO, HashMap<Integer, RouteInfo> userRouteMap) {
        HashMap<Integer, Boolean> userReadMap = new HashMap<>();

        /* 将用户是否在线保存在userReadMap中 */
        for (Map.Entry<Integer, RouteInfo> infoEntry : userRouteMap.entrySet()) {
            userReadMap.put(infoEntry.getKey(), infoEntry.getValue() != null);
        }

        GroupChatDocument groupChatDocument = GroupChatDocument.builder()
                .uid(chatMsgVO.getUid())
                .gid(chatMsgVO.getChatContent().getGid())
                .type(chatMsgVO.getChatContent().getType())
                .content(chatMsgVO.getChatContent().getContent())
                .userReadMap(userReadMap)
                .createdAt(chatMsgVO.getSendTime().getTime())
                .build();

        groupChatService.save(groupChatDocument);
    }

    @Override
    public void saveFriendAskMsg(ChatMsgVO chatMsgVO, RouteInfo friendRoute) {

    }

    @Override
    public void markFriendAskMsg(ChatMsgVO chatMsgVO, RouteInfo friendRoute) {

    }

    @Override
    public Integer getGroupLeaderId(Integer gid) {
        GroupDetail groupDetail = dubboService.getGroupUserDetailByGid(gid);
        return groupDetail.getUid();
    }

    @Override
    public void saveGroupAskMsg(ChatMsgVO chatMsgVO, RouteInfo groupLeaderRoute) {

    }

    @Override
    public void markGroupAskMsg(ChatMsgVO chatMsgVO, RouteInfo receiverRoute) {

    }
}
