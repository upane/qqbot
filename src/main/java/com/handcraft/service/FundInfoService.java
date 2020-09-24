package com.handcraft.service;


import com.handcraft.pojo.FundInfo;
import com.handcraft.pojo.MHoliday;

import java.util.Date;
import java.util.List;

public interface FundInfoService {

    void add(String qqcode,String fundcode);
    void delete(String qqcode,String fundcode);
    List<FundInfo> selectFunds(String qqcode);
}
