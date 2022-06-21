package com.zzg;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 1.0.0	默认：1.0.0
 * zzg	作者信息，可在通用配置里修改作者信息
 * 2022/04/19	日期信息，格式可在通用配置中修改
 * 应用程序开始	注释信息
 **/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.zzg.mapper")
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
