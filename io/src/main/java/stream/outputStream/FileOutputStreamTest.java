package stream.outputStream;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;

public class FileOutputStreamTest {
    public static void main(String[] args) throws Exception {
        InputStream stream = new StringBufferInputStream("test");
        stream = new ByteArrayInputStream("test2".getBytes());
        FileOutputStream outputStream = new FileOutputStream("./io/src/main/java/2.txt");
        int value=0;
        while (value!=-1){
            value=stream.read();
            outputStream.write(value);
        }
    }
}
