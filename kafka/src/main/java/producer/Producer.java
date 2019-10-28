package producer;

import callback.KafkaCallBack;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import partitioner.Mypartitioner;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @date 2019/9/18 16:12
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Producer {
    private final static String topic = "TEST-XZJ";

    public static void main(String[] args) {
        Properties p = new Properties();
        p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.51:9092,192.168.1.52:9092,192.168.1.53:9092");
        p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        p.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, Mypartitioner.class);
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);

        try {
            int i=0;
            while (true) {
                String msg = "Hello: " + i;
                ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,"2", msg);
                Future<RecordMetadata> send = kafkaProducer.send(record,new KafkaCallBack());
                Thread.sleep(500);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }

    }
}
