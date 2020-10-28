package com.xuke.macrosite.dao;

import com.xuke.macrosite.pojo.dto.ArticleDetail;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.AddArticleParams;
import com.xuke.macrosite.pojo.vo.UpdateArticleParams;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
@Repository
//@Mapper // @MapperScan替代
public interface ArticleDao {

    Integer insert(AddArticleParams article);

    Integer update(UpdateArticleParams article);

    Integer deleteById(Integer id);

    List<ArticleDetail> getArticleList(@Param("uid") Integer uid, @Param("key") String key, @Param("cid") Integer cid);

    ArticleDetail getArticleContent(Integer id);

    List<CollectArticle> getMyCollectArticle(Integer uid);

    Integer addPageViews(Integer id);
}
