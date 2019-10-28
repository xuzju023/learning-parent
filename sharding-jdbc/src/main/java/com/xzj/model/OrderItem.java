package com.xzj.model;

/**
 * @date 2019/10/15 19:43
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class OrderItem {
    private int orderId;
    private int userId;
    private String description;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
