package stream.inputStream;

import java.io.*;

public class FileInputStreamTest {
    public static void main(String[] args) throws Exception {
        FileInputStream stream = new FileInputStream("./io/src/main/java/1.txt");
        int value=0;
        StringBuffer sb = new StringBuffer();
        while (value!=-1){
            value=stream.read();
            char c=(char)value;
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
