package com.handcraft.features.api.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.features.api.CreateApiMsg;
import com.handcraft.pojo.FundInfo;
import com.handcraft.service.FundInfoService;
import com.handcraft.util.MsgCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CreateApiMsgImpl implements CreateApiMsg {
    @Resource
    MsgCreate msgCreate;
    @Autowired
    FundInfoService fundInfoService;

    private String aipUrl = "https://s.nmsl8.club/getloveword?type=";
    private String aipUrl2 = "https://api.66mz8.com/api/social.php?format=json";
    private String aipUrlwuwu = "https://api.66mz8.com/api/sweet.php?format=json";
    private String aipUrlmusic = "https://api.66mz8.com/api/music.163.php?format=json";
    private String aipEveryDayNews = "https://news.topurl.cn/api";

    private String aipfundinfo = "https://api.doctorxiong.club/v1/fund?code=";
    private String aipfundboard = "https://api.doctorxiong.club/v1/stock/board";

    private CQCodeUtil cqCodeUtil = CQCodeUtil.build();

    @Override
    public String getmusic() {
        return formatmusic(msgCreate.okHttpGetMethod(aipUrlmusic));
    }

    @Override
    public String getSpiritGuy() {
        return format2(msgCreate.okHttpGetMethod(aipUrl2));
    }

    @Override
    public String getwuwuwu() {
        return wuwuwu(msgCreate.okHttpGetMethod(aipUrlwuwu));
    }

    @Override
    public String getSweet() {
        return format(msgCreate.okHttpGetMethod(aipUrl + "1"));
    }

    @Override
    public String getSmelly() {
        return format(msgCreate.okHttpGetMethod(aipUrl + "2"));
    }

    @Override
    public String getTianGou() {
        return format(msgCreate.okHttpGetMethod("http://api.yyhy.me/tg.php?type=api"));
    }

    @Override
    public String getPoisonousChickenSoup() {

        return format(msgCreate.okHttpGetMethod(aipUrl + "4"));
    }

    @Override
    public StringBuffer getEveryDayNews() {
        String sourString= msgCreate.okHttpGetMethod(aipEveryDayNews);
        JSONObject obj = JSONObject.parseObject(sourString);
        JSONObject content = obj.getJSONObject("data");
        JSONArray araynews = content.getJSONArray("newsList");
        StringBuffer outputstr =new StringBuffer();
        outputstr.append(cqCodeUtil.getCQCode_Face("202")+"今日读报\n\n");
        for (int i =0;i<araynews.size();i++){
            JSONObject acc =araynews.getJSONObject(i);
            outputstr.append(i+1+"、"+acc.getString("title")+"\n");
        }
        return outputstr;

    }

    @Override
    public StringBuffer getFundInfos(String qqcode) {
        StringBuffer outputstr =new StringBuffer();
       List<FundInfo> fundInfos= fundInfoService.selectFunds(qqcode);
       if(fundInfos==null){
           outputstr.append(" ");
       }else {
           String getinfo="";
           for(FundInfo fundInfo:fundInfos){
               fundInfo.getFundCode();
               getinfo+=fundInfo.getFundCode()+",";
           }
           String sourString= msgCreate.okHttpGetMethod(aipfundinfo+getinfo);
           JSONObject obj = JSONObject.parseObject(sourString);
           JSONArray araynews = obj.getJSONArray("data");
           for (int i =0;i<araynews.size();i++){
               JSONObject acc =araynews.getJSONObject(i);
               outputstr.append("\n"+acc.getString("name"));
               outputstr.append("("+acc.getString("code")+")：");
               outputstr.append(acc.getString("expectGrowth"));
           }
       }
        String dapanString= msgCreate.okHttpGetMethod(aipfundboard);
        JSONObject obj2 = JSONObject.parseObject(dapanString);
        JSONArray araynews2 = obj2.getJSONArray("data");
        JSONObject acc =araynews2.getJSONObject(0);
        outputstr.append("\n"+acc.getString("name")+"  ");
        outputstr.append(acc.getString("price")+"  ");
        outputstr.append(acc.getString("changePercent")+"%");

        return outputstr;
    }

    @Override
    public StringBuffer getEveryDayNewsDeatil() {
        String sourString= msgCreate.okHttpGetMethod(aipEveryDayNews);
        JSONObject obj = JSONObject.parseObject(sourString);
        JSONObject content = obj.getJSONObject("data");
        JSONArray araynews = content.getJSONArray("newsList");
        StringBuffer outputstr =new StringBuffer();
        outputstr.append("每日新闻Details\n\n");
        for (int i =0;i<araynews.size();i++){
            JSONObject acc =araynews.getJSONObject(i);
            outputstr.append(i+1+"、"+acc.getString("title")+"\n");
            outputstr.append(i+1+"、"+acc.getString("url")+"\n\n");
        }
        return outputstr;
    }

    @Override
    public String getTodayMe(String code, String name) {
        StringBuffer stringBuffer = new StringBuffer("http://forte.love:15520/me?");
        stringBuffer.append("code=").append(code);
        stringBuffer.append("&name=").append(name);
        return msgCreate.okHttpGetMethod(stringBuffer.toString());
    }

    private static String format(String Json) {
        JSONObject obj = JSONObject.parseObject(Json);
        String content = obj.getString("content");
        content.replace("\\", "\\\\");
        return content;
    }
    private static String format2(String Json) {
        JSONObject obj = JSONObject.parseObject(Json);
        String content = obj.getString("social");
        content.replace("\\", "\\\\");
        return content;
    }
    private static String wuwuwu(String Json) {
        JSONObject obj = JSONObject.parseObject(Json);
        String content = obj.getString("sweet");
        content.replace("\\", "\\\\");
        return content;
    }

    private static String formatmusic(String Json) {
        JSONObject obj = JSONObject.parseObject(Json);
        String content = obj.getString("name");
        String artists_name = obj.getString("artists_name");
        String comments = obj.getString("comments");
        String comment =content+"\n"+artists_name+"\n"+comments;
        return comment;
    }

}
