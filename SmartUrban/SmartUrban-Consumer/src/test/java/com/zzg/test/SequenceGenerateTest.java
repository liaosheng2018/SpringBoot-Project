package com.zzg.test;

import com.alibaba.fastjson.JSON;
import com.zzg.ConsumerApplication;
import com.zzg.redis.sequence.SequenceGenerate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = ConsumerApplication.class)
public class SequenceGenerateTest {
    @Resource
    private SequenceGenerate generate;

    @Test
    public void sequenceTest() throws InterruptedException {
        List<String> dataContainer = new ArrayList<>();

        CountDownLatch latch = new CountDownLatch(5);

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(5);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();

        for (int i =0; i <5; i++){
            executor.execute(()->{
                String seqNo = generate.getSequenceNumber("HMS", "atomic-today1234", 4);
                dataContainer.add(seqNo);
                latch.countDown();
            });
        }
        latch.await();

        System.out.println(JSON.toJSONString(dataContainer));
    }
}
