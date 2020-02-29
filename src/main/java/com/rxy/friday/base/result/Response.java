package com.rxy.friday.base.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 通用响应对象
        * @author rxy
        * @date 2020/2/20  19:31  星期四
        **/
@Data
public class Response<T> implements Serializable {
    Integer code;//代码
    String msg;//信息
    List<T> datas;//返回数据
    int count;//数据数量
    T data;//任何类型条件

    public Response() {
    }
    public Response(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }


    public Response(Integer code, String msg, T data, Integer count, List<T> datas) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
        this.datas = datas;
    }


    /* 无数据传输的 成功返回 */
    public static <T> Response<T> success() {
        return new Response<T>( ResponseCode.SUCCESS.getCode(),  ResponseCode.SUCCESS.getMessage());
    }

    public static <T> Response<T> success(String msg) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> Response<T> success(ResponseCode resultCode) {
        return new Response<T>( resultCode.getCode(),  resultCode.getMessage());
    }

    /* 单个数据传输的 成功返回 */
    public static <T> Response<T> success(T data) {
        return new Response<T>( ResponseCode.SUCCESS.getCode(),  ResponseCode.SUCCESS.getMessage(), data, 0, null);
    }

    public static <T> Response<T> success(String msg, T data) {
        return new Response<T>(ResponseCode.SUCCESS.getCode(), msg, data, 0, null);
    }

    public static <T> Response<T> success(ResponseCode resultCode, T data) {
        return new Response<T>( resultCode.getCode(),  resultCode.getMessage(), data, 0, null);
    }

    /* 分页数据传输的 成功返回 */
    public static <T> Response<T> success(Integer count, List<T> datas) {
        return new Response<T>(ResponseCode.TABLE_SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage(),null,count, datas);
    }

    public static <T> Response<T> success(String msg, Integer count, List<T> datas) {
        return new Response<T>(ResponseCode.TABLE_SUCCESS.getCode(), msg, null, count, datas);
    }

    public static <T> Response<T> success(ResponseCode resultCode, Integer count, List<T> datas) {
        return new Response<T>( resultCode.getCode(),  resultCode.getMessage(), null, count, datas);
    }
    /* 无数据传输的 失败返回 */
    public static <T> Response<T> failure() {
        return new Response<T>( ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMessage());
    }

    public static <T> Response<T> failure(ResponseCode resultCode) {
        return new Response<T>( resultCode.getCode(),  resultCode.getMessage());
    }

    public static <T> Response<T> failure(Integer code, String msg) {
        return new Response<T>( code,  msg);
    }

    public static Response ok() {
        return new Response();
    }
}
