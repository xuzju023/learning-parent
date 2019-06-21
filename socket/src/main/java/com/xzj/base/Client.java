package com.xzj.base;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:14 2019/6/10
 */
public class Client {


    public static void main(String[] args) throws Exception{
        InetAddress localHost = InetAddress.getLocalHost();
        Socket socket =new Socket(localHost,8888);
        //Socket socket = new Socket("localhost",8888);
        Thread thread = new Thread(new ClientThread(socket));
        thread.start();
        //接收服务端的消息
        while(true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while((msg=reader.readLine())!=null){
                System.out.println(msg);
            }
        }
    }

    static class ClientThread implements Runnable{
        private Socket socket;

        public ClientThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //发送给服务端
                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    InputStream in = System.in;
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    writer.println(reader.readLine());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
