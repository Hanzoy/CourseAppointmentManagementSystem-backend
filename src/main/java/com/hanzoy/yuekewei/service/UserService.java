package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.bo.UserTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.entity.Users;

public interface UserService {

    /**
     * 用户通过code登陆
     * @param param 参数
     * @return 返回对象
     */
    UserLoginResult userLogin(UserLoginParam param);

    /**
     * 业务封装方法，将User内容写入token
     * @param users 需要解析的实体类
     * @return token
     */
    String writeUsersToToken(Users users);

    /**
     * 第一次登陆授权并完善信息
     * @param param 参数
     * @return 返回对象
     */
    UserRegisterResult userRegister(UserRegisterParam param);

    /**
     * 业务封装方法，解析token内容并返回一个UserTokenInfo的实体类
     * @param token 需要解析的token
     * @return 带有token信息的实体类
     */
    UserTokenInfo getUserTokenInfo(String token);

    /**
     * 检查token
     * @param param 需要检查的token
     * @return 返回包装对象
     */
    CheckTokenResult checkToken(CheckTokenParam param);

    /**
     * 修改个人信息
     * @param param
     * @return
     */
    ChangeInformationResult changeInformation(ChangeInformationParam param);

    GetUserInfoResult getUserInfo(GetUserInfoParam param);

    UpdateCourseTimeResult updateCourseTime(UpdateCourseTimeParam param);

    AddCourseTimeResult addCourseTime(AddCourseTimeParam param);
}
