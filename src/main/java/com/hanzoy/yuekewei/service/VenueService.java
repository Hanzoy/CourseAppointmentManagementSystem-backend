package com.hanzoy.yuekewei.service;

import com.hanzoy.yuekewei.pojo.dto.param.AddVenueParam;
import com.hanzoy.yuekewei.pojo.dto.param.DeleteVenueParam;
import com.hanzoy.yuekewei.pojo.dto.param.EditVenueParam;
import com.hanzoy.yuekewei.pojo.dto.result.AddVenueResult;
import com.hanzoy.yuekewei.pojo.dto.result.DeleteVenueResult;
import com.hanzoy.yuekewei.pojo.dto.result.EditVenueResult;
import com.hanzoy.yuekewei.pojo.dto.result.GetVenueResult;

/**
 * 场地业务层
 */
public interface VenueService {
    /**
     * 获取场馆信息
     * @return 教练信息返回包装类
     */
    GetVenueResult getVenue();

    AddVenueResult addVenue(AddVenueParam param);

    EditVenueResult editVenue(EditVenueParam param);

    DeleteVenueResult deleteVenue(DeleteVenueParam param);
}
