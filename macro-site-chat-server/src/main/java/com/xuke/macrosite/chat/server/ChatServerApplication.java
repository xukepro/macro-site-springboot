package com.xuke.macrosite.chat.server;

import com.xuke.macrosite.chat.server.config.AppConfiguration;
import com.xuke.macrosite.chat.server.zk.RegistryZK;
import com.xuke.macrosite.common.util.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.Resource;
import java.net.InetAddress;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class})
@Slf4j
public class ChatServerApplication implements CommandLineRunner {

    @Resource
    private AppConfiguration app;

    @Resource
    private RegistryZK registryZK;

    public static void main(String[] args) {
        SpringApplication.run(ChatServerApplication.class, args);
        log.info("chat server 启动成功");
    }

    @Override
    public void run(String... args) throws Exception {
        String address = NetUtil.getAddress();
        app.setIp(address);
        new Thread(registryZK, "registry-zk").start();
    }
}
