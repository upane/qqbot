package com.handcraft.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.template.OnGroup;
import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.beans.function.MostTypeFilter;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.types.MostType;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.features.api.CreateApiMsg;
import com.handcraft.features.pixiv.PixivMsg;
import com.handcraft.features.qqAi.QQAiTalk;
import com.handcraft.features.repeat.RepeatTalk;
import com.handcraft.mapper.ImgInfoMapper;
import com.handcraft.pojo.ImgInfo;
import com.handcraft.pojo.LocalPic;
import com.handcraft.service.LocalPicService;
import com.handcraft.util.ImgDownload;
import com.handcraft.util.MsgCreate;
import com.handcraft.util.StringUtil;
import com.simplerobot.modules.utils.KQCodeUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 公用群组监听器
 * 这里是公用监听器 对所有群开放
 *
 * @author Heilant Gong
 * <p>
 * {@link OnGroup} 此类为监听所有监听群消息的方法
 *
 * <p>方法作用说明
 * {@link this#repeat(GroupMsg, MsgSender)} 复读机
 * {@link this#qqAiTalk(GroupMsg, MsgSender)} 闲聊
 * {@link this#menu(GroupMsg, MsgSender)} 机器人菜单
 * {@link this#programmerCalendar(GroupMsg, MsgSender)} 主动获取老黄历
 * {@link this#emj(GroupMsg, MsgSender)} 发送一个emj
 * {@link this#sexImg(GroupMsg, MsgSender)} 获取一张涩图
 * {@link this#sweet(GroupMsg, MsgSender)} 舔/甜/毒狗模式
 */
@Component
@OnGroup
@Log4j2
public class AllGroupListener {

    CQCodeUtil cqCodeUtil = CQCodeUtil.build();
    @Resource
    MsgCreate msgCreate;
    @Resource
    PixivMsg pixivMsg;
    @Resource
    CreateApiMsg createApiMsg;
    @Resource
    ImgDownload imgDownload;
    @Resource
    StringUtil stringUtil;
    @Resource
    ImgInfoMapper imgInfoMapper;
    @Resource
    QQAiTalk qqAiTalk;
    @Resource
    RepeatTalk repeatTalk;
    @Autowired
    LocalPicService localPicService;
    @Value("${QQGroup.code}")
    String QQ_GROUP_CODE ;

    @Filter(value = {"今天的我"})
    public void todayMe(GroupMsg msg, MsgSender sender) {
        String at = KQCodeUtils.INSTANCE.toCq("at", "qq=" + msg.getQQ());
        String todayMe = createApiMsg.getTodayMe(msg.getQQCode(), sender.GETTER.getGroupMemberInfo(msg.getGroup(), msg.getQQCode()).getNickName());
        sender.SENDER.sendGroupMsg(msg, at + todayMe);
    }

    @Filter
    public void repeat(GroupMsg msg, MsgSender sender) {
        String groupCode = msg.getGroupCode();
        switch (groupCode) {
            case "641057857":
            case "925295392":
                return;
        }
        boolean judge = repeatTalk.judge(msg);
        if (judge) {
            sender.SENDER.sendGroupMsg(msg, msg.getMsg());
        }
    }

    @Filter(at=true)
    public void qqAiTalk(GroupMsg msg, MsgSender sender) {
        try {
            String talk = qqAiTalk.getTalk(msg.getMsg(), msg.getQQCode());
            JSONObject jsonObject = JSON.parseObject(talk);
            String answer = jsonObject.getJSONObject("data").getString("answer");
            if (null == answer) {
                answer = "听不懂你在说什么呢";
            }
            sender.SENDER.sendGroupMsg(msg, answer);
        }catch (IllegalArgumentException e){
            sender.SENDER.sendGroupMsg(msg,"辣鸡腾讯接口");
            e.printStackTrace();
        }catch (Exception e){
            sender.SENDER.sendGroupMsg(msg,"卧槽，未知错误");
            e.printStackTrace();
        }
    }


    @Filter(value = {".*菜单.*", ".*你会什么","鸡店"})
    public void menu(GroupMsg msg, MsgSender sender) {
        sender.SENDER.sendGroupMsg(msg, msgCreate.getMenu());
    }

    @Filter(value = {"二次元","二刺猿","two","涩.*","来点色图","来份色图","来分色图","来张色图","来份涩图","来分涩图","涩图","2"})
    public void localpic(GroupMsg msg, MsgSender sender){
        try {
            LocalPic localPic=localPicService.selectone();
            CQCode cqCode=cqCodeUtil.getCQCode_Image(localPic.getImgPath());
            localPicService.delete(localPic);
            sender.SENDER.sendGroupMsg(msg, String.valueOf(cqCode));
        }catch (Exception e){
                if(e instanceof NullPointerException){
                    sender.SENDER.sendGroupMsg(msg, "没图了~~~");
                }else {
                    sender.SENDER.sendGroupMsg(msg, "本地图片读取错误");
                }
        }
    }
    @Filter(value = {"three","三次元","写真","兔子",".*兔","大大大","色图","3"})
    public void localpicse(GroupMsg msg, MsgSender sender){
        try {
            LocalPic localPic=localPicService.selectonese();
            CQCode cqCode=cqCodeUtil.getCQCode_Image(localPic.getImgPath());
            localPicService.delete(localPic);
            sender.SENDER.sendGroupMsg(msg, String.valueOf(cqCode));
        }catch (Exception e){
            if(e instanceof NullPointerException){
                sender.SENDER.sendGroupMsg(msg, "没图了~~~");
            }else {
                sender.SENDER.sendGroupMsg(msg, "本地图片读取错误");
            }
        }

    }

    @Filter(value = {"黄历"})
    public void programmerCalendar(GroupMsg msg, MsgSender sender) {
        String dayMsg = msgCreate.getProgrammerCalendar(1);
        sender.SENDER.sendGroupMsg(msg, dayMsg);
    }


    @Filter(value = {"emj.*"})
    public void emj(GroupMsg msg, MsgSender sender) {
        CQCode cqCode = cqCodeUtil.getCQCode_Face("179");
        sender.SENDER.sendGroupMsg(msg, cqCode.toString());
    }


    @Filter(value = {"se"})
    public void sexImg(GroupMsg msg, MsgSender sender) {
        //接口key
        StringBuffer cqCodeLocal = new StringBuffer("");
        if (msg.getQQCode().equals("454514202") ) {//设置某人不能看
            sender.SENDER.sendGroupMsg(msg, "别看涩图了,作业写了吗,妹子谈了嘛,没有你还在这看涩图");
            return;
        }
        String msgStr = msg.getMsg();
        ImgInfo seTu = pixivMsg.getSeTu(msgStr, 0);
        // log.warn("涩图>>>>>>>>" + seTu.toString());
        if (seTu.getId().equals("0")) {
            cqCodeLocal.append("虽然没有指定类别的涩图，但是我找到了别的好康的\n");
            seTu = pixivMsg.getSeTu("", 0);
        }
        try {
            imgDownload.download(seTu.getImageUrl(), System.getProperty("user.dir") + "\\image\\", seTu.getUuid());
            imgInfoMapper.addImg(seTu);
            cqCodeLocal.append(cqCodeUtil.getCQCode_Image(System.getProperty("user.dir") + "\\image\\" + seTu.getUuid() + seTu.getFormat()).toString() + "\n");
            cqCodeLocal.append("标题:" + seTu.getTitle() + "\n");
            cqCodeLocal.append("P站ID:" + seTu.getId());
        } catch (Exception e) {
            sender.SENDER.sendGroupMsg(msg, "哎鸭,涩图不见了呢");
            e.printStackTrace();
        } finally {
             log.warn("cqCode>>>>>>>>>" + cqCodeLocal.toString());
            sender.SENDER.sendGroupMsg(msg, cqCodeLocal.toString());
        }
    }


    @Filter(value = {".我"})
    public void sweet(GroupMsg msg, MsgSender sender) {
        String at = KQCodeUtils.INSTANCE.toCq("at", "qq=" + msg.getQQ());
        String sendMsg;
        switch (msg.getMsg().substring(0, 1)) {
            case "舔":
                sendMsg = createApiMsg.getTianGou();
                sendMsg = sendMsg.substring(0, sendMsg.length() - 1);
                break;
            case "甜":
                sendMsg = createApiMsg.getSweet();
                break;
            case "毒":
                sendMsg = createApiMsg.getPoisonousChickenSoup();
                break;
            default:
                return;
        }
        sender.SENDER.sendGroupMsg(msg, at + " " + sendMsg);
    }
    //定向嘴甜模式

    /**
     * 消息例子: 甜他 @XXX
     */
    @Filter(value = {"[舔,甜,毒].*"})
    public void sweetAt(GroupMsg msg, MsgSender sender) {
        String msgStr = msg.getMsg();
        //获取At的cq码 然后直接使用就可以了
        String at = msgStr.substring(msgStr.indexOf("["), msgStr.indexOf("]") + 1);
        //信息标识
        String markStr;
        try {
            markStr = msgStr.substring(0, 1);
        } catch (Exception e) {
            return;
        }
        String sendMsg;
        switch (markStr) {
            case "舔":
                sendMsg = createApiMsg.getTianGou();
                sendMsg = sendMsg.substring(0, sendMsg.length() - 1);
                break;
            case "甜":
                sendMsg = createApiMsg.getSweet();
                break;
            case "毒":
                sendMsg = createApiMsg.getPoisonousChickenSoup();
                break;
            default:
                return;
        }
        sender.SENDER.sendGroupMsg(msg, at + " " + sendMsg);
    }
    @Filter(value = {"网抑云","网易云"})
    public void musiccomments(GroupMsg msg, MsgSender sender){
        try {
            String a=createApiMsg.getmusic();

            sender.SENDER.sendGroupMsg(msg,a.toString());
        }catch (Exception e){
                sender.SENDER.sendGroupMsg(msg, "error");
        }

    }
    @Filter(value = {"精神小伙","精神","小伙"})
    public void spiritGuy(GroupMsg msg, MsgSender sender){
        try {
            sender.SENDER.sendGroupMsg(msg,createApiMsg.getSpiritGuy());
        }catch (Exception e){
            sender.SENDER.sendGroupMsg(msg, "error");
        }

    }

    @Filter(value = {"wu"})
    public void qinghua(GroupMsg msg, MsgSender sender){
        try {
            sender.SENDER.sendGroupMsg(msg,createApiMsg.getwuwuwu());
        }catch (Exception e){
            sender.SENDER.sendGroupMsg(msg, "error");
        }

    }

    @Filter(value = {"日报","简报","每日读报","每日新闻","新闻"})
    public void everyDayNews(GroupMsg msg, MsgSender sender){
        try {
            sender.SENDER.sendGroupMsg(msg,createApiMsg.getEveryDayNews().toString());
        }catch (Exception e){
            sender.SENDER.sendGroupMsg(msg, "error");
        }

    }

    @Filter(value = {".*详情","新闻地址"})
    public void everyDayNewsDetail(GroupMsg msg, MsgSender sender){
        try {
            sender.SENDER.sendGroupMsg(msg,createApiMsg.getEveryDayNewsDeatil().toString());
        }catch (Exception e){
            sender.SENDER.sendGroupMsg(msg, "error");
        }

    }


}