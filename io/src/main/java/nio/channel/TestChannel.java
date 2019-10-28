package nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @date 2019/9/23 20:27
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TestChannel {
    public static void main(String[] args) throws Exception {
        RandomAccessFile file = new RandomAccessFile("E:/E1DCC3A4-AF51-412c-9800-838103B2CEFD.dmp","rw");
        RandomAccessFile targetFile = new RandomAccessFile("E:/target/E1DCC3A4-AF51-412c-9800-838103B2CEFD.dmp","rw");
        FileChannel fromChannel = file.getChannel();
        FileChannel toChannel = targetFile.getChannel();
//        copy1(fromChannel,toChannel);
        copy2(fromChannel,toChannel);
        fromChannel.close();
        toChannel.close();
    }
    static void copy2(FileChannel fromChannel,FileChannel toChannel) throws Exception{
        fromChannel.transferTo(0,fromChannel.size(),toChannel);
        
    }
    static void copy1(FileChannel fromChannel,FileChannel toChannel) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (-1!=fromChannel.read(buffer)){
            buffer.flip();
            toChannel.write(buffer);
            buffer.clear();
        }
    }
    
    
}
