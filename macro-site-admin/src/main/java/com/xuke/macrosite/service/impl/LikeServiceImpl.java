package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.LikeDao;
import com.xuke.macrosite.pojo.dto.LikeParams;
import com.xuke.macrosite.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@Service
public class LikeServiceImpl implements LikeService {
    @Resource
    private LikeDao likeDao;

    @Override
    public boolean addCollect(LikeParams params) {
        return likeDao.addLike(params);
    }

    @Override
    public boolean cancelCollect(LikeParams params) {
        return likeDao.cancelLike(params);
    }
}
