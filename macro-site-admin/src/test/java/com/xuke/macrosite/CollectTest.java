package com.xuke.macrosite;

import com.xuke.macrosite.dao.CollectDao;
import com.xuke.macrosite.pojo.dto.CollectParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@SpringBootTest
public class CollectTest {
    @Resource
    private CollectDao collectDao;

    @Test
    public void addCollect() {
        System.out.println(collectDao.addCollect(new CollectParams(3, 1)));
    }

    @Test
    public void cancelCollect() {
        System.out.println(collectDao.cancelCollect(new CollectParams(3, 1)));
    }
}
