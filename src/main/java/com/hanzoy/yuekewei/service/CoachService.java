package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;

import java.util.List;

/**
 * 教练业务层
 */
public interface CoachService {
    /**
     * 获取教练信息
     * @return 教练信息返回包装类
     */
    GetCoachResult getCoach();
}
