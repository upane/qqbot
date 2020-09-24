package com.handcraft.aop;


import com.forte.qqrobot.beans.cqcode.CQCode;
import com.forte.qqrobot.beans.messages.msgget.GroupMsg;
import com.forte.qqrobot.sender.MsgSender;
import com.forte.qqrobot.utils.CQCodeUtil;
import com.handcraft.anno.Check;
import com.handcraft.features.Enum.FunEnum;
import com.handcraft.pojo.GroupPower;
import com.handcraft.service.GroupPowerService;
import com.handcraft.service.QQPowerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 权限校验AOP
 */
@Aspect
@Component
public class Permission {

    @Autowired
    QQPowerService qqPowerService;
    @Autowired
    GroupPowerService groupPowerService;

    private static final Integer SUCCESS = 0;
    private static final Integer FAIL = 1;

    @Around("@annotation(com.handcraft.anno.Check)")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取msg对象
        GroupMsg msg = (GroupMsg) joinPoint.getArgs()[0];
        //获取发送器对象
        MsgSender sender = (MsgSender) joinPoint.getArgs()[1];
        //QQ号
        String qq = msg.getQQ();
        //QQ群
        String group = msg.getGroup();

        CQCode cqCode_at = CQCodeUtil.build().getCQCode_At(qq);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取注解
        Check check = signature.getMethod().getAnnotation(Check.class);
        //获取注解中的枚举类，直接打印为枚举名称
        FunEnum funEnum = check.type();
        //功能需要花费的积分
        int cost = check.cost();
        //获取枚举类的序号
        int type = funEnum.ordinal();
        GroupPower groupPower =new GroupPower();
        groupPower.setQqGroup(group);
        groupPower.setFunId(type);

        try {
            GroupPower result =groupPowerService.find(groupPower);
            int resulpoint =result.getStatus();

            if( resulpoint>0){
                //放行
                joinPoint.proceed();
            }else {
                sender.SENDER.sendGroupMsg(group,"暂未开放此功能");
            }
        } catch (Exception e) {
            if(e instanceof NullPointerException){
                sender.SENDER.sendGroupMsg(group,"暂未开放此功能");
            }else {
                e.printStackTrace();
                sender.SENDER.sendGroupMsg(group, "AOP注解错误，烦");
            }
        }

    }
}
