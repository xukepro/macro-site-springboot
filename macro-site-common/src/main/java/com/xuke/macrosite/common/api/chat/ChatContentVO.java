package com.xuke.macrosite.common.api.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by xuke on 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatContentVO implements Serializable {

    private static final long serialVersionUID = 2080203797831326086L;

    /* 接收群id */
    private Integer gid;

    /*
    * 接收者id, 当发送给群时, 同时存在gid和rid（rid用于确定服务器向谁推送消息）,
    * 当发给好友时，只有rid,
    * 当发送好友申请时，只有rid,
    * 当发送入群申请时，有gid和rid, rid用来确定向群主发消息
    * */
    private Integer rid;

    /* 聊天内容类型 */
    private Integer type;

    /* 聊天内容 */
    private String content;
}
