package com.zzg.common.vo.enums;

public enum CodeMsgEnum {
    OK(200, "成功") ,NO_MATCH(400,"请求参数 不支持"), ERROR(500, "失败") ;


    CodeMsgEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
