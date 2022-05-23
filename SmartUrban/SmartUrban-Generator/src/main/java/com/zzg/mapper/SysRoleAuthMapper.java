package com.zzg.mapper;

import com.zzg.model.SysRoleAuth;
import java.util.List;

public interface SysRoleAuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleAuth record);

    SysRoleAuth selectByPrimaryKey(Long id);

    List<SysRoleAuth> selectAll();

    int updateByPrimaryKey(SysRoleAuth record);
}