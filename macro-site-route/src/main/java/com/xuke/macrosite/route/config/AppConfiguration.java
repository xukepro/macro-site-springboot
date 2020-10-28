package com.xuke.macrosite.route.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by xuke on 2020/10/16
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

    @Value("${app.route.way}")
    private String routeWay;

    @Value("${app.redis.prefix.account}")
    private String accountPrefix;

    @Value("${app.redis.prefix.route}")
    private String routePrefix;

    @Value("${app.redis.prefix.login-status}")
    private String loginStatusPrefix;

    @Value("${app.redis.prefix.group-user}")
    private String groupUserPrefix;

    @Value("${app.redis.prefix.user-friend}")
    private String userFriendPrefix;

    @Value("${app.route.consitenthash}")
    private String consistentHashWay;

    private String ip;
}
