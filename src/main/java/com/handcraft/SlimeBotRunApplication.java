package com.handcraft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SlimeBot启动类
 *
 * @author HeilantG
 */
@SpringBootApplication
public class SlimeBotRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(SlimeBotRunApplication.class, args);
    }
}
