package com.xzj.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @date 2019/11/5 17:19
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class HttpClient {
    private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        RestTemplate restTemplate = new RestTemplate();
        CountDownLatch countDownLatch = new CountDownLatch(16);
        Mythread mythread = new Mythread(countDownLatch, restTemplate);
        for (int i = 0; i < 16; i++) {
            countDownLatch.countDown();
            executor.execute(mythread);
        }
        logger.info("正在发送..");
        executor.shutdown();
    }

    static class Mythread implements Runnable {
        CountDownLatch countDownLatch;
        RestTemplate restTemplate;

        public Mythread(CountDownLatch countDownLatch, RestTemplate restTemplate) {
            this.countDownLatch = countDownLatch;
            this.restTemplate = restTemplate;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                while (true) {
                    excute(restTemplate);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void excute(RestTemplate restTemplate) {
            try {
                restTemplate = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.add("Br-Accept-Encoding", "gzip");
                headers.add("ProtoTYPE", "json");
                headers.add("brkey", "8dd04674-50e1-4dc5-a6b0-e1d8af825254_863014031360552");
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
                HttpEntity<byte[]> entity = new HttpEntity<byte[]>(data.getBytes(), headers);
                ResponseEntity<byte[]> res = restTemplate.postForEntity("http://10.240.47.100:1080/upload?key=8dd04674-50e1-4dc5-a6b0-e1d8af825254_863014031360552", entity, byte[].class);
                System.out.println(res.getStatusCode());
            } catch (Exception e) {
                logger.error("error", e);
            }
        }

    }

    private static String data = "{}";
}
