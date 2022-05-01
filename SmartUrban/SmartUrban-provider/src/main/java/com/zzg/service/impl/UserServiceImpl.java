package com.zzg.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.zzg.mapper.UserMapper;
import com.zzg.model.User;
import com.zzg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
