package com.xuke.macrosite.chat.server.controller;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.api.chat.ServerApi;
import com.xuke.macrosite.chat.server.ws.WSServer;
import com.xuke.macrosite.common.constant.ChatMsgType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/15
 */
@RestController
public class ChatController implements ServerApi {

    @Resource
    private WSServer wsServer;

    @Override
    @PostMapping("chat")
    public Object chat(@RequestBody ChatMsgVO chatMsgVO) {
        switch (chatMsgVO.getType()) {
            case ChatMsgType.FRIEND:
            case ChatMsgType.GROUP:
                wsServer.sendMsg(chatMsgVO);
                break;
            default:
        }

        return true;
    }
}
