package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.Collect;
import com.xuke.macrosite.pojo.vo.CollectParams;

/**
 * Created by xuke on 2020/9/15
 */
public interface CollectDao {
    Integer addCollect(CollectParams collectParams);

    Integer cancelCollect(CollectParams collectParams);

    Collect checkCollect(CollectParams params);
}
