package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.result.GetVenueResult;

/**
 * 场地业务层
 */
public interface VenueService {
    /**
     * 获取场馆信息
     * @return 教练信息返回包装类
     */
    GetVenueResult getVenue();
}
