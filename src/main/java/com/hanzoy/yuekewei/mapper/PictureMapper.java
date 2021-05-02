package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.entity.Picture;

import java.util.List;

public interface PictureMapper {

    List<String> getSwiperPictureUrl();

    void insertPicture(Picture picture);
}
