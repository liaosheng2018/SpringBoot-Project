package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "t_sys_role_auth")
public class SysRoleAuth implements Serializable {
    private Long id;

    private Long roleId;

    private Long authId;

    private static final long serialVersionUID = 1L;

}