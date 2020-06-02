package com.xzj.base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(9999);
        Socket client = socket.accept();
        AcceptMessage acceptMessage = new AcceptMessage(client);
        acceptMessage.start();
        acceptMessage.join();
        ProduceMessage produceMessage = new ProduceMessage(client);
        produceMessage.start();
        produceMessage.join();
    }

    static class AcceptMessage extends Thread {
        public Socket client;


        public AcceptMessage(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    System.out.println(reader.readLine());
                     reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    static class ProduceMessage extends Thread {
        public Socket client;


        public ProduceMessage(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = client.getOutputStream();
                while (true) {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
                    writer.write("已收到");
                    // reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
