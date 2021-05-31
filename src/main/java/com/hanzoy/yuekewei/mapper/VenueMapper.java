package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.VenueInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Venue;

import java.util.List;

public interface VenueMapper {
    List<VenueInfo> getVenue();

    void insertVenue(Venue venue);
}
