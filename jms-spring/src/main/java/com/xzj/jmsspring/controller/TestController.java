package com.xzj.jmsspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2019/10/28 14:32
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@RestController
public class TestController {
    @Autowired
    private JmsTemplate template;

    @RequestMapping("/test")
    public String test() {
        template.convertAndSend("queue-1", "你好啊");
        return "ok";
    }
}
