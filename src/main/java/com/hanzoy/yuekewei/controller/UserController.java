package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.UserLoginParam;
import com.hanzoy.yuekewei.pojo.dto.param.UserRegisterParam;
import com.hanzoy.yuekewei.pojo.dto.result.UserLoginResult;
import com.hanzoy.yuekewei.pojo.dto.result.UserRegisterResult;
import com.hanzoy.yuekewei.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public CommonResult<UserLoginResult> userLogin(@RequestBody @Validated UserLoginParam param){
        UserLoginResult result = userService.userLogin(param);
        return CommonResult.success(result);
    }

    @PostMapping("/register")
    public CommonResult<UserRegisterResult> userRegister(@RequestBody @Validated UserRegisterParam param){
        UserRegisterResult result = userService.userRegister(param);
        return CommonResult.success(result);
    }
}