package com.xzj.base;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:14 2019/6/10
 */
public class Client implements Runnable {
    private CountDownLatch latch;
    private int num;

    public Client(CountDownLatch latch, int num) {
        this.latch = latch;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 1000; i++) {

                latch.await();
                Socket socket = new Socket("127.0.0.1", 9999);
                //发送给服务端
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                ByteArrayInputStream in = new ByteArrayInputStream((num + "").getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                writer.println(reader.readLine());
                writer.flush();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 500, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        CountDownLatch latch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            pool.execute(new Client(latch, i));
            latch.countDown();
        }
        TimeUnit.HOURS.sleep(1);
        // pool.shutdown();
    }


}
