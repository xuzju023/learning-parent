package com.xzj.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2019/10/30 11:29
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@RestController
public class TestProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/test")
    public String testDemo() throws InterruptedException {
        for (int i = 0; i <100 ; i++) {
            
        kafkaTemplate.send("test.xzj", "this is my first demo");
        }
        return "ok";
    }
    
}
