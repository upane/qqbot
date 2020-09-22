package com.handcraft.mapper;

import com.handcraft.pojo.ImgInfo;
import com.handcraft.pojo.LocalPic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocalPicMapper {

    int batchadd(List<LocalPic> localPics);

    int delete(String uuid);

    LocalPic queryone();

    LocalPic queryonese();
}
