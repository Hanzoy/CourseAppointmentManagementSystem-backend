package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetSwiperResult;
import com.hanzoy.yuekewei.service.StaticResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("静态资源管理")
@RestController("/resources")
public class ResourcesController {

    final
    StaticResourceService staticResourceService;

    public ResourcesController(StaticResourceService staticResourceService) {
        this.staticResourceService = staticResourceService;
    }

    @ApiOperation("获取滑动条内容")
    @GetMapping("/getSwiper")
    public CommonResult<GetSwiperResult> getSwiper(){
        GetSwiperResult result = staticResourceService.getSwiper();
        return CommonResult.success(result);
    }
}
