package com.xuke.macrosite;

import com.xuke.macrosite.dao.UserDao;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.dto.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
@SpringBootTest
public class UserTest {
    @Resource
    private UserDao userDao;

    @Test
    public void getMyUserInfo() {
        List<UserDetail> myUserInfo = userDao.getUserInfo(1);
        myUserInfo.forEach(System.out::println);
    }

    @Test
    public void getAllUserInfo() {
        List<UserDetail> list = userDao.getAllUserInfo();
        list.forEach(System.out::println);
    }

    @Test
    public void getLoginInfo() {
        List<LoginInfo> list = userDao.getLoginInfo("xuke");
        System.out.println(list);
    }
}
