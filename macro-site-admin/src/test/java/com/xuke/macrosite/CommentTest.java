//package com.xuke.macrosite;
//
//import com.xuke.macrosite.dao.CommentDao;
//import com.xuke.macrosite.pojo.dto.CommentDetail;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by xuke on 2020/9/13
// */
//@SpringBootTest
//public class CommentTest {
//    @Resource
//    private CommentDao commentDao;
//
//    @Test
//    public void getComment() {
//        List<CommentDetail> comment = commentDao.getCommentByArticle(1);
//        comment.forEach(System.out::println);
//    }
//
//    @Test
//    public void getAllComment() {
//        List<CommentDetail> comment = commentDao.getAllComment();
//        comment.forEach(System.out::println);
//    }
//
//    @Test
//    public void getPublishedComment() {
//        List<CommentDetail> comment = commentDao.getPublishedComment(2);
//        comment.forEach(System.out::println);
//    }
//
//    @Test
//    public void getReceivedComment() {
//        List<CommentDetail> comment = commentDao.getReceivedComment(1);
//        comment.forEach(System.out::println);
//    }
//}
