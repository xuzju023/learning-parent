package com.xzj;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 14:04 2019/6/5
 */
public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}
