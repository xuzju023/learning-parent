package com.xzj;

import com.xzj.service.UserService;
import com.xzj.service.UserService2;
import com.xzj.test.dao.TestUser1Dao;
import com.xzj.test.entity.TestUser1;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={AppStart.class})// 指定启动类
public class Test {
    @Autowired
    @Qualifier("service2")
    private UserService2 userService2;


    @org.junit.Test
    public void test(){
        TestUser1 testUser1 = new TestUser1();
        testUser1.setName("xzj");
        userService2.insert2(testUser1);
    }
}
