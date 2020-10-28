package com.xuke.macrosite;

import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.dao.GroupDao;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.vo.UserGroupVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/10/20
 */
@SpringBootTest
public class GroupUserTest {
    @Resource
    private GroupDao groupDao;

    @Test
    public void test() {
//        List<GroupDetail> groupUserDetail = groupDao.getGroupUserDetail();
//        for (GroupDetail userDetail : groupUserDetail) {
//            System.out.println(userDetail);
//        }
        List<UserGroupVO> userGroupVOByUid = groupDao.getUserGroupVOByUid(1);
        for (UserGroupVO userGroupVO : userGroupVOByUid) {
            System.out.println(userGroupVO);
        }
    }

    @Test
    public void test2() {
        System.out.println(groupDao.isGroupNameExisted("测试群"));
    }
}
