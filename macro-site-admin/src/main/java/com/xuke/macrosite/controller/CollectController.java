package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.CollectParams;
import com.xuke.macrosite.service.CollectService;
import org.springframework.data.repository.query.Param;
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

    @PostMapping("add")
    public ResResult<Boolean> addCollect(@RequestBody CollectParams params) {
        boolean flag = collectService.checkCollect(params);
        if (flag) return ResResult.badRequest("已经收藏过了,不能重复收藏");
        return ResResult.success(collectService.addCollect(params));
    }

    @PostMapping("cancel")
    public ResResult<Boolean> cancelCollect(@RequestBody CollectParams params) {
        boolean flag = collectService.checkCollect(params);
        if (!flag) return ResResult.badRequest("没有收藏过，无法取消收藏");
        return ResResult.success(collectService.cancelCollect(params));
    }
}
