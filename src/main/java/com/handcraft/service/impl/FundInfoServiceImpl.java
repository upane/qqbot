package com.handcraft.service.impl;

import com.handcraft.mapper.FundInfoMapper;
import com.handcraft.mapper.MHolidayMapper;
import com.handcraft.pojo.FundInfo;
import com.handcraft.pojo.MHoliday;
import com.handcraft.service.FundInfoService;
import com.handcraft.service.MHolidayService;
import com.handcraft.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FundInfoServiceImpl implements FundInfoService {
    @Autowired
    FundInfoMapper fundInfoMapper;

    @Override
    public void add(String qqcode, String fundcode) {
        FundInfo enti =new FundInfo();
        enti.setUUID(StringUtil.getUUID());
        enti.setQqCode(qqcode);
        enti.setFundCode(fundcode);
        fundInfoMapper.add(enti);
    }

    @Override
    public void delete(String qqcode,String funcode) {
        FundInfo enti =new FundInfo();
        enti.setQqCode(qqcode);
        enti.setFundCode(funcode);
        fundInfoMapper.delete(enti);
    }

    @Override
    public List<FundInfo> selectFunds(String qqcode) {

       List<FundInfo> fundInfos= fundInfoMapper.selectByQQcode(qqcode);
       return fundInfos;
    }
}
