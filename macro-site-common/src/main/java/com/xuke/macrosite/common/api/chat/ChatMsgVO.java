package com.xuke.macrosite.common.api.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuke on 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMsgVO implements Serializable {

    private static final long serialVersionUID = 8640716606408451226L;

    /* 消息类型 */
    @NotNull
    private Integer type;

    /* 发送者id */
    @NotNull
    private Integer uid;

    /* 发送内容信息 */
    @NotNull
    private ChatContentVO chatContent;

    @NotNull
    private Date sendTime;
}
