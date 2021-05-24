package com.handcraft.features.api;

import cn.hutool.core.text.StrBuilder;
import com.alibaba.fastjson.JSONObject;
import com.handcraft.util.MsgCreate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public interface CreateApiMsg {

    /**
     *网抑云
     */
     String getmusic();
    /**
     * 精神小伙
     */
     String getSpiritGuy();
    /**
     * wuwuwu
     */
     String getwuwuwu();
    /**
     * 嘴甜
     */
     String getSweet();
    /**
     * 嘴臭
     */
     String getSmelly();
    /**
     * 舔狗
     */
     String getTianGou() ;
    /**
     * 毒鸡汤
     */
     String getPoisonousChickenSoup() ;

    /**
     * 每日简报
     */
    StringBuffer getEveryDayNews();
    StringBuffer getEveryDayNewsDeatil();

    /**
     * 韭菜
     */
    StringBuffer getFundInfos(String qqcode);

    /**
     * 思知机器人
     */
    StringBuffer getnewBotAI(String talk);

    //今天的我
    String getTodayMe(String code, String name) ;

    //
    String nbnhhsh( String key) ;
}
