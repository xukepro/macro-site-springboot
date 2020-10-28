package com.xuke.macrosite.common.mongodb.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuke on 2020/9/28
 */
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendChatDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed
    private Integer uid; // 发送者id

    @Indexed
    private Integer fid; // 接收者id

    private Integer type; // 信息类型

    private String content; // 信息内容

    private Boolean read; // 聊天消息是否被阅读 true or false

    private long createdAt;
}
