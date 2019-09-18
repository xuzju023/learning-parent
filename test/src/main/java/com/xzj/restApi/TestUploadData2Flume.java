package com.xzj.restApi;

import com.xzj.util.GZipUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @date 2019/9/12 9:57
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TestUploadData2Flume {
    public static String str = "{\n" +
            "    \"as\": \"6867CDE7-E7E2-4de3-B67B-7E9DA3C37173\",\n" +
            "    \"di\": \"119.137.54.210\",\n" +
            "    \"e\": [\n" +
            "        {\n" +
            "            \"st\": 1568639491000,\n" +
            "            \"t\": 2,\n" +
            "            \"v\": {\n" +
            "                \"st\": 1,\n" +
            "                \"cid\": \"2\",\n" +
            "                \"v\": \"1.2.1\",\n" +
            "                \"di\": {\n" +
            "                    \"ov\": \"windows 7s\",\n" +
            "                    \"tm\": 2,\n" +
            "                    \"ch\": \"3\",\n" +
            "                    \"cm\": \"4\",\n" +
            "                    \"ci\": \"5\",\n" +
            "                    \"hs\": \"7\",\n" +
            "                    \"mac\": \"8\",\n" +
            "                    \"di\": \"9\",\n" +
            "                    \"dsi\": \"\"\n" +
            "                },\n" +
            "                \"ci\": {\n" +
            "                    \"ui\": \"SDF\",\n" +
            "                    \"tn\": 1,\n" +
            "                    \"un\": \"DSF\",\n" +
            "                    \"av\": \"SDF\"\n" +
            "                },\n" +
            "                \"ds\": {\n" +
            "                    \"sa\": 1,\n" +
            "                    \"sc\": 2,\n" +
            "                    \"ame\": 3,\n" +
            "                    \"ac\": 4,\n" +
            "                    \"sas\": 5\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"i\": \"aa562e08-3375-44b7-8c3e-8f77c9fc55c5\"\n" +
            "}";

    public static void main(String[] args) throws Exception {
        System.out.println(str);
        byte[] data = str.getBytes();
        data = GZipUtils.compress(data);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Br-AcCept-Encoding", "gzip");
        headers.add("ProtoTYPE", "json");
        headers.add("GUID", "/");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(data, headers);
        //http://localhost:9999/config http://devtest.ibr.cc:20164/upload
        for (int i = 0; i < 10; i++) {
            ResponseEntity<String> response = restTemplate.postForEntity("http://devtest.ibr.cc:20164/upload", entity, String.class);
            System.out.println(response.getStatusCode());
            
        }

        //System.out.println(response.getBody());
        //System.out.println(Status.NORMAL.ordinal());
    }
}
