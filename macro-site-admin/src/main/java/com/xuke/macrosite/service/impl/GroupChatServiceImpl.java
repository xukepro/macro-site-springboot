package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.common.mongodb.repository.GroupChatRepository;
import com.xuke.macrosite.common.mongodb.service.GroupChatService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/10/22
 */
@Service
@Component
public class GroupChatServiceImpl implements GroupChatService {
    @Resource
    private GroupChatRepository groupChatRepository;

    @Override
    public List<GroupChatDocument> findByGidOrderByCreatedAtDesc(Integer gid, Pageable pageable) {
        return groupChatRepository.findByGidOrderByCreatedAtDesc(gid, pageable);
    }

    @Override
    public Integer countByGidAndUidAndRead(Integer gid, Integer uid, Boolean read) {
        return groupChatRepository.countByGidAndUidAndRead(gid, uid, read);
    }

    @Override
    public GroupChatDocument save(GroupChatDocument groupChatDocument) {
        return groupChatRepository.save(groupChatDocument);
    }

    @Override
    public Integer deleteByGid(Integer gid) {
        return groupChatRepository.deleteByGid(gid);
    }

    @Override
    public Integer updateRead(Integer gid, Integer uid) {
        List<GroupChatDocument> unReadDocument = groupChatRepository.finUnReadDocument(gid, uid);
        for (GroupChatDocument document : unReadDocument) {
            document.getUserReadMap().put(uid, true);
            groupChatRepository.save(document);
        }
        return unReadDocument.size();
    }

    @Override
    public List<GroupChatDocument> getMoreChatMsg(Integer gid, long createdAt, Pageable pageable) {
        return groupChatRepository.findMore(gid, createdAt, pageable);
    }
}
