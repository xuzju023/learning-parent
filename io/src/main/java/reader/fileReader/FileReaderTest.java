package reader.fileReader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class FileReaderTest {
    public static void main(String[] args) throws Exception{
        FileReader fileReader = new FileReader("./io/src/main/java/1.txt");
        //InputStreamReader fileReader = new InputStreamReader(new FileInputStream("./io/src/main/java/1.txt"),"UTF-8");
        StringBuffer sb = new StringBuffer();
        int value=0;
        while(value!=-1){
             value = fileReader.read();
             sb.append((char)value);
        }
        System.out.println(sb.toString());
    }
}
