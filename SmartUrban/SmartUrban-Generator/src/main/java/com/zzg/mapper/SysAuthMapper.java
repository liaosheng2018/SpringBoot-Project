package com.zzg.mapper;

import com.zzg.model.SysAuth;
import java.util.List;

public interface SysAuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysAuth record);

    SysAuth selectByPrimaryKey(Long id);

    List<SysAuth> selectAll();

    int updateByPrimaryKey(SysAuth record);
}