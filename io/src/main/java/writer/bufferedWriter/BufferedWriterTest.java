package writer.bufferedWriter;

import java.io.*;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 16:52 2019/5/13
 */
public class BufferedWriterTest {

    public static void main(String[] args) throws IOException{
//        Socket编程中,尽量用PrintWriter取代BufferedWriter，下面是PrintWriter的优点：
//
//        1. PrintWriter的print、println方法可以接受任意类型的参数，而BufferedWriter的write方法只能接受字符、字符数组和字串；
//
//        2. PrintWriter的println方法自动添加换行，BufferedWriter需要显示调用newLine方法；
//
//        3. PrintWriter的方法不会抛异常，若关心异常，需要调用checkError方法看是否有异常发生；
//
//        4. PrintWriter构造方法可指定参数，实现自动刷新缓存（autoflush）；
//
//        5. PrintWriter的构造方法更广。

        //bufferedWriter效率比FileWriter高
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./io/src/main/java/2.txt"));

        PrintWriter writer = new PrintWriter(bufferedWriter);
        //StringReader reader = new StringReader("今天天气不错sdfds");
        BufferedReader reader = new BufferedReader(new FileReader("./io/src/main/java/1.txt"));
        //int a;
        String a;
        while (null!=(a=reader.readLine())){
            //writer.println(a);
            writer.println(a);
            //writer.write(a);
        }
        writer.close();
        reader.close();
        //调用close方法 清空缓冲区
        bufferedWriter.close();
    }
}
