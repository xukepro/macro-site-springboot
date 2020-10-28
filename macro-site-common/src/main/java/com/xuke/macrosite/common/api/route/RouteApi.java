package com.xuke.macrosite.common.api.route;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;

/**
 * Created by xuke on 2020/10/16
 */
public interface RouteApi {

    Object chat(ChatMsgVO chatMsgVO);

    Object offLine(OffLineVO offLineVO);

    Object login(LoginVO loginVO) throws Exception;

    Object friendAsk(ChatMsgVO chatMsgVO);

    Object friendAccept(ChatMsgVO chatMsgVO);

    Object groupAsk(ChatMsgVO chatMsgVO);

    Object groupAccept(ChatMsgVO chatMsgVO);
}