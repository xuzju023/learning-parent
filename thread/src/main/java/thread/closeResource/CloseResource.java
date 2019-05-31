package thread.closeResource;

import thread.interrupt.IOBlocked;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 11:08 2019/5/14
 */
public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InputStream socketInput = new Socket("localhost", 8080).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        //exec.execute(new IOBlocked(System.in));
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Shutting down all threads");
        exec.shutdownNow();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Closing "+socketInput.getClass().getName());
        socketInput.close();
        TimeUnit.SECONDS.sleep(1);
        //System.out.println("Closing "+System.in.getClass().getName());
        //System.in.close();
    }

}
