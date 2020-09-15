package com.xuke.macrosite;

import com.xuke.macrosite.dao.CollectDao;
import com.xuke.macrosite.dao.LikeDao;
import com.xuke.macrosite.pojo.dto.CollectParams;
import com.xuke.macrosite.pojo.dto.LikeParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@SpringBootTest
public class LikeTest {
    @Resource
    private LikeDao likeDao;

    @Test
    public void addLike() {
        System.out.println(likeDao.addLike(new LikeParams(5, 1)));
    }

    @Test
    public void cancelLike() {
        System.out.println(likeDao.cancelLike(new LikeParams(5, 1)));
    }
}
