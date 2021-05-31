package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.ManageSwiperInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ManageGetSwiperResult {
    ArrayList<ManageSwiperInfo> swiperInfo;
}
