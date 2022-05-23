package com.zzg.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zzg.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> findByUserId(@Param("userId") Integer userId);
}