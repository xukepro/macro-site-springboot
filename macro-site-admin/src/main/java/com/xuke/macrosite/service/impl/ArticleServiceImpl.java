package com.xuke.macrosite.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.xuke.macrosite.dao.ArticleDao;
import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.ArticleDetail;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.ArticleContentVO;
import com.xuke.macrosite.pojo.vo.ArticleListVO;
import com.xuke.macrosite.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public Article insert(Article article) {
        articleDao.insert(article);
        return article;
    }

    @Override
    public ArticleContentVO update(Article article) {
        articleDao.update(article);
        return getArticleContent(article.getId());
    }

    @Override
    public boolean deleteById(Integer id) {
        return articleDao.deleteById(id) > 0;
    }


    @Override
    public List<ArticleListVO> getArticleList(Integer uid, String key) {
        String keyword = "%" + key + "%";
        List<ArticleDetail> articleDetailList = articleDao.getArticleList(uid, keyword);
        List<ArticleListVO> articleListVOList = new ArrayList<>();
        for (ArticleDetail articleDetail : articleDetailList) {
            ArticleListVO articleListVO = new ArticleListVO();
            BeanUtil.copyProperties(articleDetail, articleListVO);
            articleListVOList.add(articleListVO);
        }
        return articleListVOList;
    }

    @Override
    public ArticleContentVO getArticleContent(Integer id) {
        ArticleContentVO articleContentVO = new ArticleContentVO();
        ArticleDetail articleDetail = articleDao.getArticleContent(id);
        BeanUtil.copyProperties(articleDetail, articleContentVO);
        return articleContentVO;
    }

    @Override
    public List<CollectArticle> getMyCollectArticle(Integer uid) {
        return articleDao.getMyCollectArticle(uid);
    }

    @Override
    public boolean addPageViews(Integer id) {
        return articleDao.addPageViews(id);
    }
}
