package com.bonree.dataspout.model;

/**
 * @date 2019/11/6 15:11
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public enum ResponseData {
    /**
     * 成功失败
     */
    SUCCESS(1, "success"),
    ERROR(2, "ERROR"),
    ;

    ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
