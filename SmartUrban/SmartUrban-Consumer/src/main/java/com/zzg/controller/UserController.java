package com.zzg.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzg.model.User;
import com.zzg.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/hello")
    public String get() {
        List<User> list = userService.selectAll();
        return "Hello Spring Boot!";
    }
}
