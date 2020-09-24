package com.handcraft.anno;


import com.handcraft.features.Enum.FunEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 封禁人的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Ban {
    //功能
    FunEnum[] types();
    //启用or禁用
    int status() default 0;
    //需要 管理员权限：0 普通权限：1
    int admin() default 0;
}
