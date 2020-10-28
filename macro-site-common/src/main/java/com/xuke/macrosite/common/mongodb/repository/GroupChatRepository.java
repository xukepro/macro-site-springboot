package com.xuke.macrosite.common.mongodb.repository;

import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by xuke on 2020/10/22
 */
public interface GroupChatRepository extends MongoRepository<GroupChatDocument, String> {

    /**
     * 查询用户所在群聊天文档
     * @param gid 群组id
     * @param pageable 分组
     * @return 聊天文档
     */
    @Query(
            value = "{'gid': ?0}",
            sort = "{'createdAt': -1}"
    )
    List<GroupChatDocument> findByGidOrderByCreatedAtDesc(Integer gid, Pageable pageable);

    /**
     * 用户所在群未读消息数
     * @param gid 群组id
     * @param uid 用户id
     * @param read 是否已读
     * @return 未读消息数量
     */
    @Query(
            value = "{'gid': ?0, 'userReadMap.?1': ?2}",
            count = true
    )
    Integer countByGidAndUidAndRead(Integer gid, Integer uid, Boolean read);

    Integer deleteByGid(Integer gid);

    @Query(
            value = "{'gid': ?0, 'userReadMap.?1': false}"
    )
    List<GroupChatDocument> finUnReadDocument(Integer gid, Integer uid);

    @Query(
            value = "{'gid': ?0, 'createdAt': {'$lt': ?1}}",
            sort = "{'createdAt': -1}"
    )
    List<GroupChatDocument> findMore(Integer gid, long createdAt, Pageable pageable);
}