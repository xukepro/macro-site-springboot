package com.xuke.macrosite.controller;

import com.xuke.macrosite.pojo.dto.CategoryDetail;
import com.xuke.macrosite.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/16
 */
@Api(tags = "CategoryController")
@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation("获得所有目录")
    @GetMapping("all")
    public List<CategoryDetail> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
