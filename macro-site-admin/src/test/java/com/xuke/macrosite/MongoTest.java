package com.xuke.macrosite;

import com.xuke.macrosite.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.mongodb.repository.FriendChatRepository;
import com.xuke.macrosite.pojo.dto.UserFriendDetail;
import com.xuke.macrosite.service.UserFriendService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/28
 */
@SpringBootTest
public class MongoTest {
    @Resource
    private FriendChatRepository repository;
    @Resource
    private UserFriendService userFriendService;

    @Test
    public void test() {
//        FriendChatDocument chatMsg = new FriendChatDocument();
//        chatMsg.setUid(1);
//        chatMsg.setFid(2);
//        chatMsg.setMsgType(1);
//        chatMsg.setMsgContent("asdfasd");
//        chatMsg.setState(0);
//        chatMsg.setCreatedAt(new Date());
//        repository.save(chatMsg);

//        Pageable pageable = PageRequest.of(0, 10);
//        List<FriendChatDocument> list = repository.findByUidAndFidOrderByCreatedAtDesc(1, 2, pageable);
//        for (FriendChatDocument friendChatDocument : list) {
//            System.out.println(friendChatDocument);
//        }

        System.out.println(repository.countByUidAndFidAndState(1, 2, 0));
    }

    @Test
    public void test1() {
        List<UserFriendDetail> list = userFriendService.getUserFriend(1);
        for (UserFriendDetail friendDetail : list) {
            System.out.println(friendDetail);
        }
    }
}
