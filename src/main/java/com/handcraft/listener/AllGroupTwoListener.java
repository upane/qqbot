package com.handcraft.listener;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.template.OnGroup;
import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.anno.Check;
import com.handcraft.features.Enum.FunEnum;
import com.handcraft.features.api.CreateApiMsg;
import com.handcraft.features.pixiv.PixivMsg;
import com.handcraft.features.qqAi.QQAiTalk;
import com.handcraft.features.repeat.RepeatTalk;
import com.handcraft.mapper.ImgInfoMapper;
import com.handcraft.pojo.ImgInfo;
import com.handcraft.pojo.LocalPic;
import com.handcraft.pojo.MHoliday;
import com.handcraft.service.FundInfoService;
import com.handcraft.service.LocalPicService;
import com.handcraft.service.MHolidayService;
import com.handcraft.util.ImgDownload;
import com.handcraft.util.MsgCreate;
import com.handcraft.util.StringUtil;
import com.simplerobot.modules.utils.KQCodeUtils;
import lombok.extern.log4j.Log4j2;
import net.mamoe.mirai.qqandroid.network.protocol.data.proto.MultiMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 *新的一页群功能
 */
@Component
@OnGroup
@Log4j2
public class AllGroupTwoListener {

    CQCodeUtil cqCodeUtil = CQCodeUtil.build();
    @Autowired
    FundInfoService fundInfoService;
    @Resource
    CreateApiMsg createApiMsg;
    @Resource
    RepeatTalk repeatTalk;

    @Check(type = FunEnum.FUNCTION_FUNDS)
    @Filter(value = {"基金"})
    public void jijing(GroupMsg msg, MsgSender sender) {
        sender.SENDER.sendGroupMsg(msg,"增加：订阅 12345\n" +
                "删除：退订 12345\n" +
                "查询：韭菜");
    }

    @Check(type = FunEnum.FUNCTION_FUNDS)
    @Filter(value = {"韭菜"})
    public void funinfo(GroupMsg msg, MsgSender sender) {
        try {
            StringBuffer str = createApiMsg.getFundInfos(msg.getQQCode());
            sender.SENDER.sendGroupMsg(msg, cqCodeUtil.getCQCode_At(msg.getQQCode()) + " " + str);
        }catch (Exception e){
            sender.SENDER.sendGroupMsg(msg, "error");
        }
    }

    @Check(type = FunEnum.FUNCTION_FUNDS)
    @Filter(value = {"订阅.*"})
    public void addfun(GroupMsg msg, MsgSender sender) {
       String[] msgMsg= msg.getMsg().split(" ");
        try {
            fundInfoService.add(msg.getQQCode(),msgMsg[1]);
            sender.SENDER.sendGroupMsg(msg,"订阅成功！");
        } catch (Exception e) {
            e.printStackTrace();
            sender.SENDER.sendGroupMsg(msg,"订阅失败！");
        }

    }
    @Check(type = FunEnum.FUNCTION_FUNDS)
    @Filter(value = {"退订.*"})
    public void delfund(GroupMsg msg, MsgSender sender) {
        String[] msgMsg= msg.getMsg().split(" ");
        try {
            fundInfoService.delete(msg.getQQCode(),msgMsg[1]);
            sender.SENDER.sendGroupMsg(msg,"退订成功！");
        } catch (Exception e) {
            e.printStackTrace();
            sender.SENDER.sendGroupMsg(msg,"退订失败！");
        }
    }
    @Filter
    public void repeat(GroupMsg msg, MsgSender sender) {
        String groupCode = msg.getGroupCode();
        boolean judge = repeatTalk.judge(msg);
        if (judge) {
            sender.SENDER.sendGroupMsg(msg, msg.getMsg());
        }
    }
}