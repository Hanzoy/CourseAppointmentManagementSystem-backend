package com.hanzoy.yuekewei.aop;

import com.hanzoy.yuekewei.exception.myExceptions.TokenErrorException;
import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.utils.JWTUtils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Aspect
@Component
public class CheckToken {
    @Autowired
    JWTUtils jwtUtils;

    //指定MeetingService下返回值为CommonResult的方法
    @Pointcut("execution(public com.hanzoy.yuekewei.pojo.dto.result.* com.hanzoy.yuekewei.service.*Service+.*(..))")
    public void server(){}

    @Around("server()")
    public Object checkToken(ProceedingJoinPoint joinPoint) throws Throwable {
        //声明token字段
        Field tokenField = null;
        //声明token内容
        String token = null;
        try {
            //获取param中的token字段
            Object param = joinPoint.getArgs()[0];
            tokenField = param.getClass().getDeclaredField("token");
        } catch (Exception ignored){

        }
        if(tokenField != null){
            //如果参数中存在token字段，先获取token字段
            //修改token为可查看
            tokenField.setAccessible(true);
            //获取token内容
            try {
                token =(String) tokenField.get(joinPoint.getArgs()[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //检验token
            if (!jwtUtils.checkToken(token)) {
                //如果token未通过检验则，拦截方法执行
                throw new TokenErrorException("token未通过校验");
            }
        }
        //如果没有token字段或者通过token检验则执行业务方法
        return joinPoint.proceed();
    }
}
