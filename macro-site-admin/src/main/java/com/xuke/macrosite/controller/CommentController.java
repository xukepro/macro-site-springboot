package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.CommentDetail;
import com.xuke.macrosite.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("byArticle")
    ResResult<List<CommentDetail>> getCommentByArticle(@RequestParam("aid") Integer aid){
        return ResResult.success(commentService.getCommentByArticle(aid));
    }

    @GetMapping("published")
    ResResult<List<CommentDetail>>  getPublishedComment(@RequestParam("uid") Integer uid){
        return ResResult.success(commentService.getPublishedComment(uid));
    }

    @GetMapping("received")
    ResResult<List<CommentDetail>> getReceivedComment(@RequestParam("uid") Integer uid){
        return ResResult.success(commentService.getReceivedComment(uid));
    }

    @GetMapping("all")
    ResResult<List<CommentDetail>> getAllComment(){
        return ResResult.success(commentService.getAllComment());
    }
}
