package com.xuke.macrosite.chat.client.config;

import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.protobuf.ChatMsgReqProto;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
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
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    @Bean("heartBeat")
    public ChatMsgReqProto.ChatMsgReq heartBeat() {
        return ChatMsgReqProto.ChatMsgReq.newBuilder()
                .setType(ChatMsgType.PING)
                .setUid(app.getUid())
                .build();
    }
}
