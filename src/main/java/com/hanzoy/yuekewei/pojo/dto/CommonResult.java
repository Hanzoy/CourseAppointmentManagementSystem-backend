package com.hanzoy.yuekewei.pojo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 统一返回前端类
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResult<T> {
    private String code;
    private String message;
    private T data;

    protected CommonResult(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    protected CommonResult(ResultEnum resultEnum, T data){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
        this.data = data;
    }

    public static <T> CommonResult<T> success(String code, String message, T data) {
        return new CommonResult<T>(code, message, data);
    }

    public static <T> CommonResult<T> success(String code, String message) {
        return success(code, message, null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultEnum.SUCCESS, data);
    }

    public static <T> CommonResult<T> fail(String code, String message, T data) {
        return new CommonResult<T>(code, message, data);
    }

    public static <T> CommonResult<T> fail(String code, String message) {
        return fail(code, message, null);
    }

    public static <T> CommonResult<T> paramError(String message){
        return new CommonResult<T>(ResultEnum.PARAM_ERROR.getCode(), message);
    }

    public static <T> CommonResult<T> paramError(){
        return new CommonResult<T>(ResultEnum.PARAM_ERROR, null);
    }

    public static <T> CommonResult<T> tokenError(String message){
        return new CommonResult<T>(ResultEnum.TOKEN_ERROR.getCode(), message);
    }

    public static <T> CommonResult<T> tokenError(){
        return new CommonResult<T>(ResultEnum.TOKEN_ERROR, null);
    }

    public static <T> CommonResult<T> serverError(String message){
        return new CommonResult<T>(ResultEnum.SERVER_ERROR.getCode(), message);
    }

    public static <T> CommonResult<T> serverError(){
        return new CommonResult<T>(ResultEnum.SERVER_ERROR, null);
    }

    public static <T> CommonResult<T> authError(String message){
        return new CommonResult<T>(ResultEnum.AUTH_ERROR.getCode(), message);
    }

    public static <T> CommonResult<T> authError(){
        return new CommonResult<T>(ResultEnum.AUTH_ERROR, null);
    }
}