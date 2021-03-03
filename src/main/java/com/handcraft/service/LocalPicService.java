package com.handcraft.service;


import com.handcraft.pojo.LocalPic;


public interface LocalPicService {

      LocalPic queryOneByKind( String kind);

     void delete(LocalPic localPic);


     String batchadd(String filename,Integer pictype);

}
