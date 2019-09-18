package com.xzj.util;

import com.xzj.temp.Mythread;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * 发送HTTP请求超时时间
     */
    private final static Integer connTimeOut = 1000 * 60 * 1;
    /**
     * HTTP连接成功后，等待读取数据或者写数据的最大超时时间，如果设置为0，则表示永远不会超时
     */
    private final static Integer sendTimeOut = 1000 * 60 * 1;
    /**
     * 设置请求和传输超时时间
     */
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(sendTimeOut).setConnectTimeout(connTimeOut).build();

    /**
     * @param url    请求url
     * @param params 请求参数Map
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (params != null) {
                Set<Entry<String, String>> paramEntrySets = params.entrySet();
                if (paramEntrySets != null) {
                    Iterator<Entry<String, String>> paramEntrys = paramEntrySets.iterator();
                    while (paramEntrys.hasNext()) {
                        Entry<String, String> entry = paramEntrys.next();
                        nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                }
            }
            UrlEncodedFormEntity paramEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
            httpPost.setEntity(paramEntity);
            
            httpPost.releaseConnection();
            CloseableHttpClient httpClient = HttpClients.custom().build();
            String res = send(httpClient, httpPost, params);
            return res;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

 


    /**
     * 通过httpClient发送请求获取数据
     *
     * @param httpRequest 请求对象
     * @param params      纯粹为了打log
     * @return 返回的结果字符串数据；如果请求过程抛异常，返回值为null
     */
    public static String send(CloseableHttpClient httpClient, HttpRequestBase httpRequest, Map<String, String> params) {
        CloseableHttpResponse response = null;
        //设置请求与传输时间
        httpRequest.setConfig(requestConfig);
        boolean isNotLogResult = (params.get("isNotLogResult") == null || params.get("isNotLogResult").equals("")) ? false : true;
        String businessType = params.get("businessType") == null ? null : params.get("businessType").toString();
        String resultContent = "";
        params.remove("businessType");
        params.remove("isNotLogResult");
        try {
            log.info("\n[请求地址]: " + httpRequest.getURI() + "\n[请求参数]: " + params);
            long startTime = System.currentTimeMillis();
            // 执行
            //等待2秒释放连接
            Mythread handler = new Mythread(httpRequest,httpClient);
            Thread thread = new Thread(handler);
            thread.start();
            response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            long endTime = System.currentTimeMillis();
            System.out.println(endTime-startTime);
            //响应状态码
            int statuCode = response.getStatusLine().getStatusCode();
            resultContent = EntityUtils.toString(entity, "UTF-8");

           
        } catch (Exception e) {
            log.error(httpRequest.getURI() + " 请求出现异常", e);
            resultContent = "";
        } finally {
            try {
                if (response != null) {
                    response.close();
                }

                httpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            return resultContent;
        }
    }

}
