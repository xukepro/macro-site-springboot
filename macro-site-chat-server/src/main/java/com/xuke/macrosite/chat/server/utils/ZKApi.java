package com.xuke.macrosite.chat.server.utils;

import com.xuke.macrosite.chat.server.config.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/15
 */

@Component
@Slf4j
public class ZKApi {
    @Resource
    private ZkClient zkClient;

    @Resource
    private AppConfiguration app;

    /**
     * 创建父节点
     */
    public void createRootNode() {
        boolean exists = zkClient.exists(app.getZkRoot());
        if (exists) {
            return;
        }
        zkClient.createPersistent(app.getZkRoot());
    }

    /**
     * 写入临时节点
     * @param path
     */
    public void createNode(String path) {
        boolean exists = zkClient.exists(path);
        if (exists) {
            return;
        }
        zkClient.createEphemeral(path);
    }
}
