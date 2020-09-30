package com.xuke.macrosite.ws;

import com.xuke.macrosite.common.protobuf.ChatContentProto;
import com.xuke.macrosite.common.protobuf.ChatMsgProto;
import com.xuke.macrosite.constant.ChatMsgType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;

@ChannelHandler.Sharable
@Slf4j
public class WSServerHandler extends SimpleChannelInboundHandler<ChatMsgProto.ChatMsg> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    /*读到客户端的内容*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMsgProto.ChatMsg msg) throws Exception {

        int type = msg.getType();
        int uid = msg.getUid();
        ChatContentProto.ChatContent chatContent = msg.getContent();

        switch (type) {
            case ChatMsgType.LOGIN: // 登录类型
                log.info("用户登录");
                userLogin(ctx, uid);
                break;
            case ChatMsgType.PING: // 心跳
//                log.info("客户端心跳");
                break;
            case ChatMsgType.FRIEND:
                log.info("朋友发消息");
                System.out.println(chatContent);
                int fid = chatContent.getReceiveId();
                Channel channel = WSSocketHolder.get(fid);
                channel.writeAndFlush(msg);
                break;
            case ChatMsgType.FRIEND_ASK:
                break;
            case ChatMsgType.FRIEND_ACK:
                break;
            case ChatMsgType.GROUP:
                break;
            case ChatMsgType.GROUP_ASK:
                break;
            case ChatMsgType.GROUP_ACK:
                break;
            default:
                log.info("未知类型");
        }

    }

    /*取消绑定*/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 可能出现业务判断离线后再次触发 channelInactive
        log.warn("触发 channelInactive 掉线![{}]", ctx.channel().id());
        userOffLine(ctx);
    }

    /*心跳检查*/
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            // 读空闲
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                // 关闭用户的连接
                userOffLine(ctx);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    /*用户下线*/
    private void userOffLine(ChannelHandlerContext ctx) throws IOException {
        WSSocketHolder.remove(ctx.channel());
        ctx.channel().close();
    }

    private void userLogin(ChannelHandlerContext ctx, Integer uid) throws IOException {
        // 判断是否在线, 如果在线, 则剔除当前在线用户
        Channel channel = WSSocketHolder.get(uid);
        // 如果不是第一次登陆, 并且 客户端ID和当前的不匹配, 则通知之前的客户端下线
        if (channel != null && !ctx.channel().id().equals(channel.id())) {
            ChatMsgProto.ChatMsg chatMsg = ChatMsgProto.ChatMsg.newBuilder()
                    .setType(ChatMsgType.WS_OUT)
                    .setSendTime(new Date().toString())
                    .build();
            // 发送下线消息
            channel.writeAndFlush(chatMsg);
        }

        // 加入 在线 map 中
        WSSocketHolder.put(uid, ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if ("Connection reset by peer".equals(cause.getMessage())) {
            log.error("连接出现问题");
            return;
        }

        log.error(cause.getMessage(), cause);
    }

}
