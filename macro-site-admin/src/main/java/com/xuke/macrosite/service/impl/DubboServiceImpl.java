package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.service.DubboService;
import com.xuke.macrosite.service.GroupService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/10/22
 */
@Service
@Component
public class DubboServiceImpl implements DubboService {
    @Resource
    private GroupService groupService;

    @Override
    public List<GroupDetail> getGroupUserDetail() {
        return groupService.getGroupUserDetail();
    }

    @Override
    public GroupDetail getGroupUserDetailByGid(Integer gid) {
        return groupService.getGroupUserDetailByGid(gid);
    }
}
