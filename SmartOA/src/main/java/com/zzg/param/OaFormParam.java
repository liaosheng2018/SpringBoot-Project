package com.zzg.param;

import lombok.Data;

import java.util.Date;

@Data
public class OaFormParam implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String desc;

    private String key;

    private String content;

    private String createId;

    private Date createDt;

    private Integer version;

    private String updateId;

    private Date updateDt;
}
