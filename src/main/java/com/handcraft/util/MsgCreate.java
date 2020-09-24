package com.handcraft.util;

import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.features.Enum.ProgrammerCalendar;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 信息文本拼接工具
 *
 * @author Heilant Gong
 */
@Component
public class MsgCreate {
    private CQCodeUtil cqCodeUtil = CQCodeUtil.build();


    /**
     * 生成菜单
     *
     * @return 菜单字符串
     */
    public String getMenu() {
        StringBuffer sb = new StringBuffer();
        sb.append(cqCodeUtil.getCQCode_Face("202")+"舒克舒克米其林餐厅"+cqCodeUtil.getCQCode_Face("202") + "\n");
        sb.append("1.黄历\n");
        sb.append("2.甜，舔，毒+@人\n");
        sb.append("3.二次元、三次元、se\n");
        sb.append("4.今天的我\n");
        sb.append("5.网易云、精神、wu\n");
        sb.append("6.每日读报、详情\n");
        sb.append("7.放假");

        return sb.toString();
    }


    /**
     * 每日清晨信息
     *
     * @return 每日信息
     */
    public String getDayMsg() {
        // 最终返回的文字
        StringBuilder str = new StringBuilder();
        // 确认今天是周几
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        //欢迎头
        str.append(dayHello(day));
        //老黄历
        str.append(ProgrammerCalendar.getCalendar());
        //日图
        //str.append("[CQ:image,file=" + day + ".jpg]");
        return str.toString();
    }


    /**
     * 老黄历
     *
     * @param key 为是否显示今日日期
     * @return 老黄历主体
     */
    public String getProgrammerCalendar(int... key) {
        return ProgrammerCalendar.getCalendar(key);
    }

    /**
     * @param day 星期信息
     * @return 每日信息头
     */
    private String dayHello(int day) {
        StringBuilder str = new StringBuilder();
        String[] arr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        str.append("各位晚上好,今天是");
        str.append(arr[day] + "\n");
        if (day > 5) {
            str.append("周末啦,出去散散心吧吧,或者来一把召唤师峡谷?\n");
        }
        return str.toString();
    }


    /**
     * Unicode转UTF-8
     *
     * @param str 需要转码的字符串
     * @return 转码后的字符
     */
    public String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;
    }

    /**
     * httpGet方法 包装
     *
     * @param url 访问的url
     * @return 返回值
     */
    public String okHttpGetMethod(String url) {
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

    /**
     * @param url  请求地址
     * @param json 上报内容
     * @return 返回值
     */
    public String okHttpPostMethod(String url, String json) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);

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

    public String getdata(String url, JSONObject jsonObject) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //1 . 拿到OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));
        //3 . 构建Request,将FormBody作为Post方法的参数传入
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}