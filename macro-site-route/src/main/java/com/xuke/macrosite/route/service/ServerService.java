package com.xuke.macrosite.route.service;

import com.xuke.macrosite.common.api.route.RouteInfo;
import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.common.util.NetUtil;
import com.xuke.macrosite.route.cache.RouteCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/24
 */
@Component
@Slf4j
public class ServerService {

    @Resource
    private RouteCache routeCache;

    public void checkServer(RouteInfo routeInfo) {
        boolean reachable = NetUtil.checkAddressReachable(routeInfo.getIp(), routeInfo.getWsPort(), 1000);
        if (!reachable) {
            log.info("url[{}:{}] is not available", routeInfo.getIp(), routeInfo.getWsPort());

            routeCache.rebuildCacheList();

            throw new BusinessException(ResCode.SERVER_NOT_AVAILABLE);
        }
    }
}
