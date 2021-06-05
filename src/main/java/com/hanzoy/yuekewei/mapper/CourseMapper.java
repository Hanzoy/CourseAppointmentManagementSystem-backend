package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.dto.param.AddTimetableParam;
import com.hanzoy.yuekewei.pojo.dto.param.EditTimetableParam;
import com.hanzoy.yuekewei.pojo.po.*;
import com.hanzoy.yuekewei.pojo.po.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.util.ArrayList;

public interface CourseMapper {
    ArrayList<CourseInfo> getCourseByUserOpenid(@Param("openid") String openid);

    ArrayList<Timetable> getTimetableByCourseIdAndDate(@Param("courseId") Integer courseId, @Param("date") String date);

    Boolean isReservation(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    TimetableInfo getTimetableInfoByTimetableIdAndOpenid(@Param("timetableId") Integer timetableId, @Param("openid") String openid);

    ArrayList<UserReservationInfo> getUserReservationByOpenidAndDate(@Param("openid") String openid, @Param("date") String date);

    TimetableDates getTimetableDateByTimetableId(@Param("timetableId") Integer timetableId);

    Double getCourseTime(@Param("openid") String openid, @Param("courseId") Integer courseId);

    void updateCourseTime(@Param("openid") String openid, @Param("courseId") Integer courseId, @Param("count") Double count);

    void addCourseTime(@Param("openid") String openid, @Param("courseId") Integer courseId);

    void recordOperation(@Param("openid") String openid, @Param("timetableId") Integer timetableId, @Param("operation") Double operation);

    void addUserToTimetable(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    void deleteUserToTimetable(@Param("openid") String openid, @Param("timetableId") Integer timetableId);

    ArrayList<CourseInfo> getUserCourseInfo(@Param("openid") String openid);

    ArrayList<OperationInfo> getUserOperationInfo(@Param("openid") String openid);

    ArrayList<CourseAndTimetableInfo> getHasReservationCourse(@Param("openid") String openid);

    ArrayList<TimetableInfo> getHasReservationTimetables(@Param("openid") String openid, @Param("courseId") Integer courseId);

    ArrayList<CourseInfo> getCourse();

    void insertCourse(Course course);

    void editCourse(@Param("id") Integer id, @Param("name") String name, @Param("money") Double money);

    void deleteCourse(@Param("id") Integer id);

    void changePicture(@Param("pictureId") Integer pictureId, @Param("id") Integer id );

    ArrayList<TimetableInfo> getTimetableByYearAndMonth(@Param("year") String year, @Param("month") String month);

    ArrayList<TimetableInfos> getTimetableByDateAndCourseId(@Param("date") String date, @Param("courseId") Integer courseId);

    void addTimetable(AddTimetableParam param);

    Boolean searchCoachByTimetableId(@Param("timetableId") Integer timetableId);

    void deleteTimetable(@Param("id") Integer id);

    void deleteTimetableByDate(@Param("date") String date);

    void editTimetable(EditTimetableParam param);

    void updateCoach(@Param("timetableId") Integer timetableId, @Param("coachId") Integer CoachId);

    void insertCoach(@Param("timetableId") Integer timetableId, @Param("coachId") Integer CoachId);

//    TimetableInfo getTimetableIndoByTimetableId(@Param())
}
