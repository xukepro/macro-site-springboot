package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.CommentDetail;
import com.xuke.macrosite.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/13
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @ApiOperation("获得文章的所有评论")
    @GetMapping("byArticle/{aid}")
    ResResult<List<CommentDetail>> getCommentByArticle(@PathVariable("aid") Integer aid){
        return ResResult.success(commentService.getCommentByArticle(aid));
    }

    @ApiOperation("获得一个用户发布的评论")
    @GetMapping("published/{uid}")
    ResResult<List<CommentDetail>>  getPublishedComment(@PathVariable("uid") Integer uid){
        return ResResult.success(commentService.getPublishedComment(uid));
    }

    @ApiOperation("获得一个用户收到的评论")
    @GetMapping("received/{uid}")
    ResResult<List<CommentDetail>> getReceivedComment(@PathVariable("uid") Integer uid){
        return ResResult.success(commentService.getReceivedComment(uid));
    }

    @ApiOperation("获得所有评论")
    @GetMapping("all")
    ResResult<List<CommentDetail>> getAllComment(){
        return ResResult.success(commentService.getAllComment());
    }

    @ApiOperation("根据评论id删除文章")
    @DeleteMapping("{id}")
    ResResult<Boolean> deleteComment(@PathVariable("id") Integer id) {
        return ResResult.success(commentService.deleteCommentById(id));
    }
}
