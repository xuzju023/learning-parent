package reader.StringReader;

import java.io.BufferedInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * @Author: XuZhiJun
 * @Description:  从内存中读入
 * @Date: Created in 16:33 2019/5/13
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException{
        StringReader reader = new StringReader("今天天气真好32432432abc");
        int c;
        while(-1!=(c=reader.read())){
            System.out.println((char) c);
        }
    }
}
