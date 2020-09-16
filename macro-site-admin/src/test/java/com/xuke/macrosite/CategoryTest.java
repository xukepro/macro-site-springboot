package com.xuke.macrosite;

import com.xuke.macrosite.dao.CategoryDao;
import com.xuke.macrosite.pojo.dto.CategoryDetail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/16
 */
@SpringBootTest
public class CategoryTest {
    @Resource
    private CategoryDao categoryDao;

    @Test
    public void getAllCategories() {
        List<CategoryDetail> list = categoryDao.getAllCategories();
        list.forEach(System.out::println);
    }
}
