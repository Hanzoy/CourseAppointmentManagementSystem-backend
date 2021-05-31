package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.utils.ClassCopyUtils.ClassCopyUtils;
import com.hanzoy.yuekewei.mapper.CourseMapper;
import com.hanzoy.yuekewei.pojo.bo.CourseTimetable;
import com.hanzoy.yuekewei.pojo.bo.UserTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.*;
import com.hanzoy.yuekewei.service.CourseService;
import com.hanzoy.yuekewei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@Transactional
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
//        Integer id = param.getId();

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

    @Override
    public ReservationCourseResult reservationCourse(ReservationCourseParam param) {
        //创建返回对象
        ReservationCourseResult result = new ReservationCourseResult();

        //获取参数
        Boolean isReservation = param.getIsReservation();
        Integer timetableId = param.getId();

        //获取token存储信息
        UserTokenInfo tokenInfo = userService.getUserTokenInfo(param.getToken());

        //查询当前将要预约的课程的全部信息
        TimetableInfo timetableInfo = courseMapper.getTimetableInfoByTimetableIdAndOpenid(timetableId, tokenInfo.getOpenid());

        //获取当前时间
        Date now = new Date();
        //格式化当前时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm");
        String nowDateString = sdf.format(now);

        if (isReservation) {
            //预约课程

            //如果预约的是过去的时间
            if (nowDateString.compareTo(timetableInfo.getDate() + " " + timetableInfo.getStartTime()) > 0) {
                result.setResult(false);
                result.setMessage("您不可预约当前课程");
                return result;
            }

            synchronized (CourseServiceImpl.class) {

                //判断用户是否已经预约了该课程
                Boolean hasReservation = courseMapper.isReservation(tokenInfo.getOpenid(), timetableId);
                if(hasReservation){
                    result.setResult(false);
                    result.setMessage("您已预约当前课程");
                    return result;
                }

                //查询该用户将要预约的课程当天已预约的课程
                ArrayList<UserReservationInfo> userReservations = courseMapper.getUserReservationByOpenidAndDate(tokenInfo.getOpenid(), timetableInfo.getDate());

                //判断课程是否冲突
                boolean flag = true;
                for (UserReservationInfo userReservation : userReservations) {
                    if (!(userReservation.getStartTime().compareTo(timetableInfo.getEndTime())>=0 || userReservation.getEndTime().compareTo(timetableInfo.getStartTime())<=0)){
                        flag = false;
                        break;
                    }
                }

                //如果课程冲突
                if(!flag){
                    result.setResult(false);
                    result.setMessage("您当前预约的课程时间段内已有其他课程");
                    return result;
                }

                //查询用户是否拥有足够的课时
                Integer courseTime = courseMapper.getCourseTime(tokenInfo.getOpenid(), timetableInfo.getCourseId());
                if(courseTime<=0){
                    result.setResult(false);
                    result.setMessage("您的课程课时不足，无法预约该课程");
                    return result;
                }

                //将课时数量减1
                courseMapper.updateCourseTime(tokenInfo.getOpenid(), timetableInfo.getCourseId(), courseTime-1);
                //记录当前预约操作
                courseMapper.recordOperation(tokenInfo.getOpenid(), timetableId, -1);
                //将用户添加至该课程成员中
                courseMapper.addUserToTimetable(tokenInfo.getOpenid(), timetableId);

                result.setResult(true);
                result.setMessage("完成预约成功");
            }
        } else {
            //取消预约课程
            synchronized (CourseServiceImpl.class){
                //判断用户是否已经预约了该课程
                Boolean hasReservation = courseMapper.isReservation(tokenInfo.getOpenid(), timetableId);
                if(!hasReservation){
                    result.setResult(false);
                    result.setMessage("您没有约当前课程");
                    return result;
                }

                if(sdf.format(now).compareTo(timetableInfo.getDate()+" "+timetableInfo.getStartTime())>0){
                    result.setResult(false);
                    result.setMessage("当前操作无效");
                    return result;
                }

                //如果距离课程开始还剩下2小时不能取消预约
                java.util.Calendar calendar = new GregorianCalendar();
                calendar.setTime(now);
                calendar.add(Calendar.HOUR,2); //把日期往后增加20分钟
                Date limitDate=calendar.getTime();
                if(sdf.format(limitDate).compareTo(timetableInfo.getDate()+" "+timetableInfo.getStartTime())>0){
                    result.setResult(false);
                    result.setMessage("课程开始前2小时内无法取消预约");
                    return result;
                }

                //查询用户拥有的课时
                Integer courseTime = courseMapper.getCourseTime(tokenInfo.getOpenid(), timetableInfo.getCourseId());

                //将课时数量+1
                courseMapper.updateCourseTime(tokenInfo.getOpenid(), timetableInfo.getCourseId(), courseTime+1);
                //记录当前预约操作
                courseMapper.recordOperation(tokenInfo.getOpenid(), timetableId, 1);
                //将用户从该课程成员中删除
                courseMapper.deleteUserToTimetable(tokenInfo.getOpenid(), timetableId);

                result.setResult(true);
                result.setMessage("取消预约成功");
            }
        }
        return result;
    }

    @Override
    public HasReservationCourseResult hasReservationCourse(HasReservationCourseParam param) {

        HasReservationCourseResult result = new HasReservationCourseResult();

        UserTokenInfo userTokenInfo = userService.getUserTokenInfo(param.getToken());

        ArrayList<CourseAndTimetableInfo> courseAndTimetableInfos = courseMapper.getHasReservationCourse(userTokenInfo.getOpenid());

        for (CourseAndTimetableInfo courseAndTimetableInfo : courseAndTimetableInfos) {
            ArrayList<TimetableInfo> hasReservationTimetables = courseMapper.getHasReservationTimetables(userTokenInfo.getOpenid(), courseAndTimetableInfo.getId());
            courseAndTimetableInfo.setTimetableInfos(hasReservationTimetables);
        }

        result.setCourseInfos(courseAndTimetableInfos);
        return result;
    }
}
