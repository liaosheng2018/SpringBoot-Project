package com.zzg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzg.mapper.SysLogMapper;
import com.zzg.model.SysLog;
import com.zzg.quartz.service.SysLogService;
import org.springframework.stereotype.Component;

@Service
@Component
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
