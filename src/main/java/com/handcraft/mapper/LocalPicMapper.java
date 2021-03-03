package com.handcraft.mapper;

import com.handcraft.pojo.LocalPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocalPicMapper {

    int batchadd(List<LocalPic> localPics);

    int insert(LocalPic record);

    int delete(String uuid);

    LocalPic queryOneByKind(@Param("picType") String picType);


}
