package com.xzj.util;

import java.io.*;
import java.net.Socket;

/**
 * @date 2019/10/11 17:46
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class SocketForHttpTest {


    private int port;
    private String host;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SocketForHttpTest() {
    }

    public SocketForHttpTest(String host, int port) throws Exception {

        this.host = host;
        this.port = port;

        /**
         * http协议  
         */
        socket = new Socket(this.host, this.port);

        /**
         * https协议  
         */
        // socket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(this.host, this.port);


    }


    public void sendPost() throws IOException {
        String path = "/upload";
//        String data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode("张三", "utf-8") + "&" +
//                URLEncoder.encode("age", "utf-8") + "=" + URLEncoder.encode("32", "utf-8");
        // String data = "name=zhigang_jia";    
        //System.out.println(">>>>>>>>>>>>>>>>>>>>>" + data);
        OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
        bufferedWriter = new BufferedWriter(streamWriter);
        bufferedWriter.write("POST " + path + " HTTP/1.1\r\n");
        bufferedWriter.write("Host: " + this.host + "\r\n");
        bufferedWriter.write("Content-Length: " + 0 + "\r\n");
        bufferedWriter.write("Content-Type: application/json\r\n");
        bufferedWriter.write("\r\n");
        //bufferedWriter.write(data);

        bufferedWriter.write("\r\n");
        bufferedWriter.flush();

        BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "utf-8"));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
        }
        System.out.println("ok");
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        /**
         * http协议测试  
         */
        SocketForHttpTest forHttpTest = new SocketForHttpTest("192.168.56.1", 9999);
        /**
         * https协议测试  
         */
        //SocketForHttpTest forHttpTest = new SocketForHttpTest("192.168.56.1", 9999);
        try {
            forHttpTest.sendPost();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
