package com.xuke.macrosite.service;

import com.xuke.macrosite.pojo.vo.CollectParams;

/**
 * Created by xuke on 2020/9/15
 */
public interface CollectService {
    boolean addCollect(CollectParams collectParams);

    boolean cancelCollect(CollectParams collectParams);

    boolean checkCollect(CollectParams params);

    boolean deleteCollectByAid(Integer aid);
}
