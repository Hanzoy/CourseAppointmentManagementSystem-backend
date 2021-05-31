package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.VenueInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Venue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VenueMapper {
    List<VenueInfo> getVenue();

    void insertVenue(Venue venue);

    void editVenue(@Param("id") Integer id, @Param("name") String name, @Param("address") String address);

    void deleteVenue(@Param("id") Integer id);

    void changeVenuePicture(@Param("pictureId") Integer pictureId, @Param("id") Integer id);
}
