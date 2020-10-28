package com.xuke.macrosite.chat.client.service;

import com.alibaba.fastjson.JSON;
import com.xuke.macrosite.chat.client.config.AppConfiguration;
import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.api.route.LoginVO;
import com.xuke.macrosite.common.api.route.RouteApi;
import com.xuke.macrosite.common.api.route.RouteInfo;
import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.proxy.ProxyManager;
import com.xuke.macrosite.common.res.ResResult;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/23
 */
@Service
@Slf4j
public class RouteService {
    @Resource
    private AppConfiguration app;

    @Resource
    private OkHttpClient okHttpClient;

    public RouteInfo getChatServer(LoginVO loginVO) {
        RouteApi routeApi = new ProxyManager<>(RouteApi.class, app.getRouteUrl() + "route", okHttpClient).getInstance();
        Response response = null;
        try {
            response = (Response) routeApi.login(loginVO);
            String json = response.body().string();
            ResResult resResult = JSON.parseObject(json, ResResult.class);
            if (resResult.getCode().equals(ResCode.SUCCESS.getCode())) {
                Object data = resResult.getData();
                return JSON.toJavaObject((JSON) data, RouteInfo.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.body().close();
        }

        return null;
    }

    public void sendMsg(ChatMsgVO chatMsgVO) {
        RouteApi routeApi = new ProxyManager<>(RouteApi.class, app.getRouteUrl() + "route", okHttpClient).getInstance();
        Response response = null;
        try {
            response = (Response) routeApi.chat(chatMsgVO);
            String json = response.body().string();
            ResResult resResult = JSON.parseObject(json, ResResult.class);
            log.info("接收相应: " + resResult);
            if (resResult.getCode().equals(ResCode.SUCCESS.getCode())) {
                log.info("聊天消息发送成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.body().close();
        }
    }
}
