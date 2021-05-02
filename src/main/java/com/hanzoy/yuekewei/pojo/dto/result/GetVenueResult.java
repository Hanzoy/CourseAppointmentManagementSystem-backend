package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.VenueInfo;
import lombok.Data;

import java.util.List;

@Data
public class GetVenueResult {
    private List<VenueInfo> venueInfo;
}
