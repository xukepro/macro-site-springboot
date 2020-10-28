package com.xuke.macrosite.route.service.impl;

import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.api.route.LoginVO;
import com.xuke.macrosite.common.api.route.OffLineVO;
import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.service.FriendChatService;
import com.xuke.macrosite.route.config.AppConfiguration;
import com.xuke.macrosite.route.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/10/18
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AppConfiguration app;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void login(LoginVO loginVO) {

    }

    @Override
    public Boolean offLine(OffLineVO offLineVO) {
        Boolean res = redisTemplate.delete(app.getRoutePrefix() + offLineVO.getUid());
        if (res != null) {
            log.info("用户[{}]下线，删除路由信息成功", offLineVO.getUid());
        } else {
            log.info("用户[{}]下线，删除路由信息失败", offLineVO.getUid());
        }
        return res;
    }

}
