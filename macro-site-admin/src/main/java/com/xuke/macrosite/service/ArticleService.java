package com.xuke.macrosite.service;

import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.ArticleContentVO;
import com.xuke.macrosite.pojo.vo.ArticleListVO;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
public interface ArticleService {
    List<ArticleListVO> getArticleList(Integer uid, String key);

    ArticleContentVO getArticleContent(Integer id);

    List<CollectArticle> getMyCollectArticle(Integer uid);

    Article insert(Article article);

    ArticleContentVO update(Article article);

    boolean deleteById(Integer id);

    boolean addPageViews(Integer id);
}
