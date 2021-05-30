package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.utils.ClassCopyUtils.ClassCopyUtils;
import com.hanzoy.utils.JWTUtils.JWTUtils;
import com.hanzoy.yuekewei.exception.myExceptions.WechatServerErrorException;
import com.hanzoy.yuekewei.mapper.UsersMapper;
import com.hanzoy.yuekewei.pojo.bo.UserTokenInfo;
import com.hanzoy.yuekewei.pojo.dto.param.ChangeInformationParam;
import com.hanzoy.yuekewei.pojo.dto.param.CheckTokenParam;
import com.hanzoy.yuekewei.pojo.dto.param.UserLoginParam;
import com.hanzoy.yuekewei.pojo.dto.param.UserRegisterParam;
import com.hanzoy.yuekewei.pojo.dto.result.ChangeInformationResult;
import com.hanzoy.yuekewei.pojo.dto.result.CheckTokenResult;
import com.hanzoy.yuekewei.pojo.dto.result.UserLoginResult;
import com.hanzoy.yuekewei.pojo.dto.result.UserRegisterResult;
import com.hanzoy.yuekewei.pojo.po.entity.Users;
import com.hanzoy.yuekewei.service.UserService;
import com.hanzoy.yuekewei.utils.WechatUtils.WechatUtils;
import com.hanzoy.yuekewei.utils.WechatUtils.dto.AuthorizationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    WechatUtils wechatUtils;

    @Resource
    UsersMapper usersMapper;

    @Autowired
    JWTUtils jwtUtils;

    @Override
    public UserLoginResult userLogin(UserLoginParam param) {
        //获取参数
        String code = param.getCode();

        //调取微信后端登陆接口
        AuthorizationResult authorizationResult = wechatUtils.js_codeToSession(code);

        //创建返回对象
        UserLoginResult result = new UserLoginResult();

        //判断是否通过微信接口拿到数据且没有报错
        if (authorizationResult != null && authorizationResult.getErrcode() == null){
            //获取openid
            String openid = authorizationResult.getOpenid();
            //通过openid查找对应的用户
            Users user = usersMapper.getUserByOpenid(openid);
            if (user == null){
                //没有找到用户说明还未进行权限认证

                //设置需要权限
                result.setNeedRegister(true);

                //设置token为null
                result.setToken(null);
            }else{
                //找到用户说明已完成权限认证

                //设置不需要权限认证
                result.setNeedRegister(false);

                //将openid与name写入token中
                String token = writeUsersToToken(user);
                //设置token
                result.setToken(token);
            }
        }else{
            //如果没有拿到微信数据则返回异常
            throw new WechatServerErrorException(authorizationResult != null ? authorizationResult.getErrmsg() : null);
        }
        //将返回对象写入data并返回
        return result;
    }

    @Override
    public String writeUsersToToken(Users users) {
        return jwtUtils.createTokenCustomFields(users, "openid", "name");
    }

    @Override
    public UserRegisterResult userRegister(UserRegisterParam param) {
        //获取参数
        String code = param.getCode();

        //调取微信后端登陆接口
        AuthorizationResult authorizationResult = wechatUtils.js_codeToSession(code);

        //创建返回对象
        UserRegisterResult result = new UserRegisterResult();

        //判断是否通过微信接口拿到数据且没有报错
        if (authorizationResult != null && authorizationResult.getErrcode() == null){
            //获取openid
            String openid = authorizationResult.getOpenid();
            //通过openid查找对应的用户
            Users user = usersMapper.getUserByOpenid(openid);
            if (user == null){
                //没有找到用户说明需要注册
                user = new Users();
                //拷贝 name, phone, nickName, avatarUrl
                ClassCopyUtils.ClassCopy(user, param);
                //设置openid;
                user.setOpenid(openid);
                //插入数据
                usersMapper.insertUser(user);
                //将openid与name写入token中
                String token = writeUsersToToken(user);
                //设置token
                result.setToken(token);
            }else{
                //找到用户说明不需要注册
                //将openid与name写入token中
                String token = writeUsersToToken(user);
                //设置token
                result.setToken(token);
            }
        }else{
            //如果没有拿到微信数据则返回异常
            throw new WechatServerErrorException(authorizationResult != null ? authorizationResult.getErrmsg() : null);
        }
        return result;
    }

    @Override
    public UserTokenInfo getUserTokenInfo(String token) {
        return jwtUtils.getBean(token, UserTokenInfo.class);
    }

    @Override
    public CheckTokenResult checkToken(CheckTokenParam param) {
        return null;
    }

    @Override
    public ChangeInformationResult changeInformation(ChangeInformationParam param) {

        UserTokenInfo userTokenInfo = getUserTokenInfo(param.getToken());

        usersMapper.updateInformation(userTokenInfo.getOpenid(), param.getName(), param.getPhone());
        return null;
    }
}
