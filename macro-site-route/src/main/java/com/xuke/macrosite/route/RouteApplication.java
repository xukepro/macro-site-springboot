package com.xuke.macrosite.route;

import com.xuke.macrosite.route.service.CacheService;
import com.xuke.macrosite.route.zk.ZKListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.Resource;

@SpringBootApplication(
        scanBasePackages = {"com.xuke.macrosite"},
        exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class}
)
public class RouteApplication implements CommandLineRunner {

    @Resource
    private ZKListener zkListener;

    @Resource
    private CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(RouteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 启动zk监听器
        new Thread(zkListener, "zookeeper-listener").start();
        // 从数据库加载数据到redis
        cacheService.loadData();
    }
}
