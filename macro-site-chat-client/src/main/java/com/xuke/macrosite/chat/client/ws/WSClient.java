package com.xuke.macrosite.chat.client.ws;

import com.xuke.macrosite.chat.client.config.AppConfiguration;
import com.xuke.macrosite.chat.client.service.ReConnectManager;
import com.xuke.macrosite.chat.client.service.RouteService;
import com.xuke.macrosite.chat.client.thread.ContextHolder;
import com.xuke.macrosite.chat.client.thread.ReConnectJob;
import com.xuke.macrosite.common.api.route.LoginVO;
import com.xuke.macrosite.common.api.route.RouteInfo;
import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.protobuf.ChatMsgReqProto;
import com.xuke.macrosite.common.protobuf.ChatMsgResProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.URISyntaxException;

/**
 * Created by xuke on 2020/10/1
 */
@Component
@Slf4j
public class WSClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    private SocketChannel channel;

    @Resource
    private AppConfiguration app;

    @Resource
    private RouteService routeService;

    @Resource
    private ReConnectManager reConnectManager;

    @PostConstruct
    public void start() throws InterruptedException {
        RouteInfo routeInfo = loginRoute();

        startClient(routeInfo);

        loginServer();
    }

    private RouteInfo loginRoute() {
        LoginVO loginVO = new LoginVO(app.getUid());
        RouteInfo serverRoute = routeService.getChatServer(loginVO);
        log.info("登录成功，分配的服务器为" + serverRoute);
        return serverRoute;
    }

    private void startClient(RouteInfo routeInfo) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new IdleStateHandler(10, 10, 10));

                        // 协议包解码时指定Protobuf字节数实例化为CommonProtocol类型
                        pipeline.addLast(new ProtobufEncoder());
                        pipeline.addLast(new ProtobufDecoder(ChatMsgResProto.ChatMsgRes.getDefaultInstance()));
                        pipeline.addLast(new WSClientHandler());
                    }
                });

        ChannelFuture future = bootstrap.connect(routeInfo.getIp(), routeInfo.getWsPort()).sync();
        if (future.isSuccess()) {
            log.info("client启动成功");
        }
        channel = (SocketChannel) future.channel();
    }

    private void loginServer() {
        ChatMsgReqProto.ChatMsgReq chatMsgReq = ChatMsgReqProto.ChatMsgReq.newBuilder()
                .setType(ChatMsgType.LOGIN)
                .setUid(app.getUid())
                .build();
        ChannelFuture future = channel.writeAndFlush(chatMsgReq);
        future.addListener((ChannelFutureListener) channelFuture -> log.info("login server success"));
    }

    @PreDestroy
    public void destroy() {
        group.shutdownGracefully();
    }

    public void sendMsg(ChatMsgResProto.ChatMsgRes msg) {
        channel.writeAndFlush(msg);
    }

    public void reconnect() throws InterruptedException {
        if (channel != null && channel.isActive()) {
            return ;
        }

        log.info("重连中。。。");
        start();
        log.info("重连成功！");
        reConnectManager.shutdown();
        ContextHolder.clear();
    }
}
