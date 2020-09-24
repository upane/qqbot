package com.handcraft.listener.power;


import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.beans.types.KeywordMatchType;
import com.forte.qqrobot.sender.MsgSender;
import com.handcraft.anno.Ban;
import com.handcraft.features.Enum.FunEnum;
import org.springframework.stereotype.Component;

/**
 * 权限操作
 * 1.开启/关闭 本群 某功能
 * 2.封禁/解封 某人的使用权限
 * 3.
 */
@Component
public class PowerListener {



    @Ban(types = {FunEnum.FUNCTION_SETU},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 色图"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanSe(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_SETU},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 色图"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanSe(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_CALENDAR},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 黄历"})
    @Listen(MsgGetTypes.groupMsg)
    public void BANcal(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_CALENDAR},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 黄历"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBancal(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_SWEET},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 舔狗"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanDog(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_SWEET},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 舔狗"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanDog(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_ABOUT_ME},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 今天的我"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanMe(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_ABOUT_ME},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 今天的我"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanMe(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_WANGYIYUN},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 网易云"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanYun(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_WANGYIYUN},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 网易云"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanYun(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_NEWS},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 新闻"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanNews(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_NEWS},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 新闻"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanNews(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_HOLIDAY},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 放假"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanHoliday(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {FunEnum.FUNCTION_HOLIDAY},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 放假"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanHoliday(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_QQAITALK},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 智障"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanAI(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_QQAITALK},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 智障"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanAI(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_FUNDS},status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 基金"})
    @Listen(MsgGetTypes.groupMsg)
    public void Banfund(GroupMsg msg, MsgSender sender){
    }
    @Ban(types = {FunEnum.FUNCTION_FUNDS},status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 基金"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanfund(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {
            FunEnum.FUNCTION_SETU,FunEnum. FUNCTION_CALENDAR,
            FunEnum.FUNCTION_SWEET,FunEnum.FUNCTION_ABOUT_ME,
            FunEnum.FUNCTION_WANGYIYUN,FunEnum.FUNCTION_NEWS,
            FunEnum.FUNCTION_HOLIDAY,FunEnum. FUNCTION_QQAITALK,
            FunEnum.FUNCTION_FUNDS
    },status = 0)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"关闭 所有"})
    @Listen(MsgGetTypes.groupMsg)
    public void BanAll(GroupMsg msg, MsgSender sender){
    }

    @Ban(types = {
            FunEnum.FUNCTION_SETU,FunEnum. FUNCTION_CALENDAR,
            FunEnum.FUNCTION_SWEET,FunEnum.FUNCTION_ABOUT_ME,
            FunEnum.FUNCTION_WANGYIYUN,FunEnum.FUNCTION_NEWS,
            FunEnum.FUNCTION_HOLIDAY,FunEnum. FUNCTION_QQAITALK,
            FunEnum.FUNCTION_FUNDS
    },status = 1)
    @Filter(keywordMatchType = KeywordMatchType.TRIM_CONTAINS,value = {"开启 所有"})
    @Listen(MsgGetTypes.groupMsg)
    public void NotBanAll(GroupMsg msg, MsgSender sender){
    }


}
