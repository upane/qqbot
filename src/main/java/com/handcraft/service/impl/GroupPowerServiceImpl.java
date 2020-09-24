package com.handcraft.service.impl;


import com.handcraft.mapper.GroupPowerMapper;
import com.handcraft.pojo.GroupPower;
import com.handcraft.service.GroupPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupPowerServiceImpl implements GroupPowerService {
    @Autowired
    GroupPowerMapper groupPowerMapper;

    @Override
    public GroupPower find(GroupPower groupPower) {
        return groupPowerMapper.selectByqqID(groupPower);
    }

    @Override
    public void update(GroupPower groupPower) {
        groupPowerMapper.update(groupPower);
    }

    @Override
    public void insert(GroupPower groupPower) {
        groupPowerMapper.add(groupPower);
    }
}
