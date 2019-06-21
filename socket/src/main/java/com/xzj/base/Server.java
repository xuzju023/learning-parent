package com.xzj.base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 10:06 2019/6/10
 */
public class Server {
    public static List<Socket> clients=new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket socketServer = new ServerSocket(8888);
        while (true) {
            //每连接一个客户端添加一个线程
            Socket socket = socketServer.accept();
            clients.add(socket);
            ServerThread runnable = new ServerThread(socket);
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }

    static class ServerThread implements Runnable {
        private Socket socket;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //监听每个线程的消息
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    //获取主机名
                    String hostName = socket.getInetAddress().getHostName();
                    String msg = hostName+":\n"+reader.readLine();
                    //发送给所有的客户端
                    for (Socket client : clients) {
                        OutputStream outputStream = client.getOutputStream();
                        PrintWriter printWriter = new PrintWriter(outputStream, true);
                        printWriter.println(msg);
                    }
                } catch (Exception e) {

                }
            }
        }
    }

}
