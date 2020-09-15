package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.LikeParams;

import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
public interface LikeDao {
    boolean addLike(LikeParams params);

    boolean cancelLike(LikeParams params);
}
