package com.xuke.macrosite.chat.client.ws;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.xuke.macrosite.chat.client.service.ReConnectManager;
import com.xuke.macrosite.chat.client.utils.SpringUtil;
import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.protobuf.ChatMsgReqProto;
import com.xuke.macrosite.common.protobuf.ChatMsgResProto;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/1
 */
@ChannelHandler.Sharable
@Slf4j
public class WSClientHandler extends SimpleChannelInboundHandler<ChatMsgResProto.ChatMsgRes> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("连接成功");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("断开连接, 重新连接");
        ReConnectManager reConnectManager = SpringUtil.getBean(ReConnectManager.class);
        reConnectManager.reConnect(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                ChatMsgReqProto.ChatMsgReq heartBeat = SpringUtil.getBean("heartBeat", ChatMsgReqProto.ChatMsgReq.class);
//                log.info("读空闲，发送心跳");
                ctx.writeAndFlush(heartBeat);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ChatMsgResProto.ChatMsgRes chatMsg) throws Exception {
//        System.out.println("收到消息" + chatMsg);

        int type = chatMsg.getType();
        switch (type) {
            case ChatMsgType.FRIEND:
                System.out.printf("好友[%s]: %s\n", chatMsg.getUid(), chatMsg.getChatContent().getContent());
                break;
            case ChatMsgType.GROUP:
                System.out.printf("群[%s]用户[%s]: %s\n", chatMsg.getChatContent().getRid(), chatMsg.getUid(), chatMsg.getChatContent().getContent());
                break;
            default:
                break;
        }

        System.out.println("> ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace() ;
        ctx.close() ;
    }
}
