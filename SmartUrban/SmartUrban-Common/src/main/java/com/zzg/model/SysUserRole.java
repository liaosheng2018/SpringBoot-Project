package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "t_sys_user_role")
public class SysUserRole implements Serializable {
    private Long id;

    private Long userId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}