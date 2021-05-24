package com.handcraft;

import com.handcraft.features.api.CreateApiMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class testApp {

    @Autowired
    CreateApiMsg createApiMsg;
    @Test
    public void all() {
        System.out.println(System.getProperty("user.dir"));
    }


    @Test
    public void zz() {
        createApiMsg.nbnhhsh("nmsl");
    }
}
