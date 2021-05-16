package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.CourseInfo;
import com.hanzoy.yuekewei.pojo.po.Timetable;
import com.hanzoy.yuekewei.pojo.po.TimetableInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface CourseMapper {
    ArrayList<CourseInfo> getCourseByUserOpenid(@Param("openid") String openid);

    ArrayList<Timetable> getTimetableByCourseIdAndDate(@Param("courseId") Integer courseId, @Param("date") String date);

    Boolean isReservation(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    TimetableInfo getTimetableInfoByTimetableIdAndOpenid(@Param("timetableId") Integer timetableId, @Param("openid") String openid);
}
