package com.xuke.macrosite.chat.server.route;

import com.alibaba.fastjson.JSON;
import com.xuke.macrosite.chat.server.config.AppConfiguration;
import com.xuke.macrosite.chat.server.ws.WSSocketHolder;
import com.xuke.macrosite.common.api.route.OffLineVO;
import com.xuke.macrosite.common.api.route.RouteApi;
import com.xuke.macrosite.common.api.route.RouteInfo;
import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.proxy.ProxyManager;
import com.xuke.macrosite.common.res.ResResult;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/24
 */
@Component
@Slf4j
public class RouteHandler {

    @Resource
    private AppConfiguration app;

    @Resource
    private OkHttpClient okHttpClient;

    public void offLine(Channel channel) {
        RouteApi routeApi = new ProxyManager<>(RouteApi.class, app.getRouteUrl() + "route", okHttpClient).getInstance();
        Integer uid = WSSocketHolder.getUid(channel);
//        if (uid == null) {
//            return;
//        }
        uid = 1;

        OffLineVO offLineVO = new OffLineVO(uid);
        Response response = null;
        try {
            response = (Response) routeApi.offLine(offLineVO);
            String json = response.body().string();
            ResResult resResult = JSON.parseObject(json, ResResult.class);
            if (resResult.getCode().equals(ResCode.SUCCESS.getCode())) {
                log.info("成功发送下线请求");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.body().close();
        }
    }
}
