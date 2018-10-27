package com.chaoftic.easysearch.utils;

@SuppressWarnings("unchecked")
public class Result<T> {

    enum ResultCode{
        SUCCESS(200),//成功
        FAIL(400),//失败
        UNAUTHORIZED(403),//未认证（签名错误）
        NOT_FOUND(404),//接口不存在
        INTERNAL_SERVER_ERROR(500);//服务器内部错误
        private final int code;
        ResultCode(int code) {
            this.code = code;
        }
    }

    private int code;
    private String message;
    private T data;

    public Result setCode(ResultCode code) {
        this.code = code.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    public static<T> Result success(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage("success")
                .setData(data);
    }

    public static<T> Result<T> success(T data, String message){
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setData(data)
                .setMessage(message);
    }
}