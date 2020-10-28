package com.xuke.macrosite.common.util;

import com.xuke.macrosite.common.api.route.RouteInfo;

/**
 * Created by xuke on 2020/10/18
 */
public class RouteInfoUtil {

    public static RouteInfo parse(String info) {
        String[] split = info.split(":");
        RouteInfo routeInfo = new RouteInfo(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        return routeInfo;
    }
}
