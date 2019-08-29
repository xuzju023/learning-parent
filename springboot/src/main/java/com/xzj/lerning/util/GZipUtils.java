package com.xzj.lerning.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipUtils {
    public enum Status {NORMAL, NOTENABLED, EXPIRE, FREEZE};
    public static String str = "{\n" +
            "   \"as\" : \"7BE58DD7-50B8-492c-AC0A-9ED8D74E8081\",\n" +
            "   \"ci\" : {\n" +
            "      \"av\" : \"8.0.0.1\",\n" +
            "      \"tn\" : \"usertel\",\n" +
            "      \"ui\" : \"userid\",\n" +
            "      \"un\" : \"usertel\"\n" +
            "   },\n" +
            "   \"di\" : {\n" +
            "      \"ch\" : \"Intel(R) Core(TM) i5-6500 CPU @ 3.20GHz\",\n" +
            "      \"ci\" : \"64\",\n" +
            "      \"cm\" : \"Intel(R) Core(TM) i5-6500 CPU @ 3.20GHz\",\n" +
            "      \"di\" : \"87CDA954-1B56-4aff-875F-B313BAF57FF0\",\n" +
            "      \"dsi\" : \"211.167.230.100\",\n" +
            "      \"hs\" : \"    W -DCW6C6YAFRUVZ\",\n" +
            "      \"mac\" : \"02-00-4c-4f-4f-50\",\n" +
            "      \"ov\" : \"Microsoft Windows 7 Professional Service Pack 1 version 6.1.7601\",\n" +
            "      \"tm\" : 12174\n" +
            "   },\n" +
            "   \"ds\" : {\n" +
            "      \"sa\" : 1024\n" +
            "   },\n" +
            "   \"i\" : \"560da44a-8720-438a-b70b-7ef5d2f6f702\",\n" +
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
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(data);
            // 解压缩  
            decompress(bais, baos);
            data = baos.toByteArray();
        } finally {
            baos.flush();
            baos.close();
            if (bais != null) {
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
     * @param content
     * @return
     * @throws Exception boolean
     * @Description: 判断是否是gzip格式
     * @Title: GZipUtils
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
        System.out.println(str);
        byte[] data = str.getBytes();
        data = compress(data);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(data,headers);
        //http://localhost:9999/config http://devtest.ibr.cc:20164/config
        ResponseEntity<byte[]> response = restTemplate.postForEntity("http://devtest.ibr.cc:20164/config", entity, byte[].class);

        byte[] body = response.getBody();
        byte[] result = decompress(body);
        String res = JSONObject.parseObject(result, String.class);
        System.out.println(res);
        //System.out.println(Status.NORMAL.ordinal());
    }


}
