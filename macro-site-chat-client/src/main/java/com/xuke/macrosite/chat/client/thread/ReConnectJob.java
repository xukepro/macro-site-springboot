package com.xuke.macrosite.chat.client.thread;

import com.xuke.macrosite.chat.client.ws.WSClient;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/24
 */
@Component
public class ReConnectJob implements Runnable {

    @Resource
    private WSClient wsClient;

    private ChannelHandlerContext ctx;

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        try {
            ContextHolder.setReconnect(true);
            wsClient.reconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
