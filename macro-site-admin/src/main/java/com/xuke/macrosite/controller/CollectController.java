package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.CollectParams;
import com.xuke.macrosite.service.CollectService;
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
    public ResResult<Boolean> addCollect(@RequestBody CollectParams collectParams) {
        return ResResult.success(collectService.addCollect(collectParams));
    }

    @PostMapping("cancel")
    public ResResult<Boolean> cancelCollect(@RequestBody CollectParams collectParams) {
        return ResResult.success(collectService.cancelCollect(collectParams));
    }
}
