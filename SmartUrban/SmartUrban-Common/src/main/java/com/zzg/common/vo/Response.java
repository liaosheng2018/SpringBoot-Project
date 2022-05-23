package com.zzg.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response <T>{
    private int code;

    private String msg;

    private T data;

    public static <T> Response<T> error(int code, String msg, T data){
        return new Response<T>(code, msg, data);
    }

    public static <T> Response<T> error(String msg){
        return new Response<T>(500, msg, null);
    }

    public static <T> Response<T> success(T data){
        return new Response<T>(200, null, data);
    }

    public static <T> Response<T> success(String msg){
        return new Response<T>(200, msg, null);
    }

    public static <T> Response<T> success(String msg, T data){
        return new Response<T>(200, msg, data);
    }



}
