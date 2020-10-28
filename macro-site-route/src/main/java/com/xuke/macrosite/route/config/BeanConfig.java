package com.xuke.macrosite.route.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xuke.macrosite.common.route.algorithm.RouteHandle;
import com.xuke.macrosite.common.route.algorithm.consistenthash.AbstractConsistentHash;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuke on 2020/10/15
 */
@Configuration
@Slf4j
public class BeanConfig {

    @Resource
    private AppConfiguration app;

    /**
     * 向容器注入连接过后的zkClient
     *
     * @return
     */
    @Bean
    public ZkClient buildZkClient() {
        String zkAddr = app.getZkAddr();
        int zkConnectTimeout = app.getZkConnectTimeout();
        log.info("连接zookeeper，addr: " + zkAddr + "，超时时间: " + zkConnectTimeout);
        return new ZkClient(zkAddr, zkConnectTimeout);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // json 序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // string 序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用string的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);

        // value采用Jackson序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * http client
     *
     * @return okHttp
     */
    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }

    /**
     * 创建一个LoadingCache注入容器
     *
     * @return
     */
    @Bean
    public LoadingCache<String, String> buildCache() {
        return CacheBuilder.newBuilder()
                .maximumSize(10000)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        /* 从sql或者nosql获取数据 */
                        return null;
                    }
                });
    }

    /**
     * 创建路由分发策略
     *
     * @return
     * @throws Exception
     */
    @Bean
    public RouteHandle buildRouteHandle() throws Exception {
        String routeWay = app.getRouteWay();
        RouteHandle routeHandle = (RouteHandle) Class.forName(routeWay).newInstance();
        log.info("Current route algorithm is [{}]", routeHandle.getClass().getSimpleName());
        if (routeWay.contains("ConsistentHash")) {
            //一致性 hash 算法
            Method method = Class.forName(routeWay).getMethod("setHash", AbstractConsistentHash.class);
            AbstractConsistentHash consistentHash = (AbstractConsistentHash)
                    Class.forName(app.getConsistentHashWay()).newInstance();
            method.invoke(routeHandle, consistentHash);
            return routeHandle;
        } else {

            return routeHandle;

        }
    }
}
