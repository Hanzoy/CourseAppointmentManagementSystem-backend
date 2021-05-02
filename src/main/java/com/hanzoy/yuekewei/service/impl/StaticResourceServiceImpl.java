package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.yuekewei.mapper.CoachMapper;
import com.hanzoy.yuekewei.mapper.PictureMapper;
import com.hanzoy.yuekewei.pojo.dto.result.GetCoachResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetSwiperResult;
import com.hanzoy.yuekewei.pojo.po.CoachInfo;
import com.hanzoy.yuekewei.service.StaticResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StaticResourceServiceImpl implements StaticResourceService {
    @Resource
    PictureMapper pictureMapper;


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


}
