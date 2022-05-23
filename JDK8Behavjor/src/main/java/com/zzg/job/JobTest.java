package com.zzg.job;

import org.quartz.*;
import org.quartz.spi.ThreadPool;

import java.util.Date;

public class JobTest {

    public static void main(String[] args) {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 5000);

        Date endDate = new Date();
        endDate.setTime(startDate.getTime() + 5000);

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .usingJobData("trigger1", "这是jobDetail1的trigger")
                .startNow()//立即生效
                .startAt(startDate)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行


        ThreadPool
    }

    @DisallowConcurrentExecution
    @PersistJobDataAfterExecution
    public class SimpleJob implements Job{

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        }
    }

}
