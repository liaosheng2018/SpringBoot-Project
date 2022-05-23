package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "t_sys_log")
public class SysLog implements Serializable {
    private Long id;

    private String method;

    private String uri;

    private String params;

    private Long time;

    private static final long serialVersionUID = 1L;

}