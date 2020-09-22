package com.handcraft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalPic {
    private String UUID;
    private String imgPath;
    private Date createTime;
    private Byte picType;
}
