package com.handcraft.service.impl;


import com.handcraft.mapper.QQPowerMapper;
import com.handcraft.pojo.QQPower;
import com.handcraft.service.QQPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QQPowerServiceImpl implements QQPowerService {
    @Autowired
    QQPowerMapper qqPowerMapper;

    @Override
     public Integer find(QQPower qqPower) {
     return qqPowerMapper.selectByQQandStatus(qqPower);
    }

    @Override
    public void update(QQPower qqPower) {
        qqPowerMapper.update(qqPower);
    }

    @Override
    public void insert(QQPower qqPower) {
        qqPowerMapper.add(qqPower);
    }
}
