package com.hanzoy.yuekewei.pojo.dto.result;

import com.hanzoy.yuekewei.pojo.po.CoachInfo;
import lombok.Data;

import java.util.List;

@Data
public class GetCoachResult {
    private List<CoachInfo> coachInfo;
}
