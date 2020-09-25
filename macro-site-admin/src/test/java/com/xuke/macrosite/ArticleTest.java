package com.xuke.macrosite;

import com.xuke.macrosite.dao.ArticleDao;
import com.xuke.macrosite.dao.CollectDao;
import com.xuke.macrosite.service.ArticleService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * Created by xuke on 2020/9/11
 */
@SpringBootTest
public class ArticleTest {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private CollectDao collectDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//    @Test
//    public void insert() {
//        Article article = new Article();
//        article.setUid(3);
//        article.setTitle("springboot实战");
//        article.setDescription("springboot实战");
//        article.setCid(1);
//        article.setContent("springbootspringbootspringbootspringbootspringbootspringbootspringboot");
//        Article insert = articleService.insert(article);
//        System.out.println(insert);
//    }
//
//    @Test
//    public void update() {
//        Article article = new Article();
//        article.setId(7);
//        article.setCid(2);
//        ArticleContent update = articleService.update(article);
//        System.out.println(update);
//    }
//
//    @Test
//    public void delete() {
//        System.out.println(articleService.deleteById(9));
//    }
//
//    @Test
//    public void getArticleList() {
//        List<ArticleList> list = articleService.getArticleList(null, null);
//        for (ArticleList a : list) {
//            System.out.println(a);
//        }
//    }
//
//    @Test
//    public void getArticleContent() {
//        ArticleContent articleContent = articleService.getArticleContent(10);
//        System.out.println(articleContent);
//    }
//
//    @Test
//    public void getCollectArticle() {
////        List<CollectArticle> list = collectDao.getMyCollectArticle(1);
////        list.forEach(System.out::println);
//    }
}
