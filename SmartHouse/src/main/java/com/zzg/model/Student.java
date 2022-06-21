package com.zzg.model;

import lombok.Data;

@Data
public class Student implements java.io.Serializable {
    // 主键
    private Long id;

    // 性别
    private Integer sex;

    // 年龄
    private Integer age;

    // 姓名
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
