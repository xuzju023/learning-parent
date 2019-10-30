package com.xzj.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaConfiguration {
    private final KafkaProperties properties;

    public KafkaConfiguration(KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public ProducerFactory<String, String> producerFactory() {
        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(
                this.properties.buildProducerProperties());
        return factory;
    }
    


    @Bean
    @ConditionalOnMissingBean
    ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        KafkaProperties.Listener listener = this.properties.getListener();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(listener.getConcurrency());
        factory.setBatchListener(true);
        factory.getContainerProperties().setAckMode(listener.getAckMode());
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsumerFactory<Object, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(this.properties.buildConsumerProperties());
    }



}
