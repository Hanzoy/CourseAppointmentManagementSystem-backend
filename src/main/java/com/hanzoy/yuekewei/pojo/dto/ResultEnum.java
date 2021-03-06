package com.hanzoy.yuekewei.pojo.dto;

/**
 * 返回码与返回值的枚举类
 */
public enum ResultEnum {
    SUCCESS("00000", "请求成功"),
    PARAM_ERROR("A0400", "用户请求参数错误"),
    WECHAT_SERVER_ERROR("C0001", "微信服务接口错误"),
    TOKEN_ERROR("A0220", "用户身份验证错误"),
    SERVER_ERROR("B0001", "服务器执行错误"),
    AUTH_ERROR("A0300", "访问权限异常");
    private final String code;
    private final String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
