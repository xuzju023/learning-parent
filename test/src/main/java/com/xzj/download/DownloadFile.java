package com.xzj.download;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @date 2019/9/20 10:25
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class DownloadFile {
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "\t\"crashId\":\"abc-def-ghi-jk\",\n" +
                "\t\"appId\":\"1863\",\n" +
                "\t\"appVersionName\":\"1.0.0\"\n" +
                "}";

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<String>(jsonStr, headers);

        //下载到磁盘
        RequestCallback requestCallback = restTemplate.httpEntityCallback(entity);
        File file = restTemplate.execute("http://devtest.ibr.cc:20164/fileServer/crashFileDownload/downLoad", HttpMethod.POST, requestCallback, clientHttpResponse -> {
            File res = new File("E:/temp", "res.file");
            FileOutputStream outputStream = new FileOutputStream(res);
            BufferedInputStream inputStream = new BufferedInputStream(clientHttpResponse.getBody());
            try {
                int len;
                byte[] buffer = new byte[1024 * 1024];
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                outputStream.close();
                inputStream.close();
            }
            System.out.println("-----");
            return res;
        });
        System.out.println(file.getPath());


        //下载到内存
        //ResponseEntity<byte[]> response = restTemplate.postForEntity("http://devtest.ibr.cc:20164/fileServer/crashFileDownload/downLoad", entity, byte[].class);
        //byte[] body = response.getBody();
        //System.out.println(body.length/1024/1024);
    }
}
