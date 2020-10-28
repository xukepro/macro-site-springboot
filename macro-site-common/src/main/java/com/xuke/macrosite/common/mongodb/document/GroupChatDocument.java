package com.xuke.macrosite.common.mongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by xuke on 2020/9/28
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupChatDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed
    private Integer uid; // 发送者id

    @Indexed
    private Integer gid; // 接收群id

    private Integer type; // 信息类型

    private String content; // 信息内容

    private Map<Integer, Boolean> userReadMap; // 群组用户是否已读

    private Long createdAt;

}
