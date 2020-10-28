package com.xuke.macrosite.route.service;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.api.route.RouteInfo;

import java.util.HashMap;

/**
 * Created by xuke on 2020/10/18
 */
public interface RouteService {

    RouteInfo getUserRoute(Integer fid);

    HashMap<Integer, RouteInfo> getGroupRoute(Integer gid);

    void pushChatMessage(ChatMsgVO chatMsgVO, RouteInfo routeInfo);

    void saveFriendChatMsg(ChatMsgVO chatMsgVO, RouteInfo friendRoute);

    void saveGroupChatMsg(ChatMsgVO chatMsgVO, HashMap<Integer, RouteInfo> userRouteMap);

    void saveFriendAskMsg(ChatMsgVO chatMsgVO, RouteInfo friendRoute);

    void markFriendAskMsg(ChatMsgVO chatMsgVO, RouteInfo friendRoute);

    Integer getGroupLeaderId(Integer gid);

    void saveGroupAskMsg(ChatMsgVO chatMsgVO, RouteInfo groupLeaderRoute);

    void markGroupAskMsg(ChatMsgVO chatMsgVO, RouteInfo receiverRoute);
}
