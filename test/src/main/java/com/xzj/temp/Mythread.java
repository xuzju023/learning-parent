package com.xzj.temp;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @date 2019/9/11 14:22
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Mythread implements Runnable {
    HttpRequestBase httpPost;
    CloseableHttpClient httpClient;

    public Mythread(HttpRequestBase httpPost, CloseableHttpClient httpClient) {
        this.httpPost = httpPost;
        this.httpClient = httpClient;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);

            httpPost.releaseConnection();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
