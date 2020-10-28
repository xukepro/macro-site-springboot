package com.xuke.macrosite.route.service.impl;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.dto.GroupUserDetail;
import com.xuke.macrosite.common.mongodb.repository.GroupChatRepository;
import com.xuke.macrosite.common.service.DubboService;
import com.xuke.macrosite.route.config.AppConfiguration;
import com.xuke.macrosite.route.service.CacheService;
import com.xuke.macrosite.route.util.RedisUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuke on 2020/10/20
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Reference
    private DubboService dubboService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private AppConfiguration app;

    /**
     * 缓存群与成员关系，用户与好友关系
     */
    @Override
    public void loadData() {
        loadGroups();
        loadFriends();
    }

    public void loadGroups() {
        List<GroupDetail> groupDetailList = dubboService.getGroupUserDetail();

        for (GroupDetail groupDetail : groupDetailList) {
            String key = app.getGroupUserPrefix() + groupDetail.getGid();
            redisUtil.del(key);
            List<GroupUserDetail> userList = groupDetail.getUserList();
            for (GroupUserDetail userDetail : userList) {
                redisUtil.lSet(key, userDetail.getUid());
            }
        }
    }

    public void loadFriends() {

    }
}
