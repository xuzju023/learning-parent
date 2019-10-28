package com.xzj.jmsspring.config;

import com.xzj.jmsspring.listener.SimpleMsgListener;
import com.xzj.jmsspring.model.ActiveMQConnectionProperties;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @date 2019/10/28 11:01
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Configuration
@EnableConfigurationProperties(ActiveMQConnectionProperties.class)
public class ActivemqConfiguration {


    @Bean("activeMQConnectionFactory")
    public ActiveMQConnectionFactory createActiveMQConnectionFactory(ActiveMQConnectionProperties properties) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        BeanUtils.copyProperties(properties, factory);
        return factory;
    }

    @Bean
    public JmsTemplate createJmsTemplate(@Qualifier("activeMQConnectionFactory") ActiveMQConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer createDefaultListener(ActiveMQConnectionFactory activeMQConnectionFactory) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(activeMQConnectionFactory);
        container.setMessageListener(new SimpleMsgListener());
        container.setDestinationName("queue-1");
        return container;
    }


}
