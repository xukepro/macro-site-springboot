package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.TagDetail;
import com.xuke.macrosite.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/20
 */
@RestController
@RequestMapping("tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("all")
    ResResult<List<TagDetail>> getTagDetail() {
        return ResResult.success(tagService.getTagDetail());
    }
}
