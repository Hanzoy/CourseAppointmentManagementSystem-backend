package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.ManageSwiperInfo;
import com.hanzoy.yuekewei.pojo.po.PictureInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Picture;
import com.hanzoy.yuekewei.pojo.po.entity.SwiperPicture;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface PictureMapper {

    List<String> getSwiperPictureUrl();

    void insertPicture(Picture picture);

    ArrayList<PictureInfo> getAllPicture();

    Integer getIdByUrl(@Param("url") String url);

    ArrayList<ManageSwiperInfo> manageGetSwiper();

    void changeSwiperPicture(@Param("pictureId") Integer pictureId, @Param("swiperId") Integer swiperId);

    void insertSwiper(SwiperPicture picture);

    void deleteSwiper(@Param("id") Integer id);

    void editSwiper(@Param("id") Integer id, @Param("name") String name, @Param("remark") String remark);

    void changeCoachAvatarUrl(@Param("pictureId") Integer pictureId, @Param("coachId") Integer coachId);

    void changeCoachBackgroundUrl(@Param("pictureId") Integer pictureId, @Param("coachId") Integer coachId);
}
