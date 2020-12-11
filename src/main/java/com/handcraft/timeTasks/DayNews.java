//package com.handcraft.timeTasks;
//
//import com.forte.qqrobot.bot.BotManager;
//import com.forte.qqrobot.bot.BotSender;
//import com.handcraft.features.api.CreateApiMsg;
//import com.handcraft.util.MsgCreate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.scheduling.annotation.Schedules;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//@EnableScheduling
//public class DayNews {
//    /**
//     * 每日简报 定时任务
//     * author：pan
//     *
//     */
//
//    @Autowired
//    CreateApiMsg createApiMsg;
//    @Resource
//    private BotManager botManager;
//    @Resource
//    MsgCreate msgCreate;
//
//    @Value("${QQGroup.code}")
//    private String QQ_GROUP_CODE ;
//
//
//
//    @Scheduled(cron = " 0 30 7 * * * ")
//    public void everyDayNews(){
//
//        BotSender sender = botManager.defaultBot().getSender();
//        String dayMsg = msgCreate.getDayMsg();
////        sender.SENDER.sendGroupMsg(QQ_GROUP_CODE, dayMsg);
//        sender.SENDER.sendGroupMsg(QQ_GROUP_CODE, createApiMsg.getEveryDayNews().toString());
//    }
//
//    @Scheduled(cron = " 0 10 18 * * * ")
//    public void everyLOLday(){
//
//        BotSender sender = botManager.defaultBot().getSender();
//        String dayMsg = msgCreate.getDayMsg();
//        sender.SENDER.sendGroupMsg(QQ_GROUP_CODE, dayMsg);
//    }
//
//
//
//
//
//
//
//
//
//}
