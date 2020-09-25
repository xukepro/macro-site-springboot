package com.xuke.macrosite.service;

import com.xuke.macrosite.entity.ArticleTag;
import com.xuke.macrosite.entity.Tag;
import com.xuke.macrosite.pojo.dto.TagDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuke on 2020/9/19
 */
public interface TagService {
    Tag addTag(String tagName);

    List<Tag> getTag(Integer tid, String tagName);

    List<TagDetail> getTagDetail();

    boolean deleteTag(Integer tid, String tagName);


    ArticleTag addArticleTag(Integer aid, Integer tid);

    List<ArticleTag> getArticleTags(Integer id, Integer aid, Integer tid);

    boolean deleteOneArticleTag(Integer aid, Integer tid);

    boolean deleteArticleTagByAid(Integer aid);
}
