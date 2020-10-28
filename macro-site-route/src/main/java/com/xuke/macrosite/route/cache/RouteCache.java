package com.xuke.macrosite.route.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.xuke.macrosite.route.util.ZKApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xuke on 2020/10/16
 */
@Component
@Slf4j
public class RouteCache {

    @Resource
    private LoadingCache<String, String> cache;

    @Resource
    private ZKApi zkApi;

    public void addCache(String key) {
        cache.put(key, key);
    }

    public void updateCache(List<String> children) {
        cache.invalidateAll();
        for (String child : children) {
            addCache(child);
        }
    }

    public List<String> getServerList() {
        ArrayList<String> list = new ArrayList<>();

        /* 缓存为空，则先读取写入缓存 */
        if (cache.size() == 0) {
            List<String> nodes = zkApi.getAllNodes();
            for (String node : nodes) {
                addCache(node);
            }
        }

        /* 从缓存获取 */
        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            list.add(entry.getKey());
        }

        return list;
    }

    public void rebuildCacheList() {
        updateCache(getServerList());
    }
}
