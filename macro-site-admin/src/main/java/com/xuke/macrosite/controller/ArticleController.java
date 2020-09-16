package com.xuke.macrosite.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import com.xuke.macrosite.common.api.ResPage;
import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.pojo.dto.ArticleDetail;
import com.xuke.macrosite.pojo.dto.CollectArticle;
import com.xuke.macrosite.pojo.vo.AddArticleVO;
import com.xuke.macrosite.pojo.vo.ArticleContentVO;
import com.xuke.macrosite.pojo.vo.ArticleListVO;
import com.xuke.macrosite.pojo.vo.UpdateArticleVO;
import com.xuke.macrosite.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * @return
     */
    @ApiOperation(value = "获得文章列表")
    @GetMapping("list")
    public ResResult<ResPage<ArticleDetail>> getArticleList(
            @RequestParam(name = "uid", required = false) Integer uid, // 根据用户查询
            @RequestParam(name = "key", required = false) String key,  // 模糊查询关键词
            @RequestParam(name = "cid", required = false) Integer cid,  // 根据分类查询
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        List<ArticleDetail> list = articleService.getArticleList(uid, key, cid, pageSize, pageNum);
        PageInfo<ArticleDetail> page = new PageInfo(list);

        List<ArticleListVO> articleListVOList = new ArrayList<>();
        for (ArticleDetail articleDetail : list) {
            ArticleListVO articleListVO = new ArticleListVO();
            BeanUtil.copyProperties(articleDetail, articleListVO);
            articleListVOList.add(articleListVO);
        }
        return ResResult.success(ResPage.restPage(list, page));
    }

    @ApiOperation(value = "获得一篇文章的内容")
    @GetMapping("content/{aid}")
    public ResResult<ArticleContentVO> getArticleContent(@PathVariable("aid") Integer aid) {
        return ResResult.success(articleService.getArticleContent(aid));
    }

    @ApiOperation(value = "获得自己收藏的文章")
    @GetMapping("collect")
    public ResResult<List<CollectArticle>> getMyCollectArticle(@RequestParam("uid") Integer uid) {
        return ResResult.success(articleService.getMyCollectArticle(uid));
    }

    @ApiOperation(value = "添加文章")
    @PostMapping
    public ResResult<Article> insert(@RequestBody AddArticleVO article){
        return ResResult.success(articleService.insert(article));
    }

    @ApiOperation(value = "修改文章")
    @PutMapping
    public ResResult<ArticleContentVO> update(@RequestBody @Validated UpdateArticleVO article){
        return ResResult.success(articleService.update(article));
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping("{id}")
    public ResResult<Boolean> delete(@PathVariable Integer id){
        return ResResult.success(articleService.deleteById(id));
    }

    @ApiOperation(value = "增加一次浏览量")
    @PostMapping("addpv")
    public ResResult<Boolean> addPageViews(@RequestBody Integer id){
        return ResResult.success(articleService.addPageViews(id));
    }
}
