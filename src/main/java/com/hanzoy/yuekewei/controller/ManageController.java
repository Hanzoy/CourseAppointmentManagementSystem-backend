package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
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

    @ApiOperation("修改密码")
    @PostMapping("/changePassword")
    public CommonResult<ChangePasswordResult> changePassword(@RequestBody @Validated ChangePasswordParam param){
        ChangePasswordResult result = manageService.changePassword(param);
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

    @ApiOperation("关键字查看所有用户")
    @PostMapping("/getAllUsersByKey")
    public CommonResult<GetAllUsersByKeyResult> getAllUsersByKey(@RequestBody @Validated GetAllUsersByKeyParam param){
        GetAllUsersByKeyResult result = manageService.getAllUsersByKey(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查看用户详细信息以及课程订单")
    @PostMapping("/getUserInfo")
    public CommonResult<GetUserInfoResult> getUserInfo(@RequestBody @Validated GetUserInfoParam param){
        GetUserInfoResult result = manageService.getUserInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改用户个人信息")
    @PostMapping("/changeUserInformation")
    public CommonResult<ChangeUserInformationResult> changeUserInformation(@RequestBody @Validated ChangeUserInformationParam param){
        ChangeUserInformationResult result = manageService.changeUserInformation(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查看课程表模版")
    @PostMapping("/getEXTimetable")
    public CommonResult<GetEXTimetableResult> getEXTimetable(@RequestBody @Validated GetEXTimetableParam param){
        GetEXTimetableResult result = manageService.getEXTimetable(param);
        return CommonResult.success(result);
    }

    @ApiOperation("添加课程表模版")
    @PostMapping("/addEXTimetable")
    public CommonResult<AddEXTimetableResult> addEXTimetable(@RequestBody @Validated AddEXTimetableParam param){
        AddEXTimetableResult result = manageService.addEXTimetable(param);
        return CommonResult.success(result);
    }

    @ApiOperation("删除课程模版")
    @PostMapping("/deleteEXTimetable")
    public CommonResult<DeleteEXTimetableResult> deleteEXTimetable(@RequestBody @Validated DeleteEXTimetableParam param){
        DeleteEXTimetableResult result = manageService.deleteEXTimetable(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改课程模版")
    @PostMapping("/editEXTimetable")
    public CommonResult<EditEXTimetableResult> editEXTimetable(@RequestBody @Validated EditEXTimetableParam param){
        EditEXTimetableResult result = manageService.editEXTimetable(param);
        return CommonResult.success(result);
    }
}
