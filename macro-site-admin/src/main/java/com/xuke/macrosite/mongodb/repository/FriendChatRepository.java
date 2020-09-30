package com.xuke.macrosite.mongodb.repository;

import com.xuke.macrosite.mongodb.document.FriendChatDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by xuke on 2020/9/28
 */
public interface FriendChatRepository extends MongoRepository<FriendChatDocument, String> {
    @Query(
            value = "{'$or':[{'uid': ?0, 'fid': ?1},{'uid': ?1, 'fid': ?0}]}",
            sort = "{'createdAt': -1}"
    )
    List<FriendChatDocument> findByUidAndFidOrderByCreatedAtDesc(Integer uid, Integer fid, Pageable pageable);

    Integer countByUidAndFidAndState(Integer uid, Integer fid, Integer state);
}
