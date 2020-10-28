package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.enums.ResCode;
import com.xuke.macrosite.common.exception.BusinessException;
import com.xuke.macrosite.pojo.vo.LikeParams;
import com.xuke.macrosite.service.LikeService;
import io.swagger.annotations.Api;
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
@Api(tags = "LikeController")
@RestController
@RequestMapping("like")
public class LikeController {
    @Resource
    private LikeService likeService;

    @ApiOperation("点赞")
    @PostMapping("add")
    public boolean addLike(@RequestBody @Validated LikeParams params) {
        boolean flag = likeService.checkLike(params);
        if (flag) {
            throw new BusinessException(ResCode.BAD_REQUEST, "已经点赞过了,不能重复点赞");
        }
        return likeService.addLike(params);
    }

    @ApiOperation("取消点赞")
    @PostMapping("cancel")
    public boolean cancelLike(@RequestBody @Validated LikeParams params) {
        boolean flag = likeService.checkLike(params);
        if (!flag) {
            throw new BusinessException(ResCode.BAD_REQUEST, "没有点赞过，无法取消点赞");
        }
        return likeService.cancelLike(params);
    }
}
