package com.hanzoy.yuekewei.mapper;

import com.hanzoy.yuekewei.pojo.po.CoachInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoachMapper {

    List<CoachInfo> getCoach();

    void deleteCoach(@Param("id") Integer id);

    void insertCoach(CoachInfo coachInfo);

    void editCoach(@Param("id") Integer id, @Param("name") String name, @Param("content") String content, @Param("remark") String remark);
}
