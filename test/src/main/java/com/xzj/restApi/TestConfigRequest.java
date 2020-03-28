package com.xzj.restApi;

import com.alibaba.fastjson.JSONObject;
import com.xzj.util.GZipUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @date 2019/9/5 9:42
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TestConfigRequest {
    public static String str = "{\n" +
            "   \"as\" : \"296E81FE-E0E6-452c-BAEE-2C8712F6FB9D1\",\n" +
            "   \"ci\" : {\n" +
            "      \"av\" : \"2.0.7\",\n" +
            "      \"tn\" : \"111111111111\",\n" +
            "      \"ui\" : \"11555\",\n" +
            "      \"un\" : \"111111111111\"\n" +
            "   },\n" +
            "   \"di\" : {\n" +
            "      \"ch\" : \"Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz\",\n" +
            "      \"ci\" : \"64\",\n" +
            "      \"cm\" : \"Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz\",\n" +
            "      \"di\" : \"04CK2906-E074-43f5-97A0-CC030D2ED7D7fs\",\n" +
            "      \"dsi\" : \"192.168.1.1\",\n" +
            "      \"hs\" : \"202020202020202020202020415a303445433930\",\n" +
            "      \"mac\" : \"02-00-4c-4f-4f-50\",\n" +
            "      \"ov\" : \"Microsoft Windows 7 Professional Service Pack 1 version 6.1.7601\",\n" +
            "      \"tm\" : 12206\n" +
            "   },\n" +
            "   \"ds\" : {\n" +
            "      \"sa\" : 1024\n" +
            "   },\n" +
            "   \"i\" : \"560da44a-8720-438a-b70b-7ef5d2f6f702\",\n" +
            "   \"v\" : \"10085\"\n" +
            "}";
    public static void main(String[] args) throws Exception {
        for (int i = 0; i <1 ; i++) {

           // System.out.println(str);
            byte[] data = str.getBytes();
            data = GZipUtils.compress(data);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Br-AcCept-Encoding", "gzip");
            headers.add("ProtoTYPE", "json");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            HttpEntity<byte[]> entity = new HttpEntity<byte[]>(data, headers);
            //http://localhost:9999/config http://devtest.ibr.cc:20164/config
            ResponseEntity<byte[]> response = restTemplate.postForEntity("http://devtest.ibr.cc:20164/config", entity, byte[].class);
            //ResponseEntity<byte[]> response = restTemplate.postForEntity("http://localhost:9999/config", entity, byte[].class);

            byte[] body = response.getBody();
            byte[] result = GZipUtils.decompress(body);
            String res = JSONObject.parseObject(result, String.class);
            System.out.println(res);
        }
    }
}
