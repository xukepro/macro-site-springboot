package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.CategoryDao;
import com.xuke.macrosite.pojo.dto.CategoryDetail;
import com.xuke.macrosite.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/16
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDetail> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
