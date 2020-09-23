package com.handcraft.service.impl;

import com.handcraft.mapper.LocalPicMapper;
import com.handcraft.pojo.LocalPic;
import com.handcraft.service.LocalPicService;
import com.handcraft.util.StringUtil;
import com.simplerobot.modules.delay.DelaySender;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LocalPicServiceImpl implements LocalPicService {

    @Autowired
    LocalPicMapper localPicMapper;

    @Override
    public LocalPic selectone() {
       return  localPicMapper.queryone();
    }

    @Override
    public LocalPic selectonese() {
        return  localPicMapper.queryonese();
    }

    @Override
    public void delete(LocalPic localPic) {
         localPicMapper.delete(localPic.getUUID());
    }

    @Override
    public String batchadd(String filename,Integer pictype) {
        byte a;
        File[] fdir ;
        try{
            fdir = new File(System.getProperty("user.dir") + "/"+filename+"/" ).listFiles();
            List<LocalPic> localPics = new ArrayList<LocalPic>();
            for(int i =0;i<fdir.length;i++){
                LocalPic localPic = new LocalPic();
                localPic.setUUID(StringUtil.getUUID());
                localPic.setImgPath(fdir[i].toString());
                if(pictype==null){
                     a = 0;
                }else if (pictype==2){
                     a = 2;
                } else if(pictype==1){
                     a = 1;
                }else {
                    a=0;
                }
                localPic.setPicType(a);
                localPic.setCreateTime(new Date());
                localPics.add(localPic);
            }
            localPicMapper.batchadd(localPics);

            return "数据读取成功";
        }
        catch (Exception e){
            if(e instanceof DuplicateKeyException) {
                return "图片数据有重复，sql执行失败";
            }
            e.printStackTrace();
            String[] azz = new File(System.getProperty("user.dir")).list();
            StringBuffer output = new StringBuffer();
            for(int i =0 ;i<azz.length;i++){
                output.append(azz[i]+"</br>");
            }
            return "SHIT SHIT SHIT 数据读取失败ZZZZZZZZ</br>"+output.toString()
                    +System.getProperty("/" +
                    "see"+"/") ;
        }
    }

    private FileInputStream query_getPhotoImageBlob(String adress) {
        FileInputStream is = null;
        File filePic = new File(adress);
        try {
            is = new FileInputStream(filePic);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return is;
    }
}
