package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetSwiperResult;

/**
 * 静态资源管理
 */
public interface StaticResourceService {

    /**
     * 获取滑块层图片地址
     * @return 图片地址数组返回包装类
     */
    GetSwiperResult getSwiper();


}
