package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.PictureInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetAllPictureResult {
    private ArrayList<PictureInfo> pictures;
}
