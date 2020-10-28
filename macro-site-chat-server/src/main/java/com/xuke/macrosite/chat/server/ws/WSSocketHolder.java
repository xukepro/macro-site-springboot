package com.xuke.macrosite.chat.server.ws;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 操作 在线用户的 Channel
 */
public class WSSocketHolder {

    private static final Map<Integer, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

    /**
     * 保存用户和channel关系
     */
    public static void put(Integer id, Channel channel) {
        CHANNEL_MAP.put(id, channel);
    }

    public static Channel get(Integer id) {
        return CHANNEL_MAP.get(id);
    }

    public static Map<Integer, Channel> getMAP() {
        return CHANNEL_MAP;
    }

    public static void remove(Channel channel) {
        CHANNEL_MAP.entrySet().stream().filter(entry -> entry.getValue() == channel).forEach(entry -> CHANNEL_MAP.remove(entry.getKey()));
    }

    public static Integer getUid(Channel channel) {
        for (Map.Entry<Integer, Channel> entry : CHANNEL_MAP.entrySet()) {
            if (entry.getValue() == channel) {
                return entry.getKey();
            }
        }
        return null;
    }

}
