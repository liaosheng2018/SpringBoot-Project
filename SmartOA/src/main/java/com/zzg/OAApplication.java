package com.zzg;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@MapperScan("com.zzg.mapper")
public class OAApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAApplication.class, args);
    }
}
