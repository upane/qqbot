package com.handcraft.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QQPower  {
    private String UUID;
    private String qq;
    private Integer funId;
    private Integer status;
}
