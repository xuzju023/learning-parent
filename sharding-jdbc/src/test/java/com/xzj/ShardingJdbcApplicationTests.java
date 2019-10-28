package com.xzj;

import com.xzj.mapper.OrderMapper;
import com.xzj.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcApplicationTests {
    @Autowired
    private OrderMapper mapper;

    @Test
    public void contextLoads() {
        Order order = new Order();
        for (int i = 0; i < 10; i++) {
            order.setUserId(i);
            order.setOrderId(i);
            mapper.insert(order);
        }
    }

    @Test
    public void test1() {
        Method[] declaredMethods = mapper.getClass().getDeclaredMethods();
        System.out.println("----------------" + mapper.selectOrder(1));
    }

    @Test
    public void test2() {
        Method[] declaredMethods = mapper.getClass().getDeclaredMethods();
        System.out.println("----------------" + mapper.selectOrderItem(1));
    }

}
