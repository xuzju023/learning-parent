package com.xzj.restApi;

import com.xzj.util.SocketForHttpTest;
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
 * @date 2019/10/10 11:09
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class SDKSeverTest {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1000, 1000, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        RestTemplate restTemplate = new RestTemplate();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Mythread mythread = new Mythread(countDownLatch, restTemplate);
        for (int i = 0; i < 3; i++) {
            countDownLatch.countDown();
            executor.execute(mythread);
        }
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
                for (int i = 0; i < 1; i++) {
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
                HttpEntity<byte[]> entity = new HttpEntity<byte[]>("".getBytes(), headers);
                //ResponseEntity<byte[]> res = restTemplate.postForEntity("http://192.168.1.53:80/upload?key=8dd04674-50e1-4dc5-a6b0-e1d8af825254_863014031360552", entity, byte[].class);
                ResponseEntity<byte[]> res = restTemplate.postForEntity("http://localhost:10061/upload?key=8dd04674-50e1-4dc5-a6b0-e1d8af825254_863014031360552", entity, byte[].class);
                System.out.println(res.getStatusCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void excute2() {
            try {
                SocketForHttpTest forHttpTest = new SocketForHttpTest("192.168.56.1", 9999);
                forHttpTest.sendPost();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
