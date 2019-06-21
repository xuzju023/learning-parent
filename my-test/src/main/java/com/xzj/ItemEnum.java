package com.xzj;

public enum  ItemEnum {
    cup("cup","杯子");


    private final String key;
    private final String value;


    ItemEnum(String key,String value) {
        this.key=key;
        this.value=value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
