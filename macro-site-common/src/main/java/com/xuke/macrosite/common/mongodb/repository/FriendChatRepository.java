package com.xuke.macrosite.common.mongodb.repository;

import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by xuke on 2020/9/28
 */
public interface FriendChatRepository extends MongoRepository<FriendChatDocument, String> {

    /**
     * 通过uid和fid查询，createAt倒叙，
     * @param uid 用户id
     * @param fid 好友id
     * @param pageable 分页
     * @return 聊天文档列表
     */
    @Query(
            value = "{'$or':[{'uid': ?0, 'fid': ?1},{'uid': ?1, 'fid': ?0}]}",
            sort = "{'createdAt': -1}"
    )
    List<FriendChatDocument> findByUidAndFidOrderByCreatedAtDesc(Integer uid, Integer fid, Pageable pageable);

    /**
     * 计算用户未读消息数量
     * @param uid 要用id
     * @param fid 好友id
     * @param read 是否已读
     * @return 未读消息数量
     */
    Integer countByUidAndFidAndRead(Integer uid, Integer fid, Boolean read);

    Integer deleteByUidAndFid(Integer uid, Integer fid);

    List<FriendChatDocument> findByUidAndFidAndRead(Integer uid, Integer fid, Boolean read);

    @Query(
            value = "{'$and': [{'createdAt': {'$lt': ?2}}, {'$or':[{'uid': ?0, 'fid': ?1},{'uid': ?1, 'fid': ?0}]}]}",
            sort = "{'createdAt': -1}"
    )
    List<FriendChatDocument> findMore(Integer uid, Integer fid, long createdAt, Pageable pageable);
}
