package com.xuke.macrosite.common.mongodb.service;

import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by xuke on 2020/10/22
 */
public interface GroupChatService {

    List<GroupChatDocument> findByGidOrderByCreatedAtDesc(Integer gid, Pageable pageable);

    Integer countByGidAndUidAndRead(Integer gid, Integer uid, Boolean read);

    GroupChatDocument save(GroupChatDocument groupChatDocument);

    Integer deleteByGid(Integer gid);

    Integer updateRead(Integer gid, Integer uid);

    List<GroupChatDocument> getMoreChatMsg(Integer gid, long createdAt, Pageable pageable);
}
