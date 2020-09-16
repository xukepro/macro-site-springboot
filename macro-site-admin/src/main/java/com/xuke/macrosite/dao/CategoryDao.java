package com.xuke.macrosite.dao;

import com.xuke.macrosite.pojo.dto.CategoryDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuke on 2020/9/13
 */
@Repository
public interface CategoryDao {
    List<CategoryDetail> getAllCategories();
}
