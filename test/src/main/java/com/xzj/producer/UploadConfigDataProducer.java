package com.xzj.producer;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;

/**
 * @date 2019/8/14 14:22
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class UploadConfigDataProducer {

    private static UploadConfigDataProducer instance = new UploadConfigDataProducer();

    /** 饿汉式 */
    public static UploadConfigDataProducer getInstance(){
        return instance;
    }

    /**
     * 发送数据至kafka
     * @param topic
     * @param msg
     * @throws Exception
     */
    public void sendMsg(String topic, byte[] msg) throws Exception {
        KafkaProducerFactory.getInstance().send(new ProducerRecord<>(topic, msg), new KafkaCallBack());
    }


    /**
     * 批量发送数据
     * @param topic 主题
     * @param msgList 数据list
     * @throws Exception
     */
    public void sendMsgByPart(String topic, List<byte[]> msgList) throws Exception {
        for (byte[] data : msgList) {
            sendMsg(topic, data);
        }
    }
}
