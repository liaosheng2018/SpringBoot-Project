package com.zzg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzg.model.SysUser;
import com.zzg.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Reference
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String get() {
        SysUser obj = sysUserService.selectById(1);
        System.err.println(obj.getId());
        return "Hello Spring Boot!";
    }
}
