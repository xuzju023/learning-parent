package fileMerge;

import java.io.File;

public class TestMergeFile {
    
    public static void main(String[] args) throws Exception {
        //File file = new File(TestMergeFile.class.getResource(""));

        String path = new File("").getAbsoluteFile().toString()+"\\io\\src\\main\\resource\\childrenFile3";
        String path2 = new File("").getAbsoluteFile().toString()+"\\io\\src\\main\\resource";
        File file = new File(path);
        /*File[] files = file.listFiles();
        File mergeFile = new File(path2,"file_merge");
        FileOutputStream outputStream = new FileOutputStream(mergeFile, true);//文件追加写入

        byte[] byt = new byte[10 * 1024 * 1024];
        FileInputStream temp = null;//分片文件
        for (File item : files) {
            temp = new FileInputStream(item);
            int len;
            while ((len = temp.read(byt)) != -1) {
                outputStream.write(byt, 0, len);
            }
        }
        temp.close();
        outputStream.close();
        System.out.println(mergeFile.delete());*/
        
   /*     if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File item : files) {
                item.delete();
            }
        }
        file.delete();*/
    }
}
