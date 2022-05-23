package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "t_sys_user")
public class SysUser implements Serializable {
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private static final long serialVersionUID = 1L;


}