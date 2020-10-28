package com.xuke.macrosite.chat.client.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.xuke.macrosite.chat.client.thread.ReConnectJob;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuke on 2020/10/24
 */
@Component
public class ReConnectManager {

    @Resource
    private ReConnectJob reConnectJob;

    private ScheduledExecutorService scheduledExecutorService;

    public void reConnect(ChannelHandlerContext ctx) {
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            ThreadFactory threadFactory = new ThreadFactoryBuilder()
                    .setNameFormat("reConnect-job-%d")
                    .setDaemon(true)
                    .build();

            scheduledExecutorService = new ScheduledThreadPoolExecutor(1, threadFactory);
        }

        reConnectJob.setCtx(ctx);
        scheduledExecutorService.scheduleAtFixedRate(reConnectJob, 0, 10, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
}
