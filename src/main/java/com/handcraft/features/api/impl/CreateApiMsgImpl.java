package com.handcraft.features.api.impl;

import cn.hutool.core.text.StrBuilder;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  //  http://fundgz.1234567.com.cn/js/001186.js  接口实例
    //http://hq.sinajs.cn/list=s_sh000001
    private String aipfundinfo = "http://fundgz.1234567.com.cn/js/";
    private String aipfundboard = "http://hq.sinajs.cn/list=";

    private String aipnewBotAi = "https://api.ownthink.com/bot?spoken=";

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
           for(FundInfo fundInfo:fundInfos){
               fundInfo.getFundCode();

               String sourString= msgCreate.okHttpGetMethod(aipfundinfo+fundInfo.getFundCode()+".js");

               String  sourString2 = sourString.split("gz\\(")[1];
               JSONObject obj = JSONObject.parseObject(sourString2.split("\\);")[0]);
               String name = obj.getString("name");
               String growth = obj.getString("gszzl");
               String foundCode = obj.getString("fundcode");

               outputstr.append("\n"+name);
               outputstr.append("("+foundCode+")：");
               outputstr.append(growth+"%");
           }
       }
        String dapanString= msgCreate.okHttpGetMethod(aipfundboard+"s_sh000001");
      String dapanString2 = dapanString.split("\"")[1];
        outputstr.append("\n"+dapanString2.split(",")[0]+"  "+
                dapanString2.split(",")[1]+"  "+dapanString2.split(",")[3]+"%");
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

    @Override
    public StringBuffer getnewBotAI(String talk) {
        String sourString= msgCreate.okHttpGetMethod(aipnewBotAi+talk);
        JSONObject obj = JSONObject.parseObject(sourString);
        JSONObject content = obj.getJSONObject("data");
        JSONObject info = content.getJSONObject("info");
        String text = info.getString("text");
        StringBuffer res =new StringBuffer();
        res.append(text);
        return res;
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
