package com.xzj.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @date 2019/10/30 11:30
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Component
public class TestListener {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // 指定topics，可以指定多个，最好每个topic对应一个
    @KafkaListener(id = "test-consumer-thread", topics = {"test.xzj"})
    public void listen(List<ConsumerRecord<String, String>> records, Acknowledgment ack) throws Exception {
        System.out.println("============================");
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < records.size(); i++) {
            System.out.println(threadName + ":" + records.get(i).value());
        }
        ack.acknowledge();
    }


}
