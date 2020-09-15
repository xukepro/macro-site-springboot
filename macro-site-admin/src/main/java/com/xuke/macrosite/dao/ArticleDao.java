package com.xuke.macrosite.dao;

import com.xuke.macrosite.pojo.dto.ArticleDetail;
import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
@Repository
//@Mapper // @MapperScan替代
public interface ArticleDao {
    int insert(Article article);

    int update(Article article);

    int deleteById(Integer id);

    List<ArticleDetail> getArticleList(@Param("uid") Integer uid, @Param("key") String key);

    ArticleDetail getArticleContent(Integer id);

    List<CollectArticle> getMyCollectArticle(Integer uid);

    boolean addPageViews(Integer id);
}
