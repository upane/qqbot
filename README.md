# SlimeBot

这是基于Mirai与Simple-robot-core的QQ机器人。  
主要自己用，作为CV工程师乱改了下  

主要后端，前端被我删了，JS好多。。。  
 可能layui写---待定

# 目前功能

- 舒克舒克米其林餐厅  
1.黄历  
2.甜，舔，毒+@人  
3.二次元、三次元、se  
4.今天的我  
5.网易云、精神、wu   
7.节假日  
8.基金查询  

- 开启关闭指令

# 三克油 thanks

主要参考了[HeilantG](https://github.com/HeilantG/) 的
[SlimeBot](https://github.com/HeilantG/SlimeBot/) 项目  

Mirai详情请见[Mirai](https://github.com/mamoe/mirai)

十分感谢[HeilantG](https://github.com/HeilantG/SlimeBot/) 

# 其他说明

##使用 
-  com.handcraft.features.qqAi.QQAiTalk  有appId、appKey能闲聊AI
 - com.handcraft.features.pixiv.PixivMsg  加TG机器人获取token才能涩图        获取网址都在两个类中
 
 - 推荐尝试本地图片速度快，接口晚上还是不靠谱，图片加载慢（然后报错）
 
-  基本上指令package com.handcraft.listener;  包下  
@Filter(value = {"see"})的注解 ，如这条就是发see


##开发
-  数据来源全是别人接口，改就完事了

-  大部分接口还是感觉辣鸡的，不如本地化，体验较差
##跑路
-  不说了，我还得去研究下部署。。。  
下点本地图片。。。  
加点LOL。。。  
加点盐（/滑稽）

- 2020.9.24 现在jar包部署在docker，数据库也在docker................  
