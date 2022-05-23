package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "t_sys_role")
public class SysRole implements Serializable {
    private Long id;

    private String roleName;

    private String roleCode;

    private static final long serialVersionUID = 1L;


}