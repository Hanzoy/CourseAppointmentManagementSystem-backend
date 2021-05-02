package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetSwiperResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetVenueResult;
import com.hanzoy.yuekewei.service.CoachService;
import com.hanzoy.yuekewei.service.StaticResourceService;
import com.hanzoy.yuekewei.service.VenueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("微信客户端资源获取")
@RestController
public class ResourcesController {

    @Autowired
    StaticResourceService staticResourceService;

    @Autowired
    CoachService coachService;

    @Autowired
    VenueService venueService;

    @ApiOperation("获取滑动条内容")
    @GetMapping("/getSwiper")
    public CommonResult<GetSwiperResult> getSwiper(){
        GetSwiperResult result = staticResourceService.getSwiper();
        return CommonResult.success(result);
    }

    @ApiOperation("获取教练信息")
    @GetMapping("/getCoach")
    public CommonResult<GetCoachResult> getCoach(){
        GetCoachResult coach = coachService.getCoach();
        return CommonResult.success(coach);
    }

    @ApiOperation("获取场馆信息")
    @GetMapping("/getVenue")
    public CommonResult<GetVenueResult> getVenue(){
        GetVenueResult venue = venueService.getVenue();
        return CommonResult.success(venue);
    }
}
