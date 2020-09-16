package com.xuke.macrosite.service;

import com.xuke.macrosite.entity.Collect;
import com.xuke.macrosite.pojo.dto.CollectParams;

/**
 * Created by xuke on 2020/9/15
 */
public interface CollectService {
    boolean addCollect(CollectParams collectParams);

    boolean cancelCollect(CollectParams collectParams);

    boolean checkCollect(CollectParams params);
}
