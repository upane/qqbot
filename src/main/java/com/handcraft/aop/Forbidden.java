package com.handcraft.aop;


import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.anno.Ban;
import com.handcraft.features.Enum.FunEnum;
import com.handcraft.pojo.GroupPower;
import com.handcraft.pojo.QQPower;
import com.handcraft.service.GroupPowerService;
import com.handcraft.service.QQPowerService;
import com.handcraft.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Ban人AOP
 */
@Aspect
@Component
public class Forbidden {


    @Autowired
    QQPowerService qqPowerService;
    @Autowired
    GroupPowerService groupPowerService;

    @Around("@annotation(com.handcraft.anno.Ban)")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取msg对象
        GroupMsg msg = (GroupMsg) joinPoint.getArgs()[0];
        //获取发送器对象
        MsgSender sender = (MsgSender) joinPoint.getArgs()[1];
        //发送者QQ号
        String qq = msg.getQQ();

        CQCode cqCode_at = CQCodeUtil.build().getCQCode_At(qq);

        //获取注解信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Ban ban = signature.getMethod().getAnnotation(Ban.class);
        //本功能所需要的权限
        int admin = ban.admin();
        QQPower qz=new QQPower();
        qz.setQq(qq);
        qz.setStatus(admin);
       int panduan = qqPowerService.find(qz);

       if(panduan<1){  //跳出
           sender.SENDER.sendGroupMsg(msg.getGroup(), "亲亲，你没有该权限呢~");
           return;
       }
        int status = ban.status();


        FunEnum[] types = ban.types();
        //查看数据库中是否已有数据，有则修改，没有则添加
        try {
            for (FunEnum type : types) {
                GroupPower groupPower = new GroupPower();
                groupPower.setQqGroup(msg.getGroupCode());
                groupPower.setStatus(status);
                groupPower.setFunId(type.ordinal());

                GroupPower isNull = groupPowerService.find(groupPower);
                if (null==isNull){
                    groupPower.setUUID(StringUtil.getUUID());
                    groupPowerService.insert(groupPower);
                }else{
                    groupPower.setUUID(isNull.getUUID());
                    groupPowerService.update(groupPower);
                }
            }
            String str = "";
            if (status==1){
                str = "已启用 ";
            }else{
                str = "已禁用 ";
            }
            sender.SENDER.sendGroupMsg(msg.getGroup(), str);
        } catch (Exception e) {
            sender.SENDER.sendGroupMsg(msg.getGroup(), "意料之中的系统错误XD");
            e.printStackTrace();
        }


    }
}
