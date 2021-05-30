package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.ChangeUserInformationParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllCoursesParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllUsersParam;
import com.hanzoy.yuekewei.pojo.dto.param.ManageLoginParam;
import com.hanzoy.yuekewei.pojo.dto.result.ChangeUserInformationResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllCoursesResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllUsersResult;
import com.hanzoy.yuekewei.pojo.dto.result.ManageLoginResult;
import com.hanzoy.yuekewei.service.ManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("网页端课程管理")
@RestController
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    ManageService manageService;

    @ApiOperation("管理员登陆")
    @PostMapping("/login")
    public CommonResult<ManageLoginResult> login(@RequestBody @Validated ManageLoginParam param){
        ManageLoginResult result = manageService.login(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查看当前所有的课程")
    @PostMapping("/getAllCourses")
    public CommonResult<GetAllCoursesResult> getAllCourses(@RequestBody @Validated GetAllCoursesParam param){
        GetAllCoursesResult result = manageService.getAllCourses(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查看所有用户")
    @PostMapping("/getAllUsers")
    public CommonResult<GetAllUsersResult> getAllUsers(@RequestBody @Validated GetAllUsersParam param){
        GetAllUsersResult result = manageService.getAllUsers(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改用户个人信息")
    @PostMapping("/changeUserInformation")
    public CommonResult<ChangeUserInformationResult> changeUserInformation(@RequestBody @Validated ChangeUserInformationParam param){
        ChangeUserInformationResult result = manageService.changeUserInformation(param);
        return CommonResult.success(result);
    }
}
