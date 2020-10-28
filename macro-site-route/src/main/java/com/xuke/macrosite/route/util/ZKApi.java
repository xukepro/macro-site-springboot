package com.xuke.macrosite.route.util;

import com.xuke.macrosite.route.cache.RouteCache;
import com.xuke.macrosite.route.config.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private RouteCache routeCache;

    /**
     * 监听事件
     */
    public void subscribeEvent() {
        zkClient.subscribeChildChanges(app.getZkRoot(), new IZkChildListener() {
            @Override
            public void handleChildChange(String path, List<String> children) throws Exception {
                log.info("更新本地缓存 path=[{}], children=[{}]", path, children);
                routeCache.updateCache(children);
            }
        });
    }

    public List<String> getAllNodes() {
        List<String> children = zkClient.getChildren(app.getZkRoot());
        log.info("查询所有节点成功，nodes=[{}]", children);
        return children;
    }
}
