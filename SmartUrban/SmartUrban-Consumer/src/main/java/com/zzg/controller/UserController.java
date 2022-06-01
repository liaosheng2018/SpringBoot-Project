package com.zzg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzg.model.SysUser;
import com.zzg.redis.inter.impl.RedisCacheUtil;
import com.zzg.quartz.service.SysUserService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class UserController {
    @Reference
    private SysUserService sysUserService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    private RedissonClient redissonClient;

    // 定义常量锁对象
    public static final String APPLICATION_LOCK = "APPLICATION_LOCK";

    @RequestMapping("/hello")
    @ResponseBody
    public Object  get() {
        RLock rlock = null;
        try{
            rlock = redissonClient.getLock(APPLICATION_LOCK);
            rlock.lock(10, TimeUnit.SECONDS); //设置锁超时时间，防止异常造成死锁
            // 业务逻辑处理
            SysUser obj = sysUserService.selectById(1);
            System.err.println(obj.getId());
            boolean target = redisCacheUtil.set(String.valueOf(obj.getId()), obj);
            System.out.println("是否添加成功:" + target);
        }finally {
            rlock.unlock();
        }
        return "Hello Spring Boot!";
    }

}
