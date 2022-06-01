package com.zzg.common.vo;

import cn.hutool.http.HttpStatus;
import com.zzg.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response <T> implements java.io.Serializable{
    private int code;

    private String msg;

    private T data;

    public static <T> Response<T> error(int code, String msg, T data){
        return new Response<T>(code, msg, data);
    }

    public static <T> Response<T> error(String msg){
        return new Response<T>(ResultCode.FAIL.getVal(), msg, null);
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(ResultCode.SUCCESS.getVal(), ResultCode.SUCCESS.getMsg(), data);
    }

    public static <T> Response<T> success(String msg){
        return new Response<T>(ResultCode.SUCCESS.getVal(), msg, null);
    }

    public static <T> Response<T> success(String msg, T data){
        return new Response<T>(ResultCode.SUCCESS.getVal(), msg, data);
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
