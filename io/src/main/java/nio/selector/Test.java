package nio.selector;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @date 2019/9/25 15:52
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        //设置非阻塞模式
        channel.configureBlocking(false);
        ServerSocket serverSocket = channel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        //FileChannel channel = file.getChannel();
        SelectionKey key = channel.register(selector, SelectionKey.OP_ACCEPT);
        String a="1";
        key.attach(a);
        int interestSet = key.interestOps();
        System.out.println(interestSet);
    }
}
