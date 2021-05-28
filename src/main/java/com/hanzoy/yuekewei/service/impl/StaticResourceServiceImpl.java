package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.yuekewei.exception.myExceptions.TokenErrorException;
import com.hanzoy.yuekewei.mapper.CoachMapper;
import com.hanzoy.yuekewei.mapper.PictureMapper;
import com.hanzoy.yuekewei.pojo.dto.param.GetAllPictureParam;
import com.hanzoy.yuekewei.pojo.dto.result.GetAllPictureResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetSwiperResult;
import com.hanzoy.yuekewei.pojo.po.CoachInfo;
import com.hanzoy.yuekewei.pojo.po.PictureInfo;
import com.hanzoy.yuekewei.service.ManageService;
import com.hanzoy.yuekewei.service.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaticResourceServiceImpl implements StaticResourceService {
    @Resource
    PictureMapper pictureMapper;

    @Autowired
    ManageService manageService;

    @Override
    public GetSwiperResult getSwiper() {
        //查找滑块图片地址
        List<String> swiperPictureUrl = pictureMapper.getSwiperPictureUrl();
        //创建返回类型
        GetSwiperResult result = new GetSwiperResult();
        //将滑块地址写入返回对象中
        result.setPictureUrl(swiperPictureUrl);
        return result;
    }

    @Override
    public GetAllPictureResult getAllPicture(GetAllPictureParam param) {
        //拦截非管理员token
        if(manageService.getAdminTokenInfo(param.getToken()).getId() == null){
            throw new TokenErrorException("未识别token");
        }

        //创建返回对象
        GetAllPictureResult result = new GetAllPictureResult();

        //数据库查询所有图片
        ArrayList<PictureInfo> allPicture = pictureMapper.getAllPicture();

        //将结果写入返回对象
        result.setPictures(allPicture);
        return result;
    }


}
