package com.handcraft.mapper;


import com.handcraft.pojo.QQPower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QQPowerMapper {

    int add (QQPower qqPower);
     Integer selectByQQandStatus(QQPower qqPower);
    int update (QQPower qqPower);
}
