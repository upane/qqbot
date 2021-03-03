package com.handcraft.timeTasks;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.bot.BotManager;
import com.forte.qqrobot.bot.BotSender;
import com.handcraft.features.api.CreateApiMsg;
import com.handcraft.mapper.LocalPicMapper;
import com.handcraft.pojo.ImgInfo;
import com.handcraft.pojo.LocalPic;
import com.handcraft.util.ImgDownload;
import com.handcraft.util.MsgCreate;
import com.handcraft.util.StringUtil;
import kotlinx.serialization.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Component
@EnableScheduling
public class jocker {
    /**
     * 每日简报 定时任务
     * author：pan
     *
     */

    @Autowired
    CreateApiMsg createApiMsg;
    @Resource
    private BotManager botManager;
    @Resource
    MsgCreate msgCreate;

    @Value("${QQGroup.code}")
    private String QQ_GROUP_CODE ;

    @Value("${linux_path}")
    private String linux_path ;
    @Resource
    StringUtil stringUtil;
    @Resource
    ImgDownload imgDownload;

    @Autowired
    LocalPicMapper localPicMapper;

    @Scheduled(cron = "0 05 12 * * ? ")
    public void everyDayNews(){

        String url = "https://api.tophub.fun/v2/GetAllInfoGzip?id=135&page=0";
        String url2 = "https://api.tophub.fun/v2/GetAllInfoGzip?id=136&page=0";
        downlodjocker(url,3);
        downlodjocker(url2,4);
    }

//    @Scheduled(cron = " 0 10 18 * * ? ")
//    public void everyLOLday(){
//
//        BotSender sender = botManager.defaultBot().getSender();
//        String dayMsg = msgCreate.getDayMsg();
//        sender.SENDER.sendGroupMsg(QQ_GROUP_CODE, dayMsg);
//    }


private void downlodjocker(String url,Integer kind){
//    String url = "https://api.tophub.fun/v2/GetAllInfoGzip?id=135&page=0";
    String str = msgCreate.okHttpGetMethod(url);
    JSONObject jsonObject = JSON.parseObject(str);
    JSONObject jsonObject2 =   jsonObject.getJSONObject("Data");
    JSONArray jsonArray = jsonObject2.getJSONArray("data");
    for (int i=0;i<jsonArray.size();i++) {
        JSONObject info =jsonArray.getJSONObject(i);
        //图片下载  数据库存储
        String id =info.getString("id");
        String followTime =info.getString("CreateTime");
        String imgurl =info.getString("Url");
        String[]  name = imgurl.split("/");
        String name2 =name[name.length-1];
        try {
            if(kind.equals(4)){
                StringBuffer buf = new StringBuffer();
                        buf.append("http:").append(imgurl);
                imgDownload.download(buf.toString(), linux_path, name2);
            }else {
                imgDownload.download(imgurl, linux_path, name2);
            }
            LocalPic recod =new LocalPic();
            recod.setUUID(StringUtil.getUUID());
            recod.setCreateTime(new Date());
            recod.setImgPath(linux_path+name2);
            recod.setPicType(kind);
            localPicMapper.insert(recod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






}
