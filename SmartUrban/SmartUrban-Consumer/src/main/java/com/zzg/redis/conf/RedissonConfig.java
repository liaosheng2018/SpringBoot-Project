package com.zzg.redis.conf;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redission 分布式锁对象实例化
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String pwd;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        SingleServerConfig singleServerConfig = config.useSingleServer();
        //连接可以使用"rediss://"来启用SSL连接
        singleServerConfig.setAddress("redis://"+host+":" + port);
        if(StrUtil.isNotEmpty(pwd)){
            singleServerConfig.setPassword(pwd);
        }
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

}
