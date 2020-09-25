package com.xuke.macrosite.service;

import com.xuke.macrosite.pojo.dto.ArticleList;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.AddArticleParams;
import com.xuke.macrosite.pojo.dto.ArticleContent;
import com.xuke.macrosite.pojo.vo.UpdateArticleParams;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
public interface ArticleService {
    List<ArticleList> getArticleList(Integer uid, String key, Integer cid, List<String> tags, Integer pageSize, Integer pageNum);

    ArticleContent getArticleContent(Integer id);

    List<CollectArticle> getMyCollectArticle(Integer uid);

    ArticleContent insert(AddArticleParams article);

    ArticleContent update(UpdateArticleParams article);

    boolean deleteById(Integer id);

    boolean addPageViews(Integer id);
}
