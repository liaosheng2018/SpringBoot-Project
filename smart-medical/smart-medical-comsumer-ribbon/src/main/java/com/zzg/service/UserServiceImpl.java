package com.zzg.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zzg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    RestTemplate template;

    @Override
    @HystrixCommand(fallbackMethod = "ServiceError")
    public List<User> selectAll() {
        String url ="http://provider1/provider/list";
        return template.getForObject(url, List.class);
    }

    public List<User> ServiceError() {
        System.out.println("熔断机制启动, Provider 服务提供者异常");
        return null;
    }

}
