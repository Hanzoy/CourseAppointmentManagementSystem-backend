package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.Timetable;

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

    /**
     * 根据id获取某一课程安排详细信息
     * @param param 参数
     * @return 返回包装类
     */
    GetCourseInfoResult getCourseInfo(GetCourseInfoParam param);

    /**
     * 预约课程
     * @param param 参数
     * @return 返回对象
     */
    ReservationCourseResult reservationCourse(ReservationCourseParam param);

    /**
     * 查询用户当前已经预约的课程
     * @param param
     * @return
     */
    HasReservationCourseResult hasReservationCourse(HasReservationCourseParam param);

    GetCourseResult getCourse(GetCourseParam param);
}
