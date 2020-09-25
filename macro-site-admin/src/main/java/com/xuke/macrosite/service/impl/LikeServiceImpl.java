package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.LikeDao;
import com.xuke.macrosite.entity.Like;
import com.xuke.macrosite.pojo.vo.LikeParams;
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
    public boolean addLike(LikeParams params) {
        return likeDao.addLike(params) > 0;
    }

    @Override
    public boolean cancelLike(LikeParams params) {
        return likeDao.cancelLike(params) > 0;
    }

    // 检查是否已经点赞了
    @Override
    public boolean checkLike(LikeParams params) {
        return likeDao.checkLike(params) != null;
    }

    @Override
    public boolean deleteLikeByAid(Integer aid) {
        LikeParams params = new LikeParams();
        params.setAid(aid);
        return likeDao.cancelLike(params) > 0;
    }
}
