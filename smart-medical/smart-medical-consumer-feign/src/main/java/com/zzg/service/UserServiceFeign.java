package com.zzg.service;

import com.zzg.hystric.UserServiceHystric;
import com.zzg.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "provider1",fallback = UserServiceHystric.class)
@Service
public interface UserServiceFeign {
    @GetMapping("/provider/list")
    List<User> selectAll();
}
