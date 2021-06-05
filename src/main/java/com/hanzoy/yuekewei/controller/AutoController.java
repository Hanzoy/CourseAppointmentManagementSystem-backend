package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.Auto30CreateParam;
import com.hanzoy.yuekewei.pojo.dto.param.AutoCreateParam;
import com.hanzoy.yuekewei.pojo.dto.result.Auto30CreateResult;
import com.hanzoy.yuekewei.pojo.dto.result.AutoCreateResult;
import com.hanzoy.yuekewei.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api("自动操作")
@RestController
public class AutoController {

    @Autowired
    CourseService courseService;

    @ApiOperation("根据模版自动生成课程")
    @PostMapping("/autoCreate")
    public CommonResult<AutoCreateResult> autoCreate(@RequestBody @Validated AutoCreateParam param){
        AutoCreateResult result = courseService.autoCreate(param);
        return CommonResult.success(result);
    }

    @ApiOperation("根据模版自动生成一个月课程")
    @PostMapping("/auto30Create")
    public CommonResult<Auto30CreateResult> auto30Create(@RequestBody @Validated Auto30CreateParam param){
        Auto30CreateResult result = courseService.auto30Create(param);
        return CommonResult.success(result);
    }

}
