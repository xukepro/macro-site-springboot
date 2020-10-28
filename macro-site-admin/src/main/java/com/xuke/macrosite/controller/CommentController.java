package com.xuke.macrosite.controller;

import com.xuke.macrosite.pojo.dto.CommentDetail;
import com.xuke.macrosite.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/13
 */
@Api(tags = "CommentController")
@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @ApiOperation("获得文章的所有评论")
    @GetMapping("byArticle/{aid}")
    List<CommentDetail> getCommentByArticle(@PathVariable("aid") Integer aid){
        return commentService.getCommentByArticle(aid);
    }

    @ApiOperation("获得一个用户发布的评论")
    @GetMapping("published/{uid}")
    List<CommentDetail>  getPublishedComment(@PathVariable("uid") Integer uid){
        return commentService.getPublishedComment(uid);
    }

    @ApiOperation("获得一个用户收到的评论")
    @GetMapping("received/{uid}")
    List<CommentDetail> getReceivedComment(@PathVariable("uid") Integer uid){
        return commentService.getReceivedComment(uid);
    }

    @ApiOperation("获得所有评论")
    @GetMapping("all")
    List<CommentDetail> getAllComment(){
        return commentService.getAllComment();
    }

    @ApiOperation("根据评论id删除文章")
    @DeleteMapping("{id}")
    boolean deleteComment(@PathVariable("id") Integer id) {
        return commentService.deleteCommentById(id);
    }
}
