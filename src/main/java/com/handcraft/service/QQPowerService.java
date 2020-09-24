package com.handcraft.service;


import com.handcraft.pojo.QQPower;

public interface QQPowerService {

    Integer find(QQPower qqPower);

    void update(QQPower qqPower);

    void insert(QQPower qqPower);
}
