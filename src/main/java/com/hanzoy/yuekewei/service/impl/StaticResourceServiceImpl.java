package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.yuekewei.exception.myExceptions.TokenErrorException;
import com.hanzoy.yuekewei.mapper.CoachMapper;
import com.hanzoy.yuekewei.mapper.PictureMapper;
import com.hanzoy.yuekewei.mapper.VenueMapper;
import com.hanzoy.yuekewei.pojo.dto.param.*;
import com.hanzoy.yuekewei.pojo.dto.result.*;
import com.hanzoy.yuekewei.pojo.po.CoachInfo;
import com.hanzoy.yuekewei.pojo.po.ManageSwiperInfo;
import com.hanzoy.yuekewei.pojo.po.PictureInfo;
import com.hanzoy.yuekewei.pojo.po.entity.SwiperPicture;
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

    @Override
    public ManageGetSwiperResult manageGetSwiper(ManageGetSwiperParam param) {
        ManageGetSwiperResult result = new ManageGetSwiperResult();

        ArrayList<ManageSwiperInfo> manageSwiperInfos = pictureMapper.manageGetSwiper();

        result.setSwiperInfo(manageSwiperInfos);
        return result;
    }

    @Override
    public AddSwiperResult addSwiper(AddSwiperParam param) {

        AddSwiperResult result = new AddSwiperResult();

        SwiperPicture picture = new SwiperPicture();
        picture.setName(param.getName());
        picture.setRemark(param.getRemark());
        pictureMapper.insertSwiper(picture);
        result.setId(picture.getId());
        return result;
    }

    @Override
    public DeleteSwiperResult deleteSwiper(DeleteSwiperParam param) {

        pictureMapper.deleteSwiper(param.getId());

        return null;
    }

    @Override
    public EditSwiperResult editSwiper(EditSwiperParam param) {
        pictureMapper.editSwiper(param.getId(), param.getName(), param.getRemark());
        return null;
    }


}
