package com.xzj.jmsspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @date 2019/10/28 15:51
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Service
public class MessageService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination destination, final String msg) {

        jmsTemplate.convertAndSend(destination, msg);
    }

    /**
     * Receives a message from a queue.
     *
     * @return Message text.
     * @throws JMSException
     */
    public String readTextMessage(Destination destination) throws JMSException {

        String message = null;

        Message msg = jmsTemplate.receive(destination);
        if (msg instanceof TextMessage) {
            message = ((TextMessage) msg).getText();
        }

        return message;
    }
}
