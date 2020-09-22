package com.handcraft.service;


import com.handcraft.mapper.LocalPicMapper;
import com.handcraft.pojo.LocalPic;
import com.handcraft.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


public interface LocalPicService {

      LocalPic selectone();
      LocalPic selectonese();

     void delete(LocalPic localPic);


     String batchadd(String filename,Integer pictype);

}
