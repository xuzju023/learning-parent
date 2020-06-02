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


    @Override
    public void run() {
        try {

            Socket socket = new Socket("127.0.0.1", 9999);
            //发送给服务端
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            new Thread(() -> {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    System.out.println(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            outputStream.write("nihao".getBytes());
            outputStream.flush();
            outputStream.close();
            //socket.close();
//            PrintWriter writer = new PrintWriter(outputStream, true);
//            writer.write("你好");
//            writer.flush();
//            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 500, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            pool.execute(new Client());
        TimeUnit.HOURS.sleep(1);
        // pool.shutdown();
    }


}
