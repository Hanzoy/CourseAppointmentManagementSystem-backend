package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.CourseAndUserInfo;
import com.hanzoy.yuekewei.pojo.po.TimetableExampleInfo;
import com.hanzoy.yuekewei.pojo.po.UserCourseTimeInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Admin;
import com.hanzoy.yuekewei.pojo.po.entity.TimetableExample;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ManageMapper {
    Admin getAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    ArrayList<CourseAndUserInfo> getAllCourseInfo();

    ArrayList<UserCourseTimeInfo> getUserCourseInfoByCourseId(@Param("courseId") Integer courseId);

    ArrayList<TimetableExampleInfo> getEXTimetableByWeekAndCourseId(@Param("week") Integer week, @Param("courseId") Integer courseId);

    ArrayList<TimetableExampleInfo> getEXTimetableByWeek(@Param("week") Integer week);

    void insertEXTimetable(TimetableExample timetableExample);

    void deleteEXTimetable(@Param("id") Integer id);

    void editEXTimetable(@Param("id") Integer id,
                         @Param("courseId") Integer courseId,
                         @Param("address") String address,
                         @Param("endTime") String endTime,
                         @Param("startTime") String startTime,
                         @Param("remark") String remark,
                         @Param("week") Integer week,
                         @Param("toplimit") Integer toplimit,
                         @Param("coachId") Integer coachId,
                         @Param("cost") Double cost);
    void changePassword(@Param("id") Integer id, @Param("password") String password);
}
