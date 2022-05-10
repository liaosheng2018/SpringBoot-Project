package com.zzg.common.vo;

import com.zzg.common.vo.enums.CodeMsgEnum;

/**
 * 通用响应结果集定义
 * @param <T>
 */
public class Resp<T> {
    private int code;
    private String msg;
    private T data;

    public Resp(CodeMsgEnum codeMsg, T data) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMessage();
        this.data = data;
    }

    public Resp(CodeMsgEnum codeMsg) {
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMessage();
    }

    public Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Resp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Resp OK(T data) {
        return new Resp(CodeMsgEnum.OK, data);
    }

    public static <T> Resp OK_WITHOUT_DATA() {
        return new Resp(CodeMsgEnum.OK);
    }

    public static <T> Resp ERROR(CodeMsgEnum codeMsg) {
        return new Resp(codeMsg, null);
    }

    public static <T> Resp ERROR(CodeMsgEnum codeMsg, T data) {
        return new Resp(codeMsg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
