package com.handcraft.service;


import com.handcraft.pojo.GroupPower;

public interface GroupPowerService {
    GroupPower find(GroupPower groupPower);

    void update(GroupPower groupPower);

    void insert(GroupPower groupPower);
}
