package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.Like;
import com.xuke.macrosite.pojo.vo.LikeParams;

/**
 * Created by xuke on 2020/9/15
 */
public interface LikeDao {
    Integer addLike(LikeParams params);

    Integer cancelLike(LikeParams params);

    Like checkLike(LikeParams params);
}
