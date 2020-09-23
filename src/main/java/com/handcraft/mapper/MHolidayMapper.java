package com.handcraft.mapper;

import com.handcraft.pojo.LocalPic;
import com.handcraft.pojo.MHoliday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MHolidayMapper {

    int add(MHoliday mHoliday);

    int delete(@Param("qqCode") String qqcode,@Param("holiday") String name);

    List<MHoliday> selectByqqcode(@Param("qqCode") String qqcode);
}
