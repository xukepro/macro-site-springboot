package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.CollectParams;
import com.xuke.macrosite.pojo.dto.LikeParams;
import com.xuke.macrosite.service.CollectService;
import com.xuke.macrosite.service.LikeService;
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
    private LikeService collectService;

    @PostMapping("add")
    public ResResult<Boolean> addCollect(@RequestBody LikeParams params) {
        return ResResult.success(collectService.addCollect(params));
    }

    @PostMapping("cancel")
    public ResResult<Boolean> cancelCollect(@RequestBody LikeParams params) {
        return ResResult.success(collectService.cancelCollect(params));
    }
}
