package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.ArticleContentVO;
import com.xuke.macrosite.pojo.vo.ArticleListVO;
import com.xuke.macrosite.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
@Api(tags = "ArticleController", description = "文章")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 支持模糊查询
     */
    @ApiOperation(value = "获得文章列表")
    @GetMapping("/list")
    public ResResult<List<ArticleListVO>> getArticleList(@RequestParam(name = "uid", required = false) Integer uid, @RequestParam(name = "key", required = false) String key) {
        return ResResult.success(articleService.getArticleList(uid, key));
    }

    @ApiOperation(value = "获得一篇文章的内容")
    @GetMapping("/content")
    public ResResult<ArticleContentVO> getArticleContent(@RequestParam("aid") Integer aid) {
        return ResResult.success(articleService.getArticleContent(aid));
    }

    @ApiOperation(value = "获得自己收藏的文章")
    @GetMapping("/collect")
    public ResResult<List<CollectArticle>> getMyCollectArticle(@RequestParam("uid") Integer uid) {
        return ResResult.success(articleService.getMyCollectArticle(uid));
    }

    @ApiOperation(value = "添加文章")
    @PostMapping("/")
    public ResResult<Article> insert(@RequestBody Article article){
        return ResResult.success(articleService.insert(article));
    }

    @ApiOperation(value = "修改文章")
    @PutMapping("/")
    public ResResult<ArticleContentVO> update(@RequestBody Article article){
        return ResResult.success(articleService.update(article));
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("{id}")
    public ResResult<Boolean> delete(@PathVariable Integer id){
        return ResResult.success(articleService.deleteById(id));
    }

    @ApiOperation(value = "增加一次浏览量")
    @PostMapping("/addpv")
    public ResResult<Boolean> addPageViews(@RequestBody Integer id){
        return ResResult.success(articleService.addPageViews(id));
    }
}
