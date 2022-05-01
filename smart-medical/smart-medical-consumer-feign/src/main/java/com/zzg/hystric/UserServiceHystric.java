package com.zzg.hystric;

import com.zzg.model.User;
import com.zzg.service.UserServiceFeign;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceHystric implements UserServiceFeign {
    @Override
    public List<User> selectAll() {
        System.out.println("熔断机制启动, Provider 服务提供者异常");
        return null;
    }
}
