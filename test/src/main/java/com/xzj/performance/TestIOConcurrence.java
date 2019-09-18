package com.xzj.performance;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @date 2019/9/5 9:56
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TestIOConcurrence {


    public static void main(String[] args) throws Exception{
        new TestIOConcurrence().start();
    }

    private void start() throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(800, 1000, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        CountDownLatch latch = new CountDownLatch(10);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 150; i++) {
            pool.execute(new MyThread(latch, i));
            latch.countDown();
        }
        pool.shutdown();
        //阻塞当前线程1小时 直到线程池关闭
        pool.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("总耗时:" + (end - begin) / 1000);
    }

    private class MyThread implements Runnable {
        private CountDownLatch latch;
        private int index;

        public MyThread(CountDownLatch latch, int index) {
            this.latch = latch;
            this.index = index;
        }

        @Override
        public void run() {
            try {
                latch.await();
                File file = new File("F:", "10M.txt");
                File outFile = new File("F:/out", "file" + index);
                long begin = System.currentTimeMillis();
                //ByteBuffer.allocate()
                //useBufferdStream(file, outFile); // 1 2  2 2 
                useNio(file,outFile);
                long end = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " " + outFile.getName() + "写入成功 cost:" + (end - begin) / 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void useNio(File file, File outFile) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream(outFile, true);
            FileChannel inchannel = fileInputStream.getChannel();
            FileChannel outchannel = outputStream.getChannel();
            int capacity = 1024;
            ByteBuffer buffer = ByteBuffer.allocate(capacity);
            int length;
            while((length=inchannel.read(buffer))!=-1){
                buffer.flip();
                outchannel.write(buffer);
                buffer.clear();
            }
            fileInputStream.close();
            outputStream.close();
        }

        private void useBufferdStream(File file, File outFile) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream0 = new FileOutputStream(outFile, true);
            BufferedOutputStream outputStream = new BufferedOutputStream(outputStream0);
            int length;
            //BufferedInputStream的默认缓冲区最大8192字节
            byte[] tempBytes = new byte[10*1024];
            while ((length = inputStream.read(tempBytes)) != -1) {
                outputStream.write(tempBytes, 0, length);
            }
            outputStream.close();
            inputStream.close();
        }

        private void useFileInputStream(File file, File outFile) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = new FileOutputStream(outFile, true);

            int length;
            byte[] tempBytes = new byte[1024];
            while ((length = fileInputStream.read(tempBytes)) != -1) {
                outputStream.write(tempBytes, 0, length);
            }
            outputStream.close();
            fileInputStream.close();
        }
    }
}
