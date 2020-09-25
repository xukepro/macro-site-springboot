package com.xuke.macrosite.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.xuke.macrosite.dao.ArticleDao;
import com.xuke.macrosite.entity.Tag;
import com.xuke.macrosite.pojo.dto.ArticleDetail;
import com.xuke.macrosite.pojo.dto.ArticleList;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.AddArticleParams;
import com.xuke.macrosite.pojo.dto.ArticleContent;
import com.xuke.macrosite.pojo.vo.UpdateArticleParams;
import com.xuke.macrosite.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private CommentService commentService;
    @Resource
    private LikeService likeService;
    @Resource
    private CollectService collectService;
    @Resource
    private TagService tagService;

    @Override
    @Transactional
    public List<ArticleList> getArticleList(Integer uid, String key, Integer cid, List<String> tags, Integer pageSize, Integer pageNum) {
        String keyword = key == null ? null : "%" + key + "%";
        List<ArticleDetail> articleDetailList = articleDao.getArticleList(uid, keyword, cid);

        List<ArticleList> articleListVOList = new ArrayList<>();
        a: for (ArticleDetail articleDetail : articleDetailList) {
            if (tags != null && tags.size() != 0) {
                for (String tag : tags) {
                    if (!articleDetail.getTags().contains(tag)) continue a;
                }
            }
            ArticleList articleListVO = new ArticleList();
            BeanUtil.copyProperties(articleDetail, articleListVO);
            articleListVOList.add(articleListVO);
        }
        return articleListVOList;
    }

    @Override
    @Transactional
    public ArticleContent getArticleContent(Integer id) {
        ArticleContent articleContent = new ArticleContent();
        ArticleDetail articleDetail = articleDao.getArticleContent(id);
        BeanUtil.copyProperties(articleDetail, articleContent);
        return articleContent;
    }

    @Override
    @Transactional
    public List<CollectArticle> getMyCollectArticle(Integer uid) {
        return articleDao.getMyCollectArticle(uid);
    }

    @Override
    @Transactional
    public ArticleContent insert(AddArticleParams article) {
        articleDao.insert(article);

        List<String> tags = article.getTags();
        if (tags != null && tags.size() > 0) {
            for (String tagName : tags) {
                Integer tid;
                List<Tag> existedTags = tagService.getTag(null, tagName);
                /* 没有tag则创建 */
                if (existedTags.size() == 0) {
                    Tag t = tagService.addTag(tagName);
                    tid = t.getId();
                } else {
                    tid = existedTags.get(0).getId();
                }
                /* 给article添加tag */
                tagService.addArticleTag(article.getId(), tid);
            }
        }
        return getArticleContent(article.getId());
    }

    @Override
    @Transactional
    public ArticleContent update(UpdateArticleParams article) {
        articleDao.update(article);
        ArticleContent articleContent = getArticleContent(article.getId());
        System.out.println(article);

        /* 更新tag */
        List<String> tags = article.getTags();
        Integer aid = article.getId();

        if (tags != null && tags.size() > 0) {
            List<String> existedTags = articleContent.getTags();
            for (String existedTagName : existedTags) {
                /* 删除已存在但之后没有的tag */
                if (!tags.contains(existedTagName)) {
                    List<Tag> list = tagService.getTag(null, existedTagName);
                    Tag tag = list.get(0);
                    tagService.deleteOneArticleTag(aid, tag.getId());
                }
            }

            for (String tagName : tags) {
                /* 添加以前不存在现在需要的tag */
                if (!existedTags.contains(tagName)) {
                    Tag t = tagService.addTag(tagName);
                    Integer tid = t.getId();
                    tagService.addArticleTag(aid, tid);
                }
            }
        }

        return getArticleContent(article.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        boolean result = articleDao.deleteById(id) > 0;
        if (!result) return false;

        /* 删除文章评论 */

        commentService.deleteCommentByAId(id);

        /* 删除文章点赞 */
        likeService.deleteLikeByAid(id);

        /* 删除文章收藏 */
        collectService.deleteCollectByAid(id);

        /* 删除文章Tag关联 */
        tagService.deleteArticleTagByAid(id);

        return true;
    }


    @Override
    @Transactional
    public boolean addPageViews(Integer id) {
        return articleDao.addPageViews(id) > 0;
    }
}
