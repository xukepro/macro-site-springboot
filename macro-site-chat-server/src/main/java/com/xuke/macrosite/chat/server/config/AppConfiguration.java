package com.xuke.macrosite.chat.server.config;

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
    private int httpPort ;

    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.connect.timeout}")
    private int zkConnectTimeout;

    @Value("${app.ws.server.port}")
    private int wsServerPort;

    @Value("${app.route.url}")
    private String routeUrl ;

    @Value("${app.ws.heartbeat.time}")
    private long heartBeatTime ;

    private String ip;
}
