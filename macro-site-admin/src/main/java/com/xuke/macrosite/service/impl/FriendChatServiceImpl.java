package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.repository.FriendChatRepository;
import com.xuke.macrosite.common.mongodb.service.FriendChatService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/10/14
 */
@Service
@Component
public class FriendChatServiceImpl implements FriendChatService {
    @Resource
    private FriendChatRepository friendChatRepository;

    @Override
    public Integer countByUidAndFidAndRead(Integer uid, Integer fid, Boolean read) {
        return friendChatRepository.countByUidAndFidAndRead(uid, fid, read);
    }

    @Override
    public List<FriendChatDocument> findByUidAndFidOrderByCreatedAtDesc(Integer uid, Integer fid, Pageable pageable) {
        return friendChatRepository.findByUidAndFidOrderByCreatedAtDesc(uid, fid, pageable);
    }

    @Override
    public FriendChatDocument save(FriendChatDocument friendChatDocument) {
        return friendChatRepository.save(friendChatDocument);
    }

    @Override
    public Integer deleteByUidAndFid(Integer uid, Integer fid) {
        return friendChatRepository.deleteByUidAndFid(uid, fid);
    }

    @Override
    public Integer updateRead(Integer uid, Integer fid) {
        List<FriendChatDocument> UnReadDocument = friendChatRepository.findByUidAndFidAndRead(uid, fid, false);
        for (FriendChatDocument chatDocument : UnReadDocument) {
            chatDocument.setRead(true);
            friendChatRepository.save(chatDocument);
        }
        return UnReadDocument.size();
    }

    @Override
    public List<FriendChatDocument> getMoreChatMsg(Integer uid, Integer fid, long createdAt, Pageable pageable) {
        return friendChatRepository.findMore(uid, fid, createdAt, pageable);
    }
}
