package com.xzj.restApi;

import com.alibaba.fastjson.JSON;
import com.xzj.model.SDKDataTransfer;
import com.xzj.producer.UploadConfigDataProducer;
import com.xzj.util.GZipUtils;

/**
 * @date 2019/9/10 13:55
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
public class TestUploadData2Kafka {
    public static String uploadData = "{\n" +
            "    \"i\": \"c278e388-8ecb-42c5-a507-f635f053dfad\",\n" +
            "    \"as\": \"0A223931-A7AA-400b-AA74-E08984C2CB1B\",\n" +
            "    \"di\": \"106.121.128.66\",\n" +
            "    \"e\": [\n" +
            "        {\n" +
            "            \"t\": 1,\n" +
            "            \"st\": \"1568253584691\",\n" +
            "            \"v\": {\n" +
            "                \"v\": \"v9.0.0.2\",\n" +
            "                \"di\": {\n" +
            "                    \"ch\": \"Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz\",\n" +
            "                    \"ci\": 64,\n" +
            "                    \"cm\": \"Intel(R) Core(TM) i5-4590 CPU @ 3.30GHz\",\n" +
            "                    \"di\": \"779D5F6E-456E-4a05-B837-D2EC7566F1DC\",\n" +
            "                    \"dsi\": \"192.168.4.200\",\n" +
            "                    \"hs\": \"     WD-WCC6Y6KZ8NTU\",\n" +
            "                    \"mac\": \"8c-ec-4b-8d-17-78\",\n" +
            "                    \"ov\": \"Microsoft Windows 10 Insider Preview Professional  version 10.0.17763\",\n" +
            "                    \"tm\": 16211\n" +
            "                },\n" +
            "                \"ci\": {\n" +
            "                    \"av\": \"8.0.1\",\n" +
            "                    \"tn\": \"15611750917\",\n" +
            "                    \"ui\": \"1386\",\n" +
            "                    \"un\": \"wangxiaoxiao15611750917\"\n" +
            "                },\n" +
            "                \"ds\": {\n" +
            "                    \"sa\": 1024,\n" +
            "                    \"sc\": 40,\n" +
            "                    \"ame\": 100,\n" +
            "                    \"ac\": 30,\n" +
            "                    \"sas\": 300\n" +
            "                },\n" +
            "                \"ru\": \"http://www.reeiss211.com/v1v1?username=12345678&token=12345678\",\n" +
            "                \"si\": 3688,\n" +
            "                \"li\": \"118.194.54.131\",\n" +
            "                \"lp\": 8080,\n" +
            "                \"ti\": \"124.108.103.104\",\n" +
            "                \"tp\": 443,\n" +
            "                \"dt\": 4578952,\n" +
            "                \"ct\": 12345678,\n" +
            "                \"sti\": \"12345678\",\n" +
            "                \"rt\": \"8000\",\n" +
            "                \"rti\": \"6666668\",\n" +
            "                \"dti\": \"78922223\",\n" +
            "                \"et\": \"1568253584691\",\n" +
            "                \"cna\": [\n" +
            "                    \"www.a.shifen.com\"\n" +
            "                ],\n" +
            "                \"pt\": \"5\",\n" +
            "                \"sc\": \"200\",\n" +
            "                \"se\": \"0\",\n" +
            "                \"dsi\": \"211.167.230.100\",\n" +
            "                \"rh\": \"GET / HTTP/1.1rnsd: rnUser-Agent: Mozilla/5E.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.116 Safari/537.36rnHost: www.yahoo.comrnrn\",\n" +
            "                \"rd\": \"89760\",\n" +
            "                \"rhe\": \"HTTP/1.1 200 OKrnServer: nginx/1.9.5rnDate: Tue, 17 Sep 2019 08:03:09 GMTrnContent-Type: application/octet-stream;charset=UTF-8rnContent-Length: 55rnConnection: keep-alivernrn\",\n" +
            "                \"rds\": \"700800\"\n" +
            "            }\n" +
            "        }\n" +
            "    ]\n" +
            "}";



    public static void main(String[] args) throws Exception {
        //System.out.println(uploadData);
        upload();
       
    }


    private static void upload() throws Exception {
        byte[] compressData = GZipUtils.compress(uploadData.getBytes());
        SDKDataTransfer sdkDataTransfer = constructData2Kafka(compressData, System.currentTimeMillis(), "122.11.58.187");
        UploadConfigDataProducer.getInstance().sendMsg("WINSDK_RAW_DATA_TOPIC", JSON.toJSONBytes(sdkDataTransfer));
        Thread.sleep(1000);
    }

    private static SDKDataTransfer constructData2Kafka(byte[] content, long currentTimeMillis, String deviceIp) throws Exception {
        SDKDataTransfer dataTransfer = new SDKDataTransfer();
        dataTransfer.setServerTime(currentTimeMillis);
        dataTransfer.setCompressType(1);
        dataTransfer.setReqInfo(content);
        //upload请求
        dataTransfer.setReqType(2);
        dataTransfer.setAdditionalInfo("deviceIp" + ":" + deviceIp);
        return dataTransfer;
    }

}
