package com.zzg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzg.mapper.SysAuthMapper;
import com.zzg.model.SysAuth;
import com.zzg.quartz.service.SysAuthService;
import org.springframework.stereotype.Component;

@Service
@Component
public class SysAuthServiceImpl extends ServiceImpl<SysAuthMapper, SysAuth> implements SysAuthService {
}
