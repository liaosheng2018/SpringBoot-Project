package com.zzg.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @RequestMapping("/hello")
    public String get() {
        return "Hello MIS 医疗信息系统!";
    }
}
