package com.zzg.controller;

import com.zzg.mapper.UserMapper;
import com.zzg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/user")
public class FirstController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/save")
    public String insert() {
        User user = new User();
        user.setNickname("zzg" + new Random().nextInt());
        user.setPassword("1234567");
        user.setSex(1);
        user.setBirthday("1991-12-20");
        userMapper.addUser(user);
        return "success";
    }

    @GetMapping("/listuser")
    public List<User> listuser() {
        return userMapper.findUsers();
    }

}
