package com.zzg.controller;

import com.zzg.mapper.UserMapper;
import com.zzg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper mapper;

    @RequestMapping("/list")
    public List<User> get() {
        List<User> list = mapper.selectAll();
        return list;
    }
}
