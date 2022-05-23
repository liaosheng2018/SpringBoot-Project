package com.zzg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzg.mapper.SysRoleMapper;
import com.zzg.mapper.SysUserMapper;
import com.zzg.model.SysRole;
import com.zzg.model.SysUser;
import com.zzg.service.SysRoleService;
import com.zzg.service.SysUserService;
import org.springframework.stereotype.Component;

@Service
@Component
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
