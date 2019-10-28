package com.xzj.jmsspring.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @date 2019/10/28 11:34
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class SimpleMsgListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("=====================================");
    }
}
