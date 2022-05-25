package com.zzg.redis.condition;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.resource.ClassPathResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * RedisCacheUtil 开关配置对象
 */
public class RedisCacheUtilCondition implements Condition {
    private static final Logger logger = LoggerFactory.getLogger(RedisCacheUtilCondition.class);

    //默认读取资源
    public static final String APPLICATION_PROPERTIES ="application.properties";
    //读取RedisCacheUtil开关配置值
    public static final String REDIS_FLAG ="redis.flag";

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ClassPathResource resource = new ClassPathResource(APPLICATION_PROPERTIES);
        Properties properties = new Properties();
        try {
            properties.load(resource.getStream());
        } catch (IOException e) {
           logger.error("读取配置文件异常:",e.getMessage(), e);
           return false;
        }
        return Optional.ofNullable(Convert.convert(Boolean.class, properties.getProperty(REDIS_FLAG))).orElse(false);
    }
}
