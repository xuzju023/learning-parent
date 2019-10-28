package com.xzj.jmsspring.config;

import com.xzj.jmsspring.listener.SimpleMsgListener;
import com.xzj.jmsspring.listener.SimpleMsgListener2;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * @date 2019/10/28 11:01
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Configuration
public class ActiveMQConfig {

    private static final String QUEUE = "queue-1";

    @Bean
    public ActiveMQConnectionFactory createActiveMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        SingleConnectionFactory connectionFactory = new SingleConnectionFactory();
        connectionFactory.setTargetConnectionFactory(factory);
        return factory;
    }


    @Bean
    public JmsTemplate createJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(createActiveMQConnectionFactory());
        return jmsTemplate;
    }

    @Bean
    public DefaultMessageListenerContainer createListener() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(createActiveMQConnectionFactory());
        container.setMessageListener(new SimpleMsgListener());
        container.setMessageListener(new SimpleMsgListener2());
        container.setDestinationName(QUEUE);
        return container;
    }


}
