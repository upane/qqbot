package com.handcraft.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalPic implements Serializable {
    private String UUID;
    private String imgPath;
    private Date createTime;
    private Integer picType;
    private Date picTime;


    private static final long serialVersionUID = 1L;

}
