package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.entity.Collect;
import com.xuke.macrosite.entity.Like;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.dto.CollectParams;
import com.xuke.macrosite.pojo.dto.LikeParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
public interface CollectDao {
    boolean addCollect(CollectParams collectParams);

    boolean cancelCollect(CollectParams collectParams);

    Collect checkCollect(CollectParams params);
}
