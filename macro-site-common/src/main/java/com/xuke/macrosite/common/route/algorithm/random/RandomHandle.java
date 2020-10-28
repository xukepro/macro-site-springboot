package com.xuke.macrosite.common.route.algorithm.random;


import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.common.route.algorithm.RouteHandle;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Function: 路由策略， 随机
 *
 * @Auther: jiangyunxiong
 * @Date: 2019/3/7 11:56
 * @since JDK 1.8
 */
public class RandomHandle implements RouteHandle {

    @Override
    public String routeServer(List<String> values, String key) {
        int size = values.size();
        if (size == 0) {
            throw new BusinessException(ResCode.SERVER_NOT_AVAILABLE) ;
        }
        int offset = ThreadLocalRandom.current().nextInt(size);

        return values.get(offset);
    }
}
