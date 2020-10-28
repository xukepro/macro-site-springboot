package com.xuke.macrosite;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.repository.FriendChatRepository;
import com.xuke.macrosite.common.mongodb.service.FriendChatService;
import com.xuke.macrosite.dao.UserFriendDao;
import com.xuke.macrosite.pojo.vo.AddFriendParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/10/23
 */
@SpringBootTest
public class UserFriendTest {

    @Resource
    private UserFriendDao userFriendDao;

    @Resource
    private FriendChatRepository friendChatRepository;

    @Resource
    private FriendChatService friendChatService;

    @Test
    public void test() {
        AddFriendParams addFriendParams = new AddFriendParams();
        addFriendParams.setUid(1);
        addFriendParams.setFid(2);
        System.out.println(userFriendDao.add(addFriendParams));
    }

    @Test
    public void test2() {
        System.out.println(friendChatService.updateRead(2, 1));
    }

    @Test
    public void test3() {
        Pageable pageable = PageRequest.of(0, 10);
        List<FriendChatDocument> moreChatMsg = friendChatService.getMoreChatMsg(1, 2, 1603636372782L, pageable);
        for (FriendChatDocument chatDocument : moreChatMsg) {
            System.out.println(chatDocument);
        }
    }
}
