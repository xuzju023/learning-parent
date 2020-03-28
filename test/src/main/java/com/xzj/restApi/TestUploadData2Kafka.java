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
            "            \"ei\" : \"test\",\n" +
            "            \"s\" : \"sessionxz222\",\n" +
            "            \"st\" : 1571626103000,\n" +
            "            \"v\" : \"8.0.0.3\"\n" +
            "         }\n" +
            "      }\n" +
            "   ],\n" +
            "   \"i\" : \"3bcff9ad-5bd9-4af5-8241-468a729edf91\"\n" +
            "}";



    public static void main(String[] args) throws Exception {
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
        dataTransfer.setAdditionalInfo("GUID" + ":" + "12335s");
        return dataTransfer;
    }

}
