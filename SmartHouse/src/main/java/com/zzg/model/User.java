package com.zzg.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    // 主键
    private Integer id;
    // 昵称
    private String nickname;
    // 密码
    private String password;
    // 性别
    private Integer sex;
    // 出生年月
    private String birthday;

}