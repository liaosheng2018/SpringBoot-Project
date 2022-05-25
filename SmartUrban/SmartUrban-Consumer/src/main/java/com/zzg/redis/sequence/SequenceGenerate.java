package com.zzg.redis.sequence;

import cn.hutool.core.util.StrUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/***
 * 基于Redis RedisAtomicLong 原子自增长属性和RedissonClient 分布式锁
 */
@Component
public class SequenceGenerate {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 基于redis 自增长属性实现序号生成
     * @param prefix
     * @param key
     * @param length
     * @return
     */
    public String getSequenceNumber(String prefix, String key, int length){
        String sequenceNumber ="";

        String formatToday = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // 获取当天23:59:59的毫秒数
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        // 计算Key 存活时间
        long survivalTime = (calendar.getTimeInMillis() - System.currentTimeMillis()) /1000;
        // 计算增量值
        long increment =getIncre(key, survivalTime);
        // 字符串补0 操作
        String sequence = StrUtil.padPre(String.valueOf(increment),length, "0");

        return StrUtil.join("", Arrays.asList(prefix, formatToday, sequence));
    }

    private Long getIncre(String key, long survivalTime){
        RedisAtomicLong counter = null;
        RLock lock = null;
        if(!redisTemplate.hasKey(key)){
            try {
                lock = redissonClient.getLock(key);
                lock.lock(10, TimeUnit.SECONDS); //设置锁超时时间，防止异常造成死锁
                counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
            } finally {
                lock.unlock();
            }

        } else {
            counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        }
        // 设置过期时间
        counter.expire(survivalTime, TimeUnit.SECONDS);
        Long increment = counter.incrementAndGet();

        // 判断是否初始化
        boolean target =(increment == null || increment.longValue() ==0);
        if(target){
            // 赋值初始值
            counter.set(1);
            increment = 1L;

        }

        return increment;
    }
}
