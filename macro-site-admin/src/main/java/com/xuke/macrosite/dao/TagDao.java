package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.ArticleTag;
import com.xuke.macrosite.entity.Tag;
import com.xuke.macrosite.pojo.dto.TagDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuke on 2020/9/19
 */
public interface TagDao {
    Integer addTag(Tag tag);

    List<Tag> getTag(@Param("id") Integer id, @Param("tagName") String tagName);

    List<TagDetail> getTagDetail();

    Integer deleteTag(@Param("id") Integer id, @Param("tagName") String tagName);


    Integer addArticleTag(ArticleTag articleTag);

    List<ArticleTag> getArticleTag(@Param("id") Integer id, @Param("aid") Integer aid, @Param("tid") Integer tid);

    Integer deleteArticleTag(@Param("id") Integer id, @Param("aid") Integer aid, @Param("tid") Integer tid);
}
