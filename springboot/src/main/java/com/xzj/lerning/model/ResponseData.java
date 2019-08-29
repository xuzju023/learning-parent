package com.xzj.lerning.model;

/**
 * @date 2019/8/9 11:50
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public enum  ResponseData {
    CODE_500(500,"服务解析错误");
    
    
    
    
    
    
    
    
    private int code;
    private String msg;

    ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    
}
