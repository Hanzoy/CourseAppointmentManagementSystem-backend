package com.hanzoy.yuekewei.exception;

import com.hanzoy.yuekewei.exception.myExceptions.ParamErrorException;
import com.hanzoy.yuekewei.exception.myExceptions.TokenErrorException;
import com.hanzoy.yuekewei.exception.myExceptions.WechatServerErrorException;
import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 参数数据校验格式
     * @param e 拦截的异常
     * @return 返回对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> handleValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.paramError(message);
    }

    /**
     * 请求解析异常拦截
     * @param e 拦截的异常
     * @return 返回的对象
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
        return CommonResult.paramError("请求参数解析异常");
    }

    /**
     * 微信接口异常拦截
     * @param e 拦截的异常
     * @return 返回对象
     */
    @ExceptionHandler(WechatServerErrorException.class)
    public CommonResult<Object> handleWechatServerErrorException(WechatServerErrorException e){
        return CommonResult.fail(ResultEnum.WECHAT_SERVER_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(ParamErrorException.class)
    public CommonResult<Object> handleParamErrorException(ParamErrorException e){
        return CommonResult.fail(ResultEnum.PARAM_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(TokenErrorException.class)
    public CommonResult<Object> tokenErrorException(TokenErrorException e){
        return CommonResult.tokenError(e.getMessage());
    }
//    @ExceptionHandler(NumberFormatException.class)
//    public CommonResult numberFormatException(NumberFormatException e){
//        return CommonResult.serverError("数字解析异常");
//    }

}
