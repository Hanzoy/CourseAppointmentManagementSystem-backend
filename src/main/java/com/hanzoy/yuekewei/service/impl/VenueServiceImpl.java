package com.hanzoy.yuekewei.service.impl;

import com.hanzoy.yuekewei.mapper.VenueMapper;
import com.hanzoy.yuekewei.pojo.dto.param.AddVenueParam;
import com.hanzoy.yuekewei.pojo.dto.result.AddVenueResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetVenueResult;
import com.hanzoy.yuekewei.pojo.po.VenueInfo;
import com.hanzoy.yuekewei.pojo.po.entity.Venue;
import com.hanzoy.yuekewei.service.VenueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {
    @Resource
    VenueMapper venueMapper;

    @Override
    public GetVenueResult getVenue() {
        //查询数据库
        List<VenueInfo> venueInfo = venueMapper.getVenue();
        //创建返回对象
        GetVenueResult result = new GetVenueResult();
        //写入返回对象中
        result.setVenueInfo(venueInfo);
        return result;
    }

    @Override
    public AddVenueResult addVenue(AddVenueParam param) {
        AddVenueResult result = new AddVenueResult();

        Venue venue = new Venue();
        venue.setAddress(param.getAddress());
        venue.setName(param.getName());
        venueMapper.insertVenue(venue);

        result.setId(venue.getId());
        return result;
    }
}
