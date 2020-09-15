package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.dao.CommentDao;
import com.xuke.macrosite.pojo.dto.CommentDetail;
import com.xuke.macrosite.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/13
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Override
    public List<CommentDetail> getCommentByArticle(Integer aid) {
        return commentDao.getCommentByArticle(aid);
    }

    @Override
    public List<CommentDetail> getPublishedComment(Integer uid) {
        return commentDao.getPublishedComment(uid);
    }

    @Override
    public List<CommentDetail> getReceivedComment(Integer uid) {
        return commentDao.getReceivedComment(uid);
    }

    @Override
    public List<CommentDetail> getAllComment() {
        return commentDao.getAllComment();
    }
}
