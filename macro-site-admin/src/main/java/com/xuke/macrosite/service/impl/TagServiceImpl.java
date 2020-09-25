package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.TagDao;
import com.xuke.macrosite.entity.ArticleTag;
import com.xuke.macrosite.entity.Tag;
import com.xuke.macrosite.pojo.dto.TagDetail;
import com.xuke.macrosite.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/19
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;

    @Override
    @Transactional
    public Tag addTag(String tagName) {
        List<Tag> existedTags = getTag(null, tagName);
        if (existedTags.size() != 0) {
            return existedTags.get(0);
        }
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tagDao.addTag(tag);
        return tag;
    }

    @Override
    @Transactional
    public List<Tag> getTag(Integer tid, String tagName) {
        return tagDao.getTag(tid, tagName);
    }

    @Override
    @Transactional
    public List<TagDetail> getTagDetail() {
        return tagDao.getTagDetail();
    }


    @Override
    @Transactional
    public boolean deleteTag(Integer tid, String tagName) {
        return tagDao.deleteTag(tid, tagName) > 0;
    }


    @Override
    @Transactional
    public ArticleTag addArticleTag(Integer aid, Integer tid) {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setAid(aid);
        articleTag.setTid(tid);
        tagDao.addArticleTag(articleTag);
        return articleTag;
    }

    @Override
    @Transactional
    public List<ArticleTag> getArticleTags(Integer id, Integer aid, Integer tid) {
        return tagDao.getArticleTag(id, aid, tid);
    }

    @Override
    @Transactional
    public boolean deleteOneArticleTag(Integer aid, Integer tid) {
        boolean res = tagDao.deleteArticleTag(null, aid, tid) > 0;
        if (!res) return false;

        /* 在articleTag表中删除aid和tid的一条数据时，如果该tid没有对应的aid了，则在tag表中删除该tid对应tag */
        if (getArticleTags(null, null, tid).size() == 0) {
            deleteTag(tid, null);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean deleteArticleTagByAid(Integer aid) {
        boolean res = tagDao.deleteArticleTag(null, aid, null) > 0;
        if (!res) return false;

        /* 在articleTag表中删除aid对应的全部数据时，如果存在没有对应aid的tid了，则在tag表中删除该tid对应tag */
        List<TagDetail> tagDetailList = getTagDetail();
        for (TagDetail tagDetail : tagDetailList) {
            if (tagDetail.getTotalArticle() == 0) {
                deleteTag(tagDetail.getId(), null);
            }
        }

        return true;
    }
}
