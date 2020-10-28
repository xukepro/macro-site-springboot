package com.xuke.macrosite.chat.server;

import com.xuke.macrosite.chat.server.route.RouteHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/24
 */
@SpringBootTest
public class RouteTest {

    @Resource
    private RouteHandler routeHandler;

    @Test
    public void test() {
        routeHandler.offLine(null);
    }
}
