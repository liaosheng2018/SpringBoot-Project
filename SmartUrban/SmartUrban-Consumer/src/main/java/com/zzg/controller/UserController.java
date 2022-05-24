package com.zzg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzg.model.SysUser;
import com.zzg.redis.inter.impl.RedisCacheUtil;
import com.zzg.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Reference
    private SysUserService sysUserService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @RequestMapping("/hello")
    public String get() {
        SysUser obj = sysUserService.selectById(1);
        System.err.println(obj.getId());
        boolean target = redisCacheUtil.set(String.valueOf(obj.getId()), obj);
        System.out.println("是否添加成功:" + target);
        return "Hello Spring Boot!";
    }
}
