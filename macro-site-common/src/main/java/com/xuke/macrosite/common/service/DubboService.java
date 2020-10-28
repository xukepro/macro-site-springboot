package com.xuke.macrosite.common.service;

import com.xuke.macrosite.common.dto.GroupDetail;

import java.util.List;

/**
 * Created by xuke on 2020/10/22
 */
public interface DubboService {
    List<GroupDetail> getGroupUserDetail();

    GroupDetail getGroupUserDetailByGid(Integer gid);
}
