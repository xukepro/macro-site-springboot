package com.xuke.macrosite.chat.client;

import com.xuke.macrosite.chat.client.config.AppConfiguration;
import com.xuke.macrosite.chat.client.service.RouteService;
import com.xuke.macrosite.chat.client.ws.WSClient;
import com.xuke.macrosite.common.api.chat.ChatContentVO;
import com.xuke.macrosite.common.api.chat.ChatMsgVO;
import com.xuke.macrosite.common.constant.ChatContantType;
import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.protobuf.ChatContentResProto;
import com.xuke.macrosite.common.protobuf.ChatMsgResProto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
public class ChatClientApplication implements CommandLineRunner {

    @Resource
    private WSClient client;

    @Resource
    private AppConfiguration app;

    @Resource
    private RouteService routeService;

    public static void main(String[] args) {
        SpringApplication.run(ChatClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                // [f/g]:id:content
                // f:2:hello
                System.out.print("> ");
                String msg = sc.nextLine();
                if ("".equals(msg)) {
                    return;
                }
                String[] split = msg.split(":");
                String head = split[0];
                int rid = Integer.parseInt(split[1]);
                String content = split[2];

                ChatContentVO chatContentVO = ChatContentVO.builder()
                        .fid(rid)
                        .content(content)
                        .type(ChatContantType.TEXT)
                        .build();

                if ("f".equals(head)) {

                    ChatMsgVO chatMsgVO = ChatMsgVO.builder()
                            .type(ChatMsgType.FRIEND)
                            .uid(app.getUid())
                            .chatContent(chatContentVO)
                            .sendTime(new Date())
                            .build();
                    routeService.sendMsg(chatMsgVO);

                } else if ("g".equals(head)) {

                    ChatMsgVO chatMsgVO = ChatMsgVO.builder()
                            .type(ChatMsgType.GROUP)
                            .uid(app.getUid())
                            .chatContent(chatContentVO)
                            .sendTime(new Date())
                            .build();
                    routeService.sendMsg(chatMsgVO);

                }
            }
        }, "scanner-thread").start();
    }
}
