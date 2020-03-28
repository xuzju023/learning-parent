package com.bonree.dataspout.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {
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
        String gzip = "test";
        byte[] before = gzip.toString().getBytes();
        byte[] after = GZipUtils.compress(gzip.toString().getBytes());
        DecimalFormat df = new DecimalFormat(".00000000000000000000000000000000000000000000000000000000000000000000000000000");
        System.out.println(df.format(after.length/before.length)+"%");
    }
        
    
}
