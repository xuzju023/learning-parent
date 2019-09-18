package com.xzj.producer;

import com.xzj.constants.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @date 2019/8/14 13:49
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class KafkaProducerFactory {
    private final static Logger logger = LoggerFactory.getLogger(KafkaProducerFactory.class);

    private static KafkaProducer<byte[], byte[]> producer;

    public static KafkaProducer<byte[], byte[]> getInstance() {
        if (producer == null) {
            Properties props = new Properties();
            //host1:port1,host2:port2,…kafka cluster connection
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_SERVER_ADD);
            //若kafka的leader已经开始写入数据，则发送下一包数据
            props.put(ProducerConfig.ACKS_CONFIG, "1");
            //消息压缩类型
            props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none");
            //请求数据最大大小
            props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 335544320);
            //producer可以用来缓存数据的内存大小
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 335544320);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
            //生产者延迟发送至kafka Service，单位:ms
            props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
            //批处理大小
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, 163840);
            kafkaEncrypt(props);
            producer = new KafkaProducer<>(props);
        }
        return producer;
    }

    private static void kafkaEncrypt(Properties props) {
        switch (Constants.KAFKA_ENCRYPT_TYPE) {
            case "SASL":
                props.put("security.protocol", "SASL_PLAINTEXT");
                props.put("sasl.mechanism", "PLAIN");
                break;
            case "NONE":
                break;
            default:
                break;
        }

        logger.info("kafka encrypt type is {}", Constants.KAFKA_ENCRYPT_TYPE);
    }


}
