package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.utils.ClassCopyUtils.ClassCopyUtils;
import com.hanzoy.utils.JWTUtils.JWTUtils;
import com.hanzoy.utils.MD5Utils.MD5Utils;
import com.hanzoy.yuekewei.exception.myExceptions.ParamErrorException;
import com.hanzoy.yuekewei.exception.myExceptions.TokenErrorException;
import com.hanzoy.yuekewei.mapper.CourseMapper;
import com.hanzoy.yuekewei.mapper.ManageMapper;
import com.hanzoy.yuekewei.mapper.UsersMapper;
import com.hanzoy.yuekewei.pojo.bo.AdminTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.CourseAndUserInfo;
import com.hanzoy.yuekewei.pojo.po.TimetableExampleInfo;
import com.hanzoy.yuekewei.pojo.po.UserCourseTimeInfo;
import com.hanzoy.yuekewei.pojo.po.UserInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Admin;
import com.hanzoy.yuekewei.pojo.po.entity.TimetableExample;
import com.hanzoy.yuekewei.pojo.po.entity.Users;
import com.hanzoy.yuekewei.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class ManageServiceImpl implements ManageService {

    @Resource
    ManageMapper manageMapper;

    @Resource
    UsersMapper usersMapper;

    @Resource
    CourseMapper courseMapper;

    @Autowired
    JWTUtils jwtUtils;

    @Override
    public GetAllCoursesResult getAllCourses(GetAllCoursesParam param) {
        //创建返回对象
        GetAllCoursesResult result = new GetAllCoursesResult();

        //获取token内容
        AdminTokenInfo tokenInfo = getAdminTokenInfo(param.getToken());

        //检查token
        if(tokenInfo.getId() == null){
            throw new TokenErrorException("未识别token");
        }

        //查询所有的课程
        ArrayList<CourseAndUserInfo> courseAndUserInfos = manageMapper.getAllCourseInfo();

        //将参与其中的用户写入查询的课程中
        for (CourseAndUserInfo courseAndUserInfo : courseAndUserInfos) {
            ArrayList<UserCourseTimeInfo> userCourseInfoByCourseId = manageMapper.getUserCourseInfoByCourseId(courseAndUserInfo.getId());
            courseAndUserInfo.setUserCourseTimeInfo(userCourseInfoByCourseId);
        }

        //将结果写入返回对象中
        result.setCourseAndUserInfos(courseAndUserInfos);

        return result;
    }

    @Override
    public ManageLoginResult login(ManageLoginParam param) {
        //创建返回对象
        ManageLoginResult result = new ManageLoginResult();

        //验证密码
        Admin admin = manageMapper.getAdminByUsernameAndPassword(param.getUsername(), MD5Utils.MD5(param.getPassword()));
        if(admin == null){
            throw new TokenErrorException("账号密码错误");
        }
        result.setToken(writeAdminToToken(admin));
        return result;
    }

    @Override
    public String writeAdminToToken(Admin admin) {
        return jwtUtils.createTokenCustomFields(admin, "id", "username", "name");
    }

    @Override
    public AdminTokenInfo getAdminTokenInfo(String token) {
        return jwtUtils.getBean(token, AdminTokenInfo.class);
    }

    @Override
    public GetAllUsersResult getAllUsers(GetAllUsersParam param) {
        //创建返回对象
        GetAllUsersResult result = new GetAllUsersResult();

        //获取token内容
        AdminTokenInfo tokenInfo = getAdminTokenInfo(param.getToken());

        //检查token
        if(tokenInfo.getId() == null){
            throw new TokenErrorException("未识别token");
        }

        ArrayList<Users> users = usersMapper.getAllUsers();

        result.setUsers(users);

        return result;
    }

    @Override
    public ChangeUserInformationResult changeUserInformation(ChangeUserInformationParam param) {

        //获取token内容
        AdminTokenInfo tokenInfo = getAdminTokenInfo(param.getToken());

        //检查token
        if(tokenInfo.getId() == null){
            throw new TokenErrorException("未识别token");
        }

        usersMapper.updateInformation(param.getOpenid(), param.getName(), param.getPhone(), param.getRemark());

        return null;
    }

    @Override
    public GetAllUsersByKeyResult getAllUsersByKey(GetAllUsersByKeyParam param) {
        GetAllUsersByKeyResult result = new GetAllUsersByKeyResult();

        //获取token内容
        AdminTokenInfo tokenInfo = getAdminTokenInfo(param.getToken());

        //检查token
        if(tokenInfo.getId() == null){
            throw new TokenErrorException("未识别token");
        }

        ArrayList<Users> users = usersMapper.getAllUsersByKey(param.getKey());

        result.setUsers(users);

        return result;
    }

    @Override
    public GetUserInfoResult getUserInfo(GetUserInfoParam param) {

        GetUserInfoResult result = new GetUserInfoResult();

        //获取token内容
        AdminTokenInfo tokenInfo = getAdminTokenInfo(param.getToken());

        //检查token
        if(tokenInfo.getId() == null){
            throw new TokenErrorException("未识别token");
        }

        UserInfo userInfo = usersMapper.getUserInfo(param.getOpenid());
        userInfo.setCourse(courseMapper.getUserCourseInfo(param.getOpenid()));
        userInfo.setOperation(courseMapper.getUserOperationInfo(param.getOpenid()));

        result.setUserInfo(userInfo);
        return result;
    }

    @Override
    public GetEXTimetableResult getEXTimetable(GetEXTimetableParam param) {

        GetEXTimetableResult result = new GetEXTimetableResult();

        ArrayList<TimetableExampleInfo> timetableByWeekAndCourseId = manageMapper.getEXTimetableByWeekAndCourseId(param.getWeek(), param.getCourseId());

        result.setTimetableExampleInfos(timetableByWeekAndCourseId);

        return result;
    }

    @Override
    public AddEXTimetableResult addEXTimetable(AddEXTimetableParam param) {

        AddEXTimetableResult result = new AddEXTimetableResult();

        TimetableExample timetableExample = new TimetableExample();
        ClassCopyUtils.ClassCopy(timetableExample, param);
        System.out.println(timetableExample);
        manageMapper.insertEXTimetable(timetableExample);

        result.setId(timetableExample.getId());
        return result;
    }

    @Override
    public DeleteEXTimetableResult deleteEXTimetable(DeleteEXTimetableParam param) {
        manageMapper.deleteEXTimetable(param.getId());
        return null;
    }

    @Override
    public EditEXTimetableResult editEXTimetable(EditEXTimetableParam param) {
        if(param.getStartTime().compareTo(param.getEndTime())>0){
            throw new ParamErrorException("开始时间不应该大于结束时间");
        }
        manageMapper.editEXTimetable(
                param.getId(),
                param.getCourseId(),
                param.getAddress(),
                param.getEndTime(),
                param.getStartTime(),
                param.getRemark(),
                param.getWeek(),
                param.getToplimit(),
                param.getCoachId(),
                param.getCost());
        return null;
    }

    @Override
    public ChangePasswordResult changePassword(ChangePasswordParam param) {

        AdminTokenInfo adminTokenInfo = getAdminTokenInfo(param.getToken());

        Integer id = adminTokenInfo.getId();

        //验证密码
        Admin admin = manageMapper.getAdminByUsernameAndPassword(adminTokenInfo.getUsername(), MD5Utils.MD5(param.getOldPassword()));
        if(admin == null){
            throw new TokenErrorException("账号密码错误");
        }

        manageMapper.changePassword(id, MD5Utils.MD5(param.getNewPassword()));

        return null;
    }
}
