package reader.bufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*从文件中一行一行读*/
public class BufferdInputFile {
    public static String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String s;
        StringBuilder sb = new StringBuilder();
        while (((s = in.readLine()) != null)) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        //哈哈哈
        System.out.println(read("./io/src/main/java/stream/BufferdInputFile.java"));
    }

}










