package com.handcraft.service.impl;

import com.handcraft.mapper.MHolidayMapper;
import com.handcraft.pojo.MHoliday;
import com.handcraft.service.MHolidayService;
import com.handcraft.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MHolidayServiceImpl implements MHolidayService {
    @Autowired
    MHolidayMapper mHolidayMapper;

    @Override
    public void add(String qqcode, String holiday, Date holidaytime) {
        MHoliday enti =new MHoliday();
        enti.setUUID(StringUtil.getUUID());
        enti.setHolidayTime(holidaytime);
        enti.setHoliday(holiday);
        enti.setHType("1");
        enti.setQqCode(qqcode);
        enti.setCreateTime(new Date());
        mHolidayMapper.add(enti);
    }

    @Override
    public void delete(String qqcode,String name) {
        mHolidayMapper.delete(qqcode,name);
    }

    @Override
    public List<MHoliday> selectOneHoliday(String qqcode) {

       List<MHoliday> holi= mHolidayMapper.selectByqqcode(qqcode);
       return holi;
    }
}
