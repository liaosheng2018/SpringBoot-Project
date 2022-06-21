package com.zzg.controller;

import com.zzg.service.xaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xa")
public class XAController {
    @Autowired
    private xaService transaction;

    @GetMapping("/save")
    public String insert() {
        transaction.xaTransaction();
        return "success";
    }
}
