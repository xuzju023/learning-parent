package com.xzj.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @date 2019/8/14 15:01
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class KafkaCallBack implements Callback {
    private final static Logger logger = LoggerFactory.getLogger(KafkaCallBack.class);

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            System.out.println("send success!");
            logger.info("send success! topic : {}, partition : {}, offset: {} ", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        } else {
            System.out.println("send error !");
            logger.error("send error ! ", e);
        }
    }
}
