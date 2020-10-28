package com.xuke.macrosite.chat.client.thread;

/**
 * Created by xuke on 2020/10/24
 */
public class ContextHolder {

    private static final ThreadLocal<Boolean> IS_RECONNECT = new ThreadLocal<>();

    public static void setReconnect(boolean reconnect) {
        IS_RECONNECT.set(reconnect);
    }

    public static Boolean getReconnect() {
        return IS_RECONNECT.get();
    }

    public static void clear() {
        IS_RECONNECT.remove();
    }
}
