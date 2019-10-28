package com.xzj.model;

/**
 * @date 2019/10/15 17:00
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Order {
    private int userId;
    private int orderId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
