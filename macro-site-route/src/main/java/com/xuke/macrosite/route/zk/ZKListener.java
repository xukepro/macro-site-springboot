package com.xuke.macrosite.route.zk;

import com.xuke.macrosite.route.util.ZKApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/16
 */
@Component
@Slf4j
public class ZKListener implements Runnable {

    @Resource
    private ZKApi zkApi;

    @Override
    public void run() {
        zkApi.subscribeEvent();
    }
}
