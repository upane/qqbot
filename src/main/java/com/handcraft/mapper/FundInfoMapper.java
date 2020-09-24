package com.handcraft.mapper;

import com.handcraft.pojo.FundInfo;
import com.handcraft.pojo.MHoliday;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FundInfoMapper {

    int add(FundInfo fundInfo);

    int delete(FundInfo fundInfo);

    List<FundInfo> selectByQQcode(@Param("qqCode") String qqcode);
}
