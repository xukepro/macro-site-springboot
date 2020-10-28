package com.xuke.macrosite.chat.server.ws;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.chat.server.config.AppConfiguration;
import com.xuke.macrosite.chat.server.utils.ProtocolUtil;
import com.xuke.macrosite.common.protobuf.ChatMsgResProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * Created by xuke on 2020/9/27
 */
@Component
@Slf4j
public class WSServer {
    @Resource
    private AppConfiguration app;

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    /* 启动ws */
    @PostConstruct
    public void start() throws InterruptedException {

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(app.getWsServerPort()))
                .childOption(ChannelOption.SO_KEEPALIVE, true) //保持长连接
                .childHandler(new WSServerInitializer());

        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("ws启动成功, 端口号: " + app.getWsServerPort());
        }
    }

    /* 销毁ws */
    @PreDestroy
    public void destroy() {
        boss.shutdownGracefully();
        work.shutdownGracefully();
        log.info("ws关闭成功");
    }

    public void sendMsg(ChatMsgVO chatMsgVO) {
        Channel channel = WSSocketHolder.get(chatMsgVO.getChatContent().getRid());

        if (channel == null) {
            log.info("客户端(uid={})离线, route服务器接收时还在线，到chat服务器时下线了", chatMsgVO.getChatContent().getRid());
            return;
        }

        ChatMsgResProto.ChatMsgRes chatMsgRes = ProtocolUtil.getChatMsgRes(chatMsgVO);

        ChannelFuture future = channel.writeAndFlush(chatMsgRes);
        future.addListener((ChannelFutureListener) channelFuture ->
                log.info("服务器推送数据: {}", chatMsgVO));
    }
}
