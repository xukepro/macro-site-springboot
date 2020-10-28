package com.xuke.macrosite.chat.server.ws;

import com.xuke.macrosite.chat.server.route.RouteHandler;
import com.xuke.macrosite.chat.server.utils.SpringUtil;
import com.xuke.macrosite.common.constant.ChatContantType;
import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.protobuf.ChatContentResProto;
import com.xuke.macrosite.common.protobuf.ChatMsgReqProto;
import com.xuke.macrosite.common.protobuf.ChatMsgResProto;
import com.xuke.macrosite.common.route.algorithm.RouteHandle;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@ChannelHandler.Sharable
@Slf4j
public class WSServerHandler extends SimpleChannelInboundHandler<ChatMsgReqProto.ChatMsgReq> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().id() + "连上服务器:" + ctx.channel().localAddress());
        ChatContentResProto.ChatContentRes chatContentRes = ChatContentResProto.ChatContentRes.newBuilder()
                .setRid(1)
                .setType(ChatContantType.TEXT)
                .setContent("返回消息")
                .build();

        ChatMsgResProto.ChatMsgRes chatMsgRes = ChatMsgResProto.ChatMsgRes.newBuilder()
                .setUid(2)
                .setType(ChatMsgType.FRIEND)
                .setChatContent(chatContentRes)
                .build();
        ChatMsgResProto.ChatMsgRes chatMsg = ChatMsgResProto.ChatMsgRes.newBuilder()
                .setType(ChatMsgType.LOGIN)
                .setUid(1)
                .build();
        ctx.channel().writeAndFlush(chatMsgRes);
        ctx.channel().writeAndFlush(chatMsg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    /*读到客户端的内容*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatMsgReqProto.ChatMsgReq msg) throws Exception {
        Integer uid = msg.getUid();

        if (msg.getType() == ChatMsgType.LOGIN) {
            log.info("用户[{}]登录", uid);

            /* 需要如果route踢除当前在线用户 */

//            // 判断是否在线, 如果在线, 则剔除当前在线用户
//            Channel channel = WSSocketHolder.get(uid);
//            // 如果不是第一次登陆, 并且 客户端ID和当前的不匹配, 则通知之前的客户端下线
//            if (channel != null && !ctx.channel().id().equals(channel.id())) {
//                ChatMsgResProto.ChatMsgRes chatMsg = ChatMsgResProto.ChatMsgRes.newBuilder()
//                        .setType(ChatMsgType.WS_OUT)
//                        .setSendTime(new Date().toString())
//                        .build();
//                // 发送下线消息
//                channel.writeAndFlush(chatMsg);
//            }

            // 加入 在线 map 中
            WSSocketHolder.put(uid, ctx.channel());
        } else if (msg.getType() == ChatMsgType.PING) {
            log.info("客户端[{}]心跳", ctx.channel().id());
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
                log.warn("读空闲，触发掉线[{}]", ctx.channel().id());
                userOffLine(ctx);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    /*用户下线*/
    private void userOffLine(ChannelHandlerContext ctx) throws IOException {
        RouteHandler routeHandler = SpringUtil.getBean(RouteHandler.class);
        /* 删除服务器id与channel关系 */
        WSSocketHolder.remove(ctx.channel());
        /* 通知路由服务器删除路由信息 */
        routeHandler.offLine(ctx.channel());
        ctx.channel().close();
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
