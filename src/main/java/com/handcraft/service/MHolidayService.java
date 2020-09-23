package com.handcraft.service;


import com.handcraft.pojo.MHoliday;

import java.util.Date;
import java.util.List;

public interface MHolidayService {

    void add(String qqcode,String holiday, Date holidaytime);
    void delete(String qqcode,String name);
    List<MHoliday> selectOneHoliday(String qqcode);
}
