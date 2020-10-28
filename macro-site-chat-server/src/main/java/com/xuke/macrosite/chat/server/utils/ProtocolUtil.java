package com.xuke.macrosite.chat.server.utils;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.protobuf.ChatContentResProto;
import com.xuke.macrosite.common.protobuf.ChatMsgResProto;

/**
 * Created by xuke on 2020/10/15
 */
public class ProtocolUtil {

    public static ChatMsgResProto.ChatMsgRes getChatMsgRes(ChatMsgVO chatMsgVO) {
        Integer rid;
        switch (chatMsgVO.getType()) {
            case ChatMsgType.FRIEND:
                rid = chatMsgVO.getChatContent().getRid();
                break;
            case ChatMsgType.GROUP:
                rid = chatMsgVO.getChatContent().getGid();
                break;
            default:
                /* 待拓展 */
                rid = 404;
        }

        ChatContentResProto.ChatContentRes chatContentRes = ChatContentResProto.ChatContentRes.newBuilder()
                .setRid(rid)
                .setType(chatMsgVO.getChatContent().getType())
                .setContent(chatMsgVO.getChatContent().getContent())
                .build();

        ChatMsgResProto.ChatMsgRes chatMsgRes = ChatMsgResProto.ChatMsgRes.newBuilder()
                .setUid(chatMsgVO.getUid())
                .setType(chatMsgVO.getType())
                .setChatContent(chatContentRes)
                .build();

        return chatMsgRes;
    }
}
