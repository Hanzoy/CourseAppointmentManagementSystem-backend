package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("课程管理")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @ApiOperation("查询当前用户购买了课时的课程安排")
    @PostMapping("/getMyCourseInfo")
    public CommonResult<GetMyCourseInfoResult> getMyCourseInfo(@RequestBody @Validated GetMyCourseInfoParam param){
        GetMyCourseInfoResult result = courseService.getMyCourseInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查询选定时间当前用户能预约的课程安排")
    @PostMapping("/getReservationCourseInfo")
    public CommonResult<GetReservationCourseInfoResult> getReservationCourseInfo(@RequestBody @Validated GetReservationCourseInfoParam param){
        GetReservationCourseInfoResult result = courseService.getReservationCourseInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("根据id查询课程预约信息")
    @PostMapping("/getCourseInfo")
    public CommonResult<GetCourseInfoResult> getCourseInfo(@RequestBody @Validated GetCourseInfoParam param){
        GetCourseInfoResult result = courseService.getCourseInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("课程预定")
    @PostMapping("/reservationCourse")
    public CommonResult<ReservationCourseResult> reservationCourse(@RequestBody @Validated ReservationCourseParam param){
        ReservationCourseResult result = courseService.reservationCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("用户查询已预约的课程")
    @PostMapping("/hasReservationCourse")
    public CommonResult<HasReservationCourseResult> hasReservationCourse(@RequestBody @Validated HasReservationCourseParam param){
        HasReservationCourseResult result = courseService.hasReservationCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查询所有课程")
    @PostMapping("/getCourse")
    public CommonResult<GetCourseResult> getCourse(@RequestBody @Validated GetCourseParam param){
        GetCourseResult result = courseService.getCourse(param);
        return CommonResult.success(result);
    }
}
