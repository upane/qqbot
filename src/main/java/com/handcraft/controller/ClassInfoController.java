package com.handcraft.controller;

import com.alibaba.fastjson.JSON;
import com.handcraft.pojo.ClassInfo;
import com.handcraft.pojo.LocalPic;
import com.handcraft.service.ClassInfoService;
import com.handcraft.service.LocalPicService;
import com.handcraft.util.StringUtil;
import org.jetbrains.annotations.TestOnly;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 课表信息接口
 *
 * @author HeilantG
 * {@link this#select}查询所有课表
 * {@link this#insert}添加一次课
 * {@link this#delete}根据uuid删除一次课
 */
@Controller
public class ClassInfoController {
    @Resource
    StringUtil stringUtil;

    @Resource
    LocalPicService localPicService;
    
    @Resource
    ClassInfoService classInfoService;

    @RequestMapping(value = "/select/ClassInfo")
    @ResponseBody
    public String select() {
        return JSON.toJSONString(classInfoService.queryAllClass());
    }

    @GetMapping(value = "/insert")
    @ResponseBody
    public String insertpic(@RequestParam String filename,Integer ftype) {
         return localPicService.batchadd(filename,ftype);
    }

    @RequestMapping(value = "/delete/ClassInfo")
    @ResponseBody
    public String delete(String uuid) {
        int i = classInfoService.deleteClassByUuid(uuid);
        if (i == 1) {
            return "success";
        }
        return "error";
    }
    @RequestMapping(value = "/insert/ClassInfo")
    public String insert(ClassInfo classInfo) {
        classInfo.setUuid(StringUtil.getUUID());
        classInfoService.insertClassInfo(classInfo);
        return "redirect:/page/class_table.html";
    }


}
