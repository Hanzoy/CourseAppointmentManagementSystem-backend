package com.hanzoy.yuekewei.controller;

import com.hanzoy.yuekewei.mapper.CourseMapper;
import com.hanzoy.yuekewei.mapper.PictureMapper;
import com.hanzoy.yuekewei.pojo.dto.CommonResult;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.entity.Picture;
import com.hanzoy.yuekewei.service.CourseService;
import com.hanzoy.yuekewei.utils.aliyunOSSUtils.AliyunOSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

@Api("课程管理")
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Resource
    PictureMapper pictureMapper;

    @Resource
    CourseMapper courseMapper;

    @ApiOperation("查询当前用户购买了课时的课程安排")
    @PostMapping("/getMyCourseInfo")
    public CommonResult<GetMyCourseInfoResult> getMyCourseInfo(@RequestBody @Validated GetMyCourseInfoParam param){
        GetMyCourseInfoResult result = courseService.getMyCourseInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查询选定时间当前用户能预约的课程安排")
    @PostMapping("/getReservationCourseInfo")
    public CommonResult<GetReservationCourseInfoResult> getReservationCourseInfo(@RequestBody @Validated GetReservationCourseInfoParam param){
        GetReservationCourseInfoResult result = courseService.getReservationCourseInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("根据id查询课程预约信息")
    @PostMapping("/getCourseInfo")
    public CommonResult<GetCourseInfoResult> getCourseInfo(@RequestBody @Validated GetCourseInfoParam param){
        GetCourseInfoResult result = courseService.getCourseInfo(param);
        return CommonResult.success(result);
    }

    @ApiOperation("课程预定")
    @PostMapping("/reservationCourse")
    public CommonResult<ReservationCourseResult> reservationCourse(@RequestBody @Validated ReservationCourseParam param){
        ReservationCourseResult result = courseService.reservationCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("用户查询已预约的课程")
    @PostMapping("/hasReservationCourse")
    public CommonResult<HasReservationCourseResult> hasReservationCourse(@RequestBody @Validated HasReservationCourseParam param){
        HasReservationCourseResult result = courseService.hasReservationCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查询所有课程")
    @PostMapping("/getCourse")
    public CommonResult<GetCourseResult> getCourse(@RequestBody @Validated GetCourseParam param){
        GetCourseResult result = courseService.getCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("增加课程")
    @PostMapping("/addCourse")
    public CommonResult<AddCourseResult> addCourse(@RequestBody @Validated AddCourseParam param){
        AddCourseResult result = courseService.addCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改课程")
    @PostMapping("/editCourse")
    public CommonResult<EditCourseResult> editCourse(@RequestBody @Validated EditCourseParam param){
        EditCourseResult result = courseService.editCourse(param);
        return CommonResult.success(result);
    }

    @ApiOperation("删除课程")
    @PostMapping("/deleteCourse")
    public CommonResult<DeleteCourseResult> deleteCourse(@RequestBody @Validated DeleteCourseParam param){
        DeleteCourseResult result = courseService.deleteCourse(param);
        return CommonResult.success(result);
    }
    @ApiOperation("上传并修改course图片")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String upLoadVenue(@RequestParam("file") MultipartFile file, @RequestParam( value = "id", required = false)Integer id) {
        String fileName = file.getOriginalFilename();
        String uploadUrl = null;
        try {
            if (file != null) {
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliyunOSSUtil.upLoad(newFile);
                    newFile.delete();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        pictureMapper.insertPicture(new Picture(null, uploadUrl, null));
        Integer pictureId = pictureMapper.getIdByUrl(uploadUrl);
//        pictureMapper.changeSwiperPicture(pictureId, id);
        courseMapper.changePicture(pictureId, id);
        return uploadUrl;
    }

    @ApiOperation("查询当前选定月份所有的课表信息")
    @PostMapping("/getTimetableByYearAndMonth")
    public CommonResult<GetTimetableByYearAndMonthResult> getTimetableByYearAndMonth(@RequestBody @Validated GetTimetableByYearAndMonthParam param){
        GetTimetableByYearAndMonthResult result = courseService.getTimetableByYearAndMonth(param);
        return CommonResult.success(result);
    }

    @ApiOperation("查询当前选定日期和课程下的所有课表信息")
    @PostMapping("/getTimetableByDateAndCourseId")
    public CommonResult<GetTimetableByDateAndCourseIdResult> getTimetableByDateAndCourseId(@RequestBody @Validated GetTimetableByDateAndCourseIdParam param){
        GetTimetableByDateAndCourseIdResult result = courseService.getTimetableByDateAndCourseId(param);
        return CommonResult.success(result);
    }

    @ApiOperation("添加timetable")
    @PostMapping("/addTimetable")
    public CommonResult<AddTimetableResult> addTimetable(@RequestBody @Validated AddTimetableParam param){
        AddTimetableResult result = courseService.addTimetable(param);
        return CommonResult.success(result);
    }

    @ApiOperation("删除timetable")
    @PostMapping("/deleteTimetable")
    public CommonResult<DeleteTimetableResult> deleteTimetable(@RequestBody @Validated DeleteTimetableParam param){
        DeleteTimetableResult result = courseService.deleteTimetable(param);
        return CommonResult.success(result);
    }

    @ApiOperation("修改timetable")
    @PostMapping("/editTimetable")
    public CommonResult<EditTimetableResult> editTimetable(@RequestBody @Validated EditTimetableParam param){
        EditTimetableResult result = courseService.editTimetable(param);
        return CommonResult.success(result);
    }
}
