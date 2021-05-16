package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.utils.ClassCopyUtils.ClassCopyUtils;
import com.hanzoy.yuekewei.mapper.CourseMapper;
import com.hanzoy.yuekewei.pojo.bo.CourseTimetable;
import com.hanzoy.yuekewei.pojo.bo.UserTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.GetCourseInfoParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetMyCourseInfoParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetReservationCourseInfoParam;
import com.hanzoy.yuekewei.pojo.dto.result.GetCourseInfoResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetMyCourseInfoResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetReservationCourseInfoResult;
import com.hanzoy.yuekewei.pojo.po.CourseInfo;
import com.hanzoy.yuekewei.pojo.po.Timetable;
import com.hanzoy.yuekewei.pojo.po.TimetableInfo;
import com.hanzoy.yuekewei.service.CourseService;
import com.hanzoy.yuekewei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    UserService userService;

    @Resource
    CourseMapper courseMapper;
    
    @Override
    public GetMyCourseInfoResult getMyCourseInfo(GetMyCourseInfoParam param) {
        //创建返回对象
        GetMyCourseInfoResult result = new GetMyCourseInfoResult();
        
        //获取token存储的信息
        UserTokenInfo tokenInfo = userService.getUserTokenInfo(param.getToken());

        //数据库查询Courses
        ArrayList<CourseInfo> Courses = courseMapper.getCourseByUserOpenid(tokenInfo.getOpenid());

        //写入数据
        result.setCourseInfo(Courses);

        return result;
    }

    @Override
    public GetReservationCourseInfoResult getReservationCourseInfo(GetReservationCourseInfoParam param) {
        //创建返回对象
        GetReservationCourseInfoResult result = new GetReservationCourseInfoResult();
        result.setCourseTimetables(new ArrayList<>());

        //获取token存储的信息
        UserTokenInfo tokenInfo = userService.getUserTokenInfo(param.getToken());

        //数据库查询Courses
        ArrayList<CourseInfo> Courses = courseMapper.getCourseByUserOpenid(tokenInfo.getOpenid());

        for (CourseInfo courses : Courses) {
            //创建某一课程的时间表
            CourseTimetable courseTimetable = new CourseTimetable();

            //完善该课程的时间表信息
            courseTimetable.setId(courses.getId());

            //查询该课程的课程时间表内具体内容
            ArrayList<Timetable> timetableByCourseIdAndDate = courseMapper.getTimetableByCourseIdAndDate(courses.getId(), param.getDate());

            for (Timetable timetable : timetableByCourseIdAndDate) {
                //查询用户是否预约了该课程
                Boolean reservation = courseMapper.isReservation(tokenInfo.getOpenid(), timetable.getId());
                //设置是否预约了课程
                timetable.setIsReservation(reservation);
            }

            //将查询的课程时间表具体内容写入时间表中
            courseTimetable.setTimetables(timetableByCourseIdAndDate);

            //将该课程时间表写入返回对象内
            result.getCourseTimetables().add(courseTimetable);

        }
        return result;
    }

    @Override
    public GetCourseInfoResult getCourseInfo(GetCourseInfoParam param) {
        //获取待查的课程id
        Integer id = param.getId();

        //创建返回对象
        GetCourseInfoResult result = new GetCourseInfoResult();

        //获取token存储的信息
        UserTokenInfo tokenInfo = userService.getUserTokenInfo(param.getToken());

        //数据库查询
        TimetableInfo timetableInfo = courseMapper.getTimetableInfoByTimetableIdAndOpenid(param.getId(), tokenInfo.getOpenid());

        //将查询到到数据写入返回对象中
        ClassCopyUtils.ClassCopy(result, timetableInfo);

        return result;
    }
}
