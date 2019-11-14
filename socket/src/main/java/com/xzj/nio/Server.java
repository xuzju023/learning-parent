package com.xzj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @date 2019/10/31 20:18
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Server {

    public static void testServer() throws IOException {

        // 1、获取Selector选择器
        Selector selector = Selector.open();

        // 2、获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3.设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 4、绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8080));

        // 5、将通道注册到选择器上,并注册的操作为：“接收”操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        // 6、采用轮询的方式，查询获取“准备就绪”的注册过的操作
        while (selector.select() > 0) {
            // 7、获取当前选择器中所有注册的选择键（“已经准备就绪的操作”）
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> it = selectionKeys.iterator();
            while (it.hasNext()) {
                // 8、获取“准备就绪”的时间
                SelectionKey selectedKey = it.next();

                // 9、判断key是具体的什么事件
                if (selectedKey.isAcceptable()) {
                    System.out.println("----------");
                    // 10、若接受的事件是“接收就绪” 操作,就获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 11、切换为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12、将该通道注册到selector选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectedKey.isReadable()) {
                    //Thread thread = new Thread(new ReadHandler(selectedKey));
                    //pool.execute(thread);
                    new ReadHandler(selectedKey).run();
                }

                // 15、移除选择键
                it.remove();
            }
        }

        // 7、关闭连接
        serverSocketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        testServer();
    }

    
    static class ReadHandler implements Runnable {
        private SelectionKey selectedKey;

        public ReadHandler(SelectionKey selectedKey) {
            this.selectedKey = selectedKey;
        }

        @Override
        public void run() {
            try {
                // 13、获取该选择器上的“读就绪”状态的通道
                SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                // 14、读取数据
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int length;
                if ((length = socketChannel.read(byteBuffer)) != -1) {
                    byteBuffer.flip();
                    System.out.print(new String(byteBuffer.array(), 0, length));
                    byteBuffer.clear();
                }
               // socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
