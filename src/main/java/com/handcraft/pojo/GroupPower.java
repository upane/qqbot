package com.handcraft.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupPower {
    private String UUID;
    private String qqGroup;
    private Integer funId;
    private Integer status;
}
