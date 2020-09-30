package com.xuke.macrosite.mongodb.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

/**
 * Created by xuke on 2020/9/28
 */
@Document
@Data
public class GroupChatDocument {
    @Id
    private String id;
    @Indexed
    private Integer groupId; // 群id
    @Indexed
    private Integer uid; // 发送者id
    private Integer msgType;
    private String msgContent;
    private Date createdAt;
}
