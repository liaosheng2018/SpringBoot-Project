package com.zzg.controller;

import com.zzg.mapper.UserMapper;
import com.zzg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FirstController {
    @Autowired
    private UserMapper mapper;

    @RequestMapping("/hello")
    public String get() {
        List<User> list = mapper.selectAll();
        return "Hello Spring Boot!";
    }
}
