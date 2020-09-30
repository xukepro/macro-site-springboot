package com.xuke.macrosite.constant;

import lombok.Data;
import lombok.Getter;

/**
 * Created by xuke on 2020/9/27
 */
@Getter
public class ChatMsgType {
    public static final int LOGIN_OUT = -2; // 登录异常
    public static final int WS_OUT = -1; // 异地登录
    public static final int PING = 0; // 心跳
    public static final int LOGIN = 1; // 登录
    public static final int FRIEND = 2; // 好友消息
    public static final int GROUP = 3; // 群消息
    public static final int FRIEND_ASK = 4; // 请求加好友
    public static final int FRIEND_ACK = 5; // 好友同意
    public static final int GROUP_ASK = 6; // 请求入群
    public static final int GROUP_ACK = 7; // 同意入群
}
