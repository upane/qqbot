package com.handcraft.mapper;

import com.handcraft.pojo.GroupPower;
import com.handcraft.pojo.QQPower;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GroupPowerMapper  {
    int add (GroupPower groupPower);
    GroupPower selectByqqID(GroupPower groupPower);
   int update (GroupPower groupPower);

}
