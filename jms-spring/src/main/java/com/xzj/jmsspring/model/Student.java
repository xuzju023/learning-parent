package com.xzj.jmsspring.model;

import java.io.Serializable;

/**
 * @date 2019/10/28 18:31
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Student implements Serializable {
    private static final long serialVersionUID = -658250125732806493L;
    private String name;
    private String password;
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
