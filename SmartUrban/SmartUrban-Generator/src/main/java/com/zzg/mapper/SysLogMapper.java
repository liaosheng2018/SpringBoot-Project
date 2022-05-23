package com.zzg.mapper;

import com.zzg.model.SysLog;
import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    List<SysLog> selectAll();

    int updateByPrimaryKey(SysLog record);
}