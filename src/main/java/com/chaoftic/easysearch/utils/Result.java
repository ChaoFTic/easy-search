package com.chaoftic.easysearch.utils;

@SuppressWarnings("unchecked")
public class Result<T> {

    public static Integer SUCCESS = 200;
    public static Integer FAIL = 400;//失败
    public static Integer UNAUTHORIZED = 403;//未认证（签名错误）
    public static Integer NOT_FOUND = 404;//接口不存在
    public static Integer INTERNAL_SERVER_ERROR = 500;//服务器内部错误


    private int code;
    private String message;
    private T data;

    public static <T> Result success(T data) {
        return new Result()
                .setCode(SUCCESS)
                .setMessage("success")
                .setData(data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result()
                .setCode(SUCCESS)
                .setData(data)
                .setMessage(message);
    }

    private Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
}