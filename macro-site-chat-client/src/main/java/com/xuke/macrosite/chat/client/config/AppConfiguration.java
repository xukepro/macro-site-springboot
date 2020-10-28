package com.xuke.macrosite.chat.client.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xuke on 2020/10/15
 */
@Component
@Data
public class AppConfiguration {

    @Value("${server.port}")
    private int httpPort;

    @Value("${app.uid}")
    private int uid;

    @Value("${app.route.url}")
    private String routeUrl;

    @Value("${app.ws.heartbeat.time}")
    private String heartBeatTime;

    @Value("${app.ws.reconnect.count}")
    private String reconnectCount;

    private String ip;
}
