package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.CollectDao;
import com.xuke.macrosite.pojo.dto.CollectParams;
import com.xuke.macrosite.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private CollectDao collectDao;

    @Override
    public boolean addCollect(CollectParams collectParams) {
        return collectDao.addCollect(collectParams);
    }

    @Override
    public boolean cancelCollect(CollectParams collectParams) {
        return collectDao.cancelCollect(collectParams);
    }
}
