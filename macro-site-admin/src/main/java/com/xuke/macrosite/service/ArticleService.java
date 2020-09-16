package com.xuke.macrosite.service;

import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.ArticleDetail;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.AddArticleVO;
import com.xuke.macrosite.pojo.vo.ArticleContentVO;
import com.xuke.macrosite.pojo.vo.UpdateArticleVO;

import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
public interface ArticleService {
    List<ArticleDetail> getArticleList(Integer uid, String key, Integer cid, Integer pageSize, Integer pageNum);

    ArticleContentVO getArticleContent(Integer id);

    List<CollectArticle> getMyCollectArticle(Integer uid);

    Article insert(AddArticleVO article);

    ArticleContentVO update(UpdateArticleVO article);

    boolean deleteById(Integer id);

    boolean addPageViews(Integer id);
}
