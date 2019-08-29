package stream.outputStream;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {
    
    public static String str="{\n" +
            "   \"ci\" : {\n" +
            "      \"av\" : \"ClientVersion\",\n" +
            "      \"tn\" : \"usertel\",\n" +
            "      \"ui\" : \"userid\",\n" +
            "      \"un\" : \"usertel\"\n" +
            "   },\n" +
            "   \"di\" : {\n" +
            "      \"ch\" : \"Intel(R) Core(TM) i5-6500 CPU @ 3.20GHz\",\n" +
            "      \"ci\" : \"64\",\n" +
            "      \"cm\" : \"Intel(R) Core(TM) i5-6500 CPU @ 3.20GHz\",\n" +
            "      \"di\" : \"{B6013F16-B7EE-48e6-8556-2825F2FE9795}\",\n" +
            "      \"dsi\" : \"211.167.230.100\",\n" +
            "      \"hs\" : \"    W -DCW6C6YAFRUVZ\",\n" +
            "      \"mac\" : \"02-00-4c-4f-4f-50\",\n" +
            "      \"ov\" : \"Microsoft Windows 7 Professional Service Pack 1 version 6.1.7601\",\n" +
            "      \"tm\" : 12174\n" +
            "   },\n" +
            "   \"ds\" : {\n" +
            "      \"sa\" : 1024\n" +
            "   },\n" +
            "   \"i\" : \"123455678\",\n" +
            "   \"v\" : \"1.0.0.0\"\n" +
            "}";
    
    
	public static final int BUFFER = 1024;  
    public static final String EXT = ".gz";  

    /** 
     * 数据压缩 
     *  
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] compress(byte[] data) throws Exception {  
        ByteArrayInputStream bais = new ByteArrayInputStream(data);  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        // 压缩  
        compress(bais, baos);  
        byte[] output = baos.toByteArray();  
        baos.flush();  
        baos.close();  
        bais.close();  
        return output;  
    }  


    /** 
     * 数据压缩 
     *  
     * @param is 
     * @param os 
     * @throws Exception 
     */  
    public static void compress(InputStream is, OutputStream os)  
            throws Exception {  
        GZIPOutputStream gos = new GZIPOutputStream(os);  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = is.read(data, 0, BUFFER)) != -1) {  
            gos.write(data, 0, count);  
        }  
        gos.finish();  
        gos.flush();  
        gos.close();  
    }  


    /** 
     * 数据解压缩 
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decompress(byte[] data) throws Exception {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        ByteArrayInputStream bais = null;
    	try{
	        bais = new ByteArrayInputStream(data);  
	        // 解压缩  
	        decompress(bais, baos);  
	        data = baos.toByteArray();  
    	} finally {
            baos.flush();  
            baos.close();  
            if(bais != null){
                bais.close();  
            }
        }
        return data;  
    }  

    /** 
     * 数据解压缩 
     *  
     * @param is 
     * @param os 
     * @throws Exception 
     */  
    public static void decompress(InputStream is, OutputStream os)  
            throws Exception {  
        GZIPInputStream gis = new GZIPInputStream(is);  
        int count;  
        byte data[] = new byte[BUFFER];  
        while ((count = gis.read(data, 0, BUFFER)) != -1) {  
            os.write(data, 0, count);  
        }  
        gis.close();  
    }
    
    /**
     * @Description: 判断是否是gzip格式
     * @Title: GZipUtils
     * @param content
     * @return
     * @throws Exception 
     * boolean
     * @user <a href=mailto:zhangmc@bonree.com>张梦川</a>
     */
    public static boolean isGzip(byte[] content) throws Exception {
    	if (content.length < 2) {
    		return false;
    	}
    	int ss = (content[0] & 0xff) | ((content[1] & 0xff) << 8);
    	if (ss != GZIPInputStream.GZIP_MAGIC) {
    		return false;
    	}
    	return true;
    }

    public static void main(String[] args) throws Exception {
        byte[] data = str.getBytes();
        data=compress(data);
        InputStream stream = new ByteArrayInputStream(data);
        FileOutputStream outputStream = new FileOutputStream("./io/src/main/java/3.txt");
        int value=0;
        while (value!=-1){
            value=stream.read();
            outputStream.write(value);
        }
    }
        
    
}
