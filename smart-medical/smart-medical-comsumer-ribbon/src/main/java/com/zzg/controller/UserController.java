package com.zzg.controller;

import com.zzg.job.DelayJob;
import com.zzg.model.User;
import com.zzg.service.UserService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> get() throws SchedulerException {
        List<User> list = userService.selectAll();

        // quartz 定时任务框架实现
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(DelayJob.class)
                .withIdentity("delayJob", "delayGroup").build();

        // 创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("delayTrigger", "delayTriggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // 将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);

        // 调度器开始调度任务
        scheduler.start();


        return list;
    }
}
