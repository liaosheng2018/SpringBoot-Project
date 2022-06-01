package com.zzg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzg.mapper.SysUserMapper;
import com.zzg.model.SysUser;
import com.zzg.quartz.service.SysUserService;
import org.springframework.stereotype.Component;

@Service
@Component
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
