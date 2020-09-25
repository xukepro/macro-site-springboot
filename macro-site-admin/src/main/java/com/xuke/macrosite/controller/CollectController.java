package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.vo.CollectParams;
import com.xuke.macrosite.service.CollectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@RestController
@RequestMapping("collect")
public class CollectController {
    @Resource
    private CollectService collectService;

    @ApiOperation("收藏")
    @PostMapping("add")
    public ResResult<Boolean> addCollect(@RequestBody @Validated CollectParams params) {
        boolean flag = collectService.checkCollect(params);
        if (flag) return ResResult.badRequest("已经收藏过了,不能重复收藏");
        return ResResult.success(collectService.addCollect(params));
    }

    @ApiOperation("取消收藏")
    @PostMapping("cancel")
    public ResResult<Boolean> cancelCollect(@RequestBody @Validated CollectParams params) {
        boolean flag = collectService.checkCollect(params);
        if (!flag) return ResResult.badRequest("没有收藏过，无法取消收藏");
        return ResResult.success(collectService.cancelCollect(params));
    }
}
