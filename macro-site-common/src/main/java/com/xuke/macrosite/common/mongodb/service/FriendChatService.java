package com.xuke.macrosite.common.mongodb.service;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by xuke on 2020/10/14
 */
public interface FriendChatService {
    Integer countByUidAndFidAndRead(Integer uid, Integer fid, Boolean read);

    List<FriendChatDocument> findByUidAndFidOrderByCreatedAtDesc(Integer uid, Integer fid, Pageable pageable);

    FriendChatDocument save(FriendChatDocument friendChatDocument);

    Integer deleteByUidAndFid(Integer uid, Integer fid);

    Integer updateRead(Integer uid, Integer fid);

    List<FriendChatDocument> getMoreChatMsg(Integer uid, Integer fid, long createdAt, Pageable pageable);
}
