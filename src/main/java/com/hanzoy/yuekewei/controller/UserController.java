package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("新用户注册")
    @PostMapping("/register")
    public CommonResult<UserRegisterResult> userRegister(@RequestBody @Validated UserRegisterParam param){
        UserRegisterResult result = userService.userRegister(param);
        return CommonResult.success(result);
    }

    @ApiOperation("检查token")
    @PostMapping("/checkToken")
    public CommonResult<CheckTokenResult> checkToken(@RequestBody @Validated CheckTokenParam param){
        CheckTokenResult result = userService.checkToken(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改信息")
    @PostMapping("/changeInformation")
    public CommonResult<ChangeInformationResult> changeInformation(@RequestBody @Validated ChangeInformationParam param){
        ChangeInformationResult result = userService.changeInformation(param);
        return CommonResult.success(result);
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/getUserInfo")
    public CommonResult<GetUserInfoResult> getUserInfo(@RequestBody @Validated GetUserInfoParam param){
        GetUserInfoResult result = userService.getUserInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改课时")
    @PostMapping("/updateCourseTime")
    public CommonResult<UpdateCourseTimeResult> updateCourseTime(@RequestBody @Validated UpdateCourseTimeParam param){
        UpdateCourseTimeResult result = userService.updateCourseTime(param);
        return CommonResult.success(result);
    }

    @ApiOperation("创建课时")
    @PostMapping("/addCourseTime")
    public CommonResult<AddCourseTimeResult> updateCourseTime(@RequestBody @Validated AddCourseTimeParam param){
        AddCourseTimeResult result = userService.addCourseTime(param);
        return CommonResult.success(result);
    }
}
