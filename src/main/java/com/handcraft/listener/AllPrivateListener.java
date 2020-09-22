package com.handcraft.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forte.qqrobot.anno.Filter;
import com.forte.qqrobot.anno.Listen;
import com.forte.qqrobot.anno.template.OnPrivate;
import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.beans.messages.msgget.PrivateMsg;
import com.forte.qqrobot.beans.messages.types.MsgGetTypes;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.features.api.CreateApiMsg;
import com.handcraft.features.pixiv.PixivMsg;
import com.handcraft.features.qqAi.QQAiTalk;
import com.handcraft.mapper.ImgInfoMapper;
import com.handcraft.pojo.ImgInfo;
import com.handcraft.pojo.LocalPic;
import com.handcraft.service.LocalPicService;
import com.handcraft.util.ImgDownload;
import com.handcraft.util.MsgCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;


/**
 * 公用私聊监听器
 * 这里是公用监听器 对所有群开放
 *
 * @author Heilant Gong
 * <p>
 * {@link OnPrivate} 此类为监听所有细聊消息的方法
 *
 * <p>方法作用说明
 * {@link this#qqAiTalk(PrivateMsg, MsgSender)} 闲聊模式
 * {@link this#menu(PrivateMsg, MsgSender)} 菜单
 * {@link this#programmerCalendar(PrivateMsg, MsgSender)} 程序员的老黄历
 * {@link this#sexImg(PrivateMsg, MsgSender)} 涩图
 * {@link this#sweet(PrivateMsg, MsgSender)} 舔/甜/毒狗模式
 */

@Component
@OnPrivate
public class AllPrivateListener {
    CQCodeUtil cqCodeUtil = CQCodeUtil.build();

    @Resource
    MsgCreate msgCreate;
    @Resource
    PixivMsg pixivMsg;
    @Resource
    ImgDownload imgDownload;
    @Resource
    CreateApiMsg createApiMsg;
    @Resource
    ImgInfoMapper imgInfoMapper;
    @Resource
    QQAiTalk qqAiTalk;
    @Autowired
    LocalPicService localPicService;

    @Filter()
    public void qqAiTalk(PrivateMsg msg, MsgSender sender) {
        try {
            String msgStr = msg.getMsg();
//        //移除开头的空格
//        msgStr = msgStr.substring(1);
            System.out.println(msgStr);
            String talk = qqAiTalk.getTalk(msgStr, msg.getQQCode());
            JSONObject jsonObject = JSON.parseObject(talk);
            String answer = jsonObject.getJSONObject("data").getString("answer");
            if (null == answer) {
                answer = "听不懂你在说什么呢";
            }
            sender.SENDER.sendPrivateMsg(msg, answer);

        }catch (IllegalArgumentException e){
            sender.SENDER.sendPrivateMsg(msg,"辣鸡腾讯接口");
            e.printStackTrace();
        }catch (Exception e){
            sender.SENDER.sendPrivateMsg(msg,"未知错误");
            e.printStackTrace();
        }

    }


    @Filter(value = {".*菜单", "你会什么"})
    public void menu(PrivateMsg msg, MsgSender sender) {
        sender.SENDER.sendPrivateMsg(msg, msgCreate.getMenu());
    }

    @Filter(value = {"黄历"})
    public void programmerCalendar(PrivateMsg msg, MsgSender sender) {
        int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String str = msgCreate.getProgrammerCalendar(i);
        sender.SENDER.sendPrivateMsg(msg, str);
    }

    @Filter(value = {"local"})
    public void localpic(PrivateMsg msg, MsgSender sender){
        try {
            LocalPic localPic=localPicService.selectone();
            CQCode cqCode=cqCodeUtil.getCQCode_Image(localPic.getImgPath());
            localPicService.delete(localPic);
            sender.SENDER.sendPrivateMsg(msg, String.valueOf(cqCode));
        }catch (Exception e){
            if(e instanceof NullPointerException){
                sender.SENDER.sendPrivateMsg(msg, "没图了~~~");
            }else {
                sender.SENDER.sendPrivateMsg(msg, "本地图片读取错误");
            }
        }
    }
//    @Listen(MsgGetTypes.privateMsg)
    @Filter(value = {"se"})
    public void sexImg(PrivateMsg msg, MsgSender sender) {
        ImgInfo seTu = pixivMsg.getSeTu( "", 0);
        StringBuffer cqCodeLocal = new StringBuffer();
        try {
            imgDownload.download(seTu.getImageUrl(), null, seTu.getUuid());
            imgInfoMapper.addImg(seTu);
            cqCodeLocal.append(cqCodeUtil.getCQCode_Image(System.getProperty("user.dir") + "\\image\\" + seTu.getUuid() + seTu.getFormat()).toString() + "\n");
            cqCodeLocal.append("标题:" + seTu.getTitle() + "\n");
            cqCodeLocal.append("P站ID:" + seTu.getId());
            String aaa=cqCodeLocal.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            sender.SENDER.sendPrivateMsg(msg, cqCodeLocal.toString());
        }
    }
    @Filter(value = {"芜湖"})
    public void seR18(PrivateMsg msg, MsgSender sender) {
        ImgInfo seTu = pixivMsg.getSeTu( "", 1);
        StringBuffer cqCodeLocal = new StringBuffer();
        try {
            imgDownload.download(seTu.getImageUrl(), null, seTu.getUuid());
            imgInfoMapper.addImg(seTu);
            cqCodeLocal.append(cqCodeUtil.getCQCode_Image(System.getProperty("user.dir") + "\\image\\" + seTu.getUuid() + seTu.getFormat()).toString() + "\n");
            cqCodeLocal.append("标题:" + seTu.getTitle() + "\n");
            cqCodeLocal.append("P站ID:" + seTu.getId());
            String aaa=cqCodeLocal.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            sender.SENDER.sendPrivateMsg(msg, cqCodeLocal.toString());
        }
    }

    @Filter(value = {".我"})
    public void sweet(PrivateMsg msg, MsgSender sender) {
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
        sender.SENDER.sendPrivateMsg(msg, sendMsg);
    }

    @Filter(value = {"二次元","二刺猿","two","涩.*","来点色图","来份色图","来分色图","来张色图","来份涩图","来分涩图","涩图","2"})
    public void privlocalpic(PrivateMsg msg, MsgSender sender){
        try {
            LocalPic localPic=localPicService.selectone();
            CQCode cqCode=cqCodeUtil.getCQCode_Image(localPic.getImgPath());
            localPicService.delete(localPic);
            sender.SENDER.sendPrivateMsg(msg, String.valueOf(cqCode));
        }catch (Exception e){
            if(e instanceof NullPointerException){
                sender.SENDER.sendPrivateMsg(msg, "没图了~~~");
            }else {
                sender.SENDER.sendPrivateMsg(msg, "本地图片读取错误");
            }
        }
    }
    @Filter(value = {"three","三次元","写真","兔子",".*兔","大大大","色图","3"})
    public void localpicse(PrivateMsg msg, MsgSender sender){
        try {
            LocalPic localPic=localPicService.selectonese();
            CQCode cqCode=cqCodeUtil.getCQCode_Image(localPic.getImgPath());
            localPicService.delete(localPic);
            sender.SENDER.sendPrivateMsg(msg, String.valueOf(cqCode));
        }catch (Exception e){
            if(e instanceof NullPointerException){
                sender.SENDER.sendPrivateMsg(msg, "没图了~~~");
            }else {
                sender.SENDER.sendPrivateMsg(msg, "本地图片读取错误");
            }
        }

    }
}
