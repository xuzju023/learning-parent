package com.xzj.base;


import java.io.*;
import java.net.InetAddress;
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
public class Client {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(32, 32, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 1; i++) {
            try {
                Socket socket
                        = new Socket("127.0.0.1", 10061);
                executor.execute(new Thread(new ClientThread(countDownLatch, i, socket)));
            } catch (IOException e) {
                countDownLatch.countDown();
                e.printStackTrace();
            }
        }
        TimeUnit.HOURS.sleep(1);

    }

    static class ClientThread implements Runnable {
        private int num;
        private Socket socket;
        private CountDownLatch countDownLatch;

        public ClientThread(CountDownLatch countDownLatch, int num, Socket socket) {
            this.countDownLatch = countDownLatch;
            this.num = num;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                System.out.println(num);
                //发送给服务端
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(outputStream, true);
                InputStream in = new ByteArrayInputStream((num + "").getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                writer.println(reader.readLine());
                writer.flush();
                // socket.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
