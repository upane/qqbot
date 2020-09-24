package com.handcraft.controller;

import com.handcraft.service.LocalPicService;
import com.handcraft.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class LocalSaveJPGController {
    @Resource
    StringUtil stringUtil;

    @Resource
    LocalPicService localPicService;

    @GetMapping(value = "/insert")
    @ResponseBody
    public String insertpic(@RequestParam String filename, Integer ftype) {
        return localPicService.batchadd(filename, ftype);
    }


}
