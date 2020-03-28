package com.xzj.restApi;

import com.alibaba.fastjson.JSONObject;
import com.xzj.util.GZipUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @date 2019/9/12 9:57
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TestUploadData2Flume {
    public static String str = "{\n" +
            "   \"as\" : \"F6651B53-DFBF-4606-B73D-3BD9B4AAC0A6\",\n" +
            "   \"di\" : \"111.193.180.156\",\n" +
            "   \"e\" : [\n" +
            "      {\n" +
            "         \"st\" : 1571280490657,\n" +
            "         \"t\" : 3,\n" +
            "         \"v\" : {\n" +
            "            \"ci\" : {\n" +
            "               \"av\" : \"8.0.0.3\",\n" +
            "               \"tn\" : \"135555\",\n" +
            "               \"ui\" : \"yuanid\",\n" +
            "               \"un\" : \"135555\"\n" +
            "            },\n" +
            "            \"dfi\" : \"1\",\n" +
            "            \"di\" : {\n" +
            "               \"ch\" : \"Intel(R) Core(TM) i5-8259U CPU @ 2.30GHz\",\n" +
            "               \"ci\" : 64,\n" +
            "               \"cm\" : \"Intel(R) Core(TM) i5-8259U CPU @ 2.30GHz\",\n" +
            "               \"di\" : \"467FAE02-960A-4902-B73F-10F6A23D5915\",\n" +
            "               \"dsi\" : \"10.211.55.1\",\n" +
            "               \"hs\" : \"474e3435474150314a4e344641314e5354434d36\",\n" +
            "               \"mac\" : \"00-1c-42-1e-76-f2\",\n" +
            "               \"ov\" : \"Microsoft Windows 7 Professional  version 6.1.7600\",\n" +
            "               \"tm\" : 4095\n" +
            "            },\n" +
            "            \"ds\" : {\n" +
            "               \"ac\" : \"9.129420\",\n" +
            "               \"ame\" : \"54.914063\",\n" +
            "               \"sa\" : \"2147\",\n" +
            "               \"sas\" : \"235562\",\n" +
            "               \"sc\" : \"9.375000\",\n" +
            "               \"su\":\"111\"\n" +
            "            },\n" +
            "            \"ei\" : \"\",\n" +
            "            \"s\" : \"xuzhijun408\",\n" +
            "            \"st\" : 1571626103000,\n" +
            "            \"v\" : \"8.0.0.3\"\n" +
            "         }\n" +
            "      }\n" +
            "   ],\n" +
            "   \"i\" : \"3bcff9ad-5bd9-4af5-8241-468a729edf91\"\n" +
            "}";

    public static void main(String[] args) throws Exception {
        System.out.println(str);
        JSONObject obj = JSONObject.parseObject(str);
        JSONObject temp = obj.getJSONArray("e").getJSONObject(0).getJSONObject("v");
        String[] sort = new String[]{"1","1","1"};
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < sort.length; i++) {
            temp.put("st",i*1000+currentTimeMillis);
            temp.put("ei",sort[i]);
            String res = obj.toString();
            System.out.println(obj);
            byte[] data = res.getBytes();
            data = GZipUtils.compress(data);
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            System.out.println("======");
            System.out.println("======");
            headers.add("Br-AcCept-Encoding", "GZIP");
            headers.add("ProtoTYPE", "json");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,5);
            headers.add("GUID", uuid);
            HttpEntity<byte[]> entity = new HttpEntity<byte[]>(data, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("http://devtest.ibr.cc:20164/upload", entity, String.class);
            System.out.println(response.getStatusCode());

        }
        //go();
    }
    
//    static String data2="{\n" +
//            "    \"as\": \"17EC270C-F660-4238-8E4E-47CAE610DEF1\",\n" +
//            "    \"di\": \"119.137.53.14\",\n" +
//            "    \"e\": [\n" +
//            "        {\n" +
//            "            \"st\": 1568950427327,\n" +
//            "            \"t\": 2,\n" +
//            "            \"v\": {\n" +
//            "                \"di\": {\n" +
//            "                    \"ch\": \"Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz\",\n" +
//            "                    \"di\": \"929FD6D7-A0B8-436a-B2A1-8DE9E2C632C7\",\n" +
//            "                    \"ci\": 64,\n" +
//            "                    \"ov\": \"Microsoft Windows 7 Professional Service Pack 1 version 6.1.7601\",\n" +
//            "                    \"ovm\":\"2|6|3|0|0\",\n" +
//            "                    \"tm\": 12206,\n" +
//            "                    \"cm\": \"Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz\",\n" +
//            "                    \"dsi\": \"192.168.1.1\",\n" +
//            "                    \"hs\": \"202020202020202020202020415a303445433930\",\n" +
//            "                    \"mac\": \"00-ff-dd-7a-6c-0e\"\n" +
//            "                },\n" +
//            "                \"ds\": {\n" +
//            "                    \"sc\": 40,\n" +
//            "                    \"ac\": 30,\n" +
//            "                    \"sas\": 300,\n" +
//            "                    \"ame\": 100,\n" +
//            "                    \"sa\": 1024,\n" +
//            "                    \"su\":60\n" +
//            "                },\n" +
//            "                \"ci\": {\n" +
//            "                    \"ui\": \"01\",\n" +
//            "                    \"av\": \"v1.1\",\n" +
//            "                    \"un\": \"13419609465\",\n" +
//            "                    \"tn\": \"13419609465\"\n" +
//            "                },\n" +
//            "                \"st\": 1568950671000,\n" +
//            "                \"cid\": \"fdgfdgf\",\n" +
//            "                \"v\": \"1.0.2\"\n" +
//            "            }\n" +
//            "        }\n" +
//            "    ],\n" +
//            "    \"i\": \"560da44a-8720-438a-b70b-7ef5d2f6f702\"\n" +
//            "}";
//    static void go() throws Exception{
//        System.out.println(data2);
//        byte[] data = data2.getBytes();
//        data = GZipUtils.compress(data);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        System.out.println("======");
//        System.out.println("======");
//        headers.add("Br-AcCept-Encoding", "GZIP");
//        headers.add("ProtoTYPE", "json");
//        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0,5);
//        headers.add("GUID", uuid);
//        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(data, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity("http://devtest.ibr.cc:20164/upload", entity, String.class);
//        System.out.println(response.getStatusCode());
//    }
//    
}
