package com.xuke.macrosite.dao;

import com.xuke.macrosite.entity.Comment;
import com.xuke.macrosite.pojo.dto.CommentDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuke on 2020/9/13
 */
@Repository
public interface CommentDao {
    /**
     * 文章的评论
     */
    List<CommentDetail> getCommentByArticle(Integer aid);

    /**
     * 用户发表的的评论
     */
    List<CommentDetail> getPublishedComment(Integer uid);

    /**
     * 收到别人的评论
     */
    List<CommentDetail> getReceivedComment(Integer uid);

    /**
     * 管理员能看到的所有评论
     */
    List<CommentDetail> getAllComment();
}
