package com.handcraft.features.localPic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.pojo.LocalPic;
import com.handcraft.service.LocalPicService;
import com.handcraft.service.MsgTimeService;
import com.handcraft.util.StringUtil;
import kotlinx.serialization.json.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LocalPIcMsg {
    @Autowired
MsgTimeService msgTimeService;
@Autowired
LocalPicService localPicService;
    private static CQCodeUtil cqCodeUtil = CQCodeUtil.build();
     public static String okHttpGetMethod(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
         String aipEveryDayNews = "https://news.topurl.cn/api";
         String aaaa= okHttpGetMethod(aipEveryDayNews);

        StringBuffer outputstr =new StringBuffer();

         JSONObject obj = JSONObject.parseObject(aaaa);
        JSONObject content = obj.getJSONObject("data");
        JSONArray araynews = content.getJSONArray("newsList");



        outputstr.append(cqCodeUtil.getCQCode_Face("202")+"今日读报:\n");
        for (int i =0;i<araynews.size();i++){
            JSONObject acc =araynews.getJSONObject(i);
            outputstr.append(i+1+"、"+acc.getString("title")+"\n");
        }

        System.out.println(outputstr);
    }
}

