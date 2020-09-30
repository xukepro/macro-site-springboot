package com.xuke.macrosite.mongodb.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * Created by xuke on 2020/9/28
 */
@Document
@Data
public class FriendChatDocument {
    @Id
    private String id;
    @Indexed
    private Integer uid; // 发送者id
    @Indexed
    private Integer fid; // 接收者id
    private Integer msgType;
    private String msgContent;
    private Integer state; // 聊天消息是否被阅读 0：未读，1：已读
    private Date createdAt;
}
