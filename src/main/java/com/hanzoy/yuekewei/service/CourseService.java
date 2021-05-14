package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.param.GetMyCourseInfoParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetReservationCourseInfoParam;
import com.hanzoy.yuekewei.pojo.dto.result.GetMyCourseInfoResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetReservationCourseInfoResult;

public interface CourseService {
    /**
     * 获取用户拥有课时的课程信息
     * @param param 参数
     * @return 返回对象
     */
    GetMyCourseInfoResult getMyCourseInfo(GetMyCourseInfoParam param);

    /**
     * 获取某一时刻用户能预约的课程时间安排
     * @param param 参数
     * @return 返回包装类
     */
    GetReservationCourseInfoResult getReservationCourseInfo(GetReservationCourseInfoParam param);
}
