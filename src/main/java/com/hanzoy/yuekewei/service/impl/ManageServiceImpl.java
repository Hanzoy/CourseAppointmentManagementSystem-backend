package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.utils.JWTUtils.JWTUtils;
import com.hanzoy.utils.MD5Utils.MD5Utils;
import com.hanzoy.yuekewei.exception.myExceptions.TokenErrorException;
import com.hanzoy.yuekewei.mapper.ManageMapper;
import com.hanzoy.yuekewei.mapper.UsersMapper;
import com.hanzoy.yuekewei.pojo.bo.AdminTokenInfo;
import com.hanzoy.yuekewei.pojo.bo.UserTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllCoursesParam;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllUsersParam;
import com.hanzoy.yuekewei.pojo.dto.param.ManageLoginParam;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllCoursesResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllUsersResult;
import com.hanzoy.yuekewei.pojo.dto.result.ManageLoginResult;
import com.hanzoy.yuekewei.pojo.po.CourseAndUserInfo;
import com.hanzoy.yuekewei.pojo.po.UserCourseTimeInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Admin;
import com.hanzoy.yuekewei.pojo.po.entity.Users;
import com.hanzoy.yuekewei.service.ManageService;
import com.hanzoy.yuekewei.service.UserService;
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
}
