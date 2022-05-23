package com.zzg.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzg.model.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> findByUserId(Integer userId);
}
