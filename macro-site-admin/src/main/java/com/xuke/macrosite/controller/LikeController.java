package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.vo.LikeParams;
import com.xuke.macrosite.service.LikeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@RestController
@RequestMapping("like")
public class LikeController {
    @Resource
    private LikeService likeService;

    @ApiOperation("点赞")
    @PostMapping("add")
    public ResResult<Boolean> addLike(@RequestBody @Validated LikeParams params) {
        boolean flag = likeService.checkLike(params);
        if (flag) return ResResult.badRequest("已经点赞过了,不能重复点赞");
        return ResResult.success(likeService.addLike(params));
    }

    @ApiOperation("取消点赞")
    @PostMapping("cancel")
    public ResResult<Boolean> cancelLike(@RequestBody @Validated LikeParams params) {
        boolean flag = likeService.checkLike(params);
        if (!flag) {
            return ResResult.badRequest("没有点赞过，无法取消点赞");
        }
        return ResResult.success(likeService.cancelLike(params));
    }
}
