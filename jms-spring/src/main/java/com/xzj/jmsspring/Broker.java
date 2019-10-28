package com.xzj.jmsspring;

import org.apache.activemq.broker.BrokerService;

/**
 * @date 2019/10/28 14:30
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Broker {
    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.setUseJmx(true);
        broker.addConnector("tcp://localhost:61616");
        broker.start();
    }
}
