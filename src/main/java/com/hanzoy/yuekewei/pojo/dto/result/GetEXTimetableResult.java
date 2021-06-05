package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.TimetableExampleInfo;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetEXTimetableResult {
    ArrayList<TimetableExampleInfo> timetableExampleInfos;
}
