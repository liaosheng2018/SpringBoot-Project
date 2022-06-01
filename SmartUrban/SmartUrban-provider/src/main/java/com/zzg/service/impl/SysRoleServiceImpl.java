package com.zzg.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzg.mapper.SysRoleMapper;
import com.zzg.model.SysRole;
import com.zzg.quartz.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper mapper;
    @Override
    public List<SysRole> findByUserId(Integer userId) {
        return mapper.findByUserId(userId);
    }
}
