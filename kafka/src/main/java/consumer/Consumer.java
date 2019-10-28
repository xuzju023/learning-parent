package consumer;

import org.apache.kafka.clients.consumer.*;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @date 2019/9/18 17:07
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Consumer {
    private final static String topic = "TEST-XZJ";

    public static void main(String[] args) {
        Consumer.base(topic);
    }

    public static void base(String topic) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.51:9092,192.168.1.52:9092,192.168.1.53:9092");
        //当前consumer的分组Id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-xzj-group-1");
        //设置自动提交偏移量
        props.put("enable.auto.commit", "true");
        //设置自动提交的频率
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //消费者分区策略
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.RangeAssignor");
        
        
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅topic,可以订阅多个"test","test2"
        consumer.subscribe(Arrays.asList(topic));

        try {
            while (true) {
                //poll()用来获取消息,参数1000（毫秒）的含义是，当缓冲区中没有可用消息时，以此时间进行轮训等待。当设置为0时，理解返回当前可用的消息或者返回空。
                ConsumerRecords<String, String> records = consumer.poll(0);
                for (ConsumerRecord<String, String> record : records) {
                    System.err.println("offset:" + record.offset() + " key:" + record.key() + " value:" + record.value() + " partition:" + record.partition());
    
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
