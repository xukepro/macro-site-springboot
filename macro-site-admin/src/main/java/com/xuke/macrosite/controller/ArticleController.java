package com.xuke.macrosite.controller;

import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.AddArticleParams;
import com.xuke.macrosite.pojo.dto.ArticleContent;
import com.xuke.macrosite.pojo.dto.ArticleList;
import com.xuke.macrosite.pojo.vo.UpdateArticleParams;
import com.xuke.macrosite.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/11
 */
@Api(tags = "ArticleController")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 支持模糊查询
     * @return
     */
    @ApiOperation(value = "获得文章列表")
    @GetMapping("list")
    public List<ArticleList> getArticleList(
//            @RequestParam @Validated GetArticleParams params
            @RequestParam(name = "uid", required = false) Integer uid, // 根据用户查询
            @RequestParam(name = "key", required = false) String key,  // 模糊查询关键词
            @RequestParam(name = "cid", required = false) Integer cid,  // 根据分类查询
            @RequestParam(name = "tags", required = false) List<String> tags,  // 根据tags查询
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
//        return ResResult.success(ResPage.restPage(articleService.getArticleList(uid, key, cid, tags, pageSize, pageNum)));
//        PageHelper.startPage(pageNum, pageSize); 遍历会造成total失效为当前页大小的问题
        return articleService.getArticleList(uid, key, cid, tags, pageSize, pageNum);
    }

    @ApiOperation(value = "获得一篇文章的内容")
    @GetMapping("content/{aid}")
    public ArticleContent getArticleContent(@PathVariable("aid") Integer aid) {
        return articleService.getArticleContent(aid);
    }

    @ApiOperation(value = "获得自己收藏的文章")
    @GetMapping("collect")
    public List<CollectArticle> getMyCollectArticle(@RequestParam("uid") Integer uid) {
        return articleService.getMyCollectArticle(uid);
    }

    @ApiOperation(value = "添加文章")
    @PostMapping
    public ArticleContent insert(@RequestBody @Validated AddArticleParams article){
        return articleService.insert(article);
    }

    @ApiOperation(value = "修改文章")
    @PutMapping
    public ArticleContent update(
            @RequestBody
            @Validated
                    UpdateArticleParams article){
        System.out.println(article);
        return articleService.update(article);
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("{id}")
    public boolean delete(@PathVariable Integer id){
        return articleService.deleteById(id);
    }

    @ApiOperation(value = "增加一次浏览量")
    @PostMapping("addpv")
    public boolean addPageViews(@RequestBody Integer aid){
        return articleService.addPageViews(aid);
    }
}
