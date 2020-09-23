package com.handcraft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MHoliday {
    private String UUID;
    private String qqCode;
    private String holiday;
    private Date createTime;
    private Date holidayTime;
    private String hType; //1、节假日 2、基金


}
