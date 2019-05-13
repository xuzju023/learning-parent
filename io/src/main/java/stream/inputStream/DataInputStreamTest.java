package stream.inputStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class DataInputStreamTest { 
    public static void main(String[] args) throws IOException {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream("123abc三二一".getBytes()));

        try {
            while (inputStream.available()!=0){
                System.out.println((char) inputStream.readByte());
            }
        } catch (IOException e) {
            System.err.println("end of stream");
        }
    }
}
