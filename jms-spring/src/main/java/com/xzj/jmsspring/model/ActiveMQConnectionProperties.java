package com.xzj.jmsspring.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @date 2019/10/28 20:00
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@ConfigurationProperties(
        prefix = "activemq.properties"
)
public class ActiveMQConnectionProperties {
    private String brokerURL;
    private String userName;
    private String password;
    private boolean trustAllPackages;

    public boolean isTrustAllPackages() {
        return trustAllPackages;
    }

    public void setTrustAllPackages(boolean trustAllPackages) {
        this.trustAllPackages = trustAllPackages;
    }

    public String getBrokerURL() {
        return brokerURL;
    }

    public void setBrokerURL(String brokerURL) {
        this.brokerURL = brokerURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
