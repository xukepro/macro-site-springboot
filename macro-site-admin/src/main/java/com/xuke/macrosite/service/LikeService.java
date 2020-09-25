package com.xuke.macrosite.service;

import com.xuke.macrosite.pojo.vo.LikeParams;

/**
 * Created by xuke on 2020/9/15
 */
public interface LikeService {
    boolean addLike(LikeParams params);

    boolean cancelLike(LikeParams params);

    boolean checkLike(LikeParams params);

    boolean deleteLikeByAid(Integer aid);
}
