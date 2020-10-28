package com.xuke.macrosite;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.common.mongodb.repository.FriendChatRepository;
import com.xuke.macrosite.common.mongodb.repository.GroupChatRepository;
import com.xuke.macrosite.pojo.vo.UserFriendVO;
import com.xuke.macrosite.service.FriendService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xuke on 2020/9/28
 */
@SpringBootTest
public class MongoTest {

    @Resource
    private FriendChatRepository repository;

    @Resource
    private GroupChatRepository groupChatRepository;

    @Resource
    private FriendService friendService;

    @Test
    public void test() {
        FriendChatDocument document = FriendChatDocument.builder()
                .uid(2)
                .fid(1)
                .type(1)
                .content("msg2")
                .read(true)
                .createdAt(new Date().getTime())
                .build();

        repository.save(document);
        System.out.println(repository.countByUidAndFidAndRead(1, 2, true));
    }

    @Test
    public void test1() {
        List<UserFriendVO> list = friendService.getUserFriend(1);
        for (UserFriendVO friendDetail : list) {
            System.out.println(friendDetail);
        }
    }

    @Test
    public void test2() {
        HashMap<Integer, Boolean> map = new HashMap<>();
        map.put(2, false);
        map.put(3, false);
        GroupChatDocument document = GroupChatDocument.builder()
                .uid(1)
                .gid(1)
                .type(1)
                .content("unread")
                .userReadMap(map)
                .createdAt(new Date().getTime())
                .build();

        groupChatRepository.save(document);
    }

    @Test
    public void test3() {
        System.out.println(groupChatRepository.countByGidAndUidAndRead(1, 2, false));
    }
}
