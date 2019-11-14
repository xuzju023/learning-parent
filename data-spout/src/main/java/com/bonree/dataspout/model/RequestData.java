package com.bonree.dataspout.model;

/**
 * @date 2019/11/6 15:04
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class RequestData {
    private Object data;
    private Integer concurrenceNum;
    private Integer time;
    private String url;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getConcurrenceNum() {
        return concurrenceNum;
    }

    public void setConcurrenceNum(Integer concurrenceNum) {
        this.concurrenceNum = concurrenceNum;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
