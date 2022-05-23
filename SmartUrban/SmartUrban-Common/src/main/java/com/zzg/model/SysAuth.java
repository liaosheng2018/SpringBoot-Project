package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "t_sys_auth")
public class SysAuth implements Serializable {
    private Long id;

    private String authName;

    private String permission;

    private static final long serialVersionUID = 1L;
}