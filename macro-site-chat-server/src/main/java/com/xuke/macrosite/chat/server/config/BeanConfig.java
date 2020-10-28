package com.xuke.macrosite.chat.server.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuke on 2020/10/15
 */
@Configuration
@Slf4j
public class BeanConfig {

    @Resource
    private AppConfiguration app;

    @Bean
    public ZkClient buildZkClient() {
        String zkAddr = app.getZkAddr();
        int zkConnectTimeout = app.getZkConnectTimeout();
        log.info("连接zookeeper，addr: " + zkAddr + "，超时时间: " + zkConnectTimeout);
        return new ZkClient(zkAddr, zkConnectTimeout);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }
}
