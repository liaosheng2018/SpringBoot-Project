package com.zzg.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("oa_form")
public class OaForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String desc;

    @TableField(value = "key_")
    private String key;

    private String content;

    @TableField(value = "create_id")
    private String createId;

    @TableField(value = "create_dt")
    private Date createDt;

    private Integer version;

    @TableField(value = "update_id")
    private String updateId;

    @TableField(value = "update_dt")
    private Date updateDt;

}