package com.handcraft.controller;

import com.alibaba.fastjson.JSON;
import com.handcraft.pojo.MsgTime;
import com.handcraft.service.MsgTimeService;
import com.handcraft.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 定时提醒接口
 *
 * @author Heilant Gong
 * {@link this#queryAll()} 查询所有提醒信息
 * {@link this#insert(MsgTime)} 添加
 * {@link this#delete(MsgTime)} 删除
 */
@Controller
public class MsgTimeController {

    @Resource
    MsgTimeService msgTimeService;
    @Resource
    StringUtil stringUtil;

    @RequestMapping(value = "/query/msgTime")
    @ResponseBody
    public String queryAll() {
        return JSON.toJSONString(msgTimeService.queryAll());
    }

    @RequestMapping(value = "/insert/msgTime")
    public String insert(MsgTime msgTime) {
        msgTime.setUuid(StringUtil.getUUID());
        msgTimeService.insert(msgTime);
        return "redirect:/page/msgTime_table.html";
    }

    @RequestMapping(value = "/delete/msgTime")
    public String delete(MsgTime msgTime) {
        msgTimeService.delete(msgTime);
        return "redirect:/page/msgTime_table.html";
    }
}
