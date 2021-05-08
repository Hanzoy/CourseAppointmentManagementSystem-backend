package com.hanzoy.yuekewei.exception.myExceptions;

public class WechatServerErrorException extends RuntimeException{
    public WechatServerErrorException(String message) {
        super(message);
    }
}
