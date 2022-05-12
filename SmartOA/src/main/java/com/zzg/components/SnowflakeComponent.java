package com.zzg.components;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * 基于HuTool 工具包雪花算法
 */
@Component
public class SnowflakeComponent {
    private static Logger logger = LoggerFactory.getLogger(SnowflakeComponent.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private long workerId;//为终端ID
    private long datacenterId = 1;//数据中心ID
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @PostConstruct
    public void init() {
        workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        logger.info("当前机器的workId:{}", workerId);
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

}
