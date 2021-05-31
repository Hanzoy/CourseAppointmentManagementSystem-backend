package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface CourseMapper {
    ArrayList<CourseInfo> getCourseByUserOpenid(@Param("openid") String openid);

    ArrayList<Timetable> getTimetableByCourseIdAndDate(@Param("courseId") Integer courseId, @Param("date") String date);

    Boolean isReservation(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    TimetableInfo getTimetableInfoByTimetableIdAndOpenid(@Param("timetableId") Integer timetableId, @Param("openid") String openid);

    ArrayList<UserReservationInfo> getUserReservationByOpenidAndDate(@Param("openid") String openid, @Param("date") String date);

    TimetableDates getTimetableDateByTimetableId(@Param("timetableId") Integer timetableId);

    Integer getCourseTime(@Param("openid") String openid, @Param("courseId") Integer courseId);

    void updateCourseTime(@Param("openid") String openid, @Param("courseId") Integer courseId, @Param("count") Integer count);

    void addCourseTime(@Param("openid") String openid, @Param("courseId") Integer courseId);

    void recordOperation(@Param("openid") String openid, @Param("timetableId") Integer timetableId, @Param("operation") Integer operation);

    void addUserToTimetable(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    void deleteUserToTimetable(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    ArrayList<CourseInfo> getUserCourseInfo(@Param("openid") String openid);

    ArrayList<OperationInfo> getUserOperationInfo(@Param("openid") String openid);
}
