package com.xuke.macrosite.chat.server.zk;

import com.xuke.macrosite.chat.server.config.AppConfiguration;
import com.xuke.macrosite.chat.server.utils.ZKApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/15
 */
@Slf4j
@Component
public class RegistryZK implements Runnable{

    @Resource
    private ZKApi zkApi;

    @Resource
    private AppConfiguration app;

    @Override
    public void run() {
        zkApi.createRootNode();

        // 注册到zookeeper中
        String path = app.getZkRoot() +
                "/" + app.getIp() +
                ":" + app.getWsServerPort() +
                ":" + app.getHttpPort();
        zkApi.createNode(path);
        log.info("chat server注册到zookeeper中，path: " + path);
    }
}
