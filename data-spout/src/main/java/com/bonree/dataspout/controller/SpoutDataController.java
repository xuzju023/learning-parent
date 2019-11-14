package com.bonree.dataspout.controller;

import com.bonree.dataspout.model.RequestData;
import com.bonree.dataspout.model.ResponseData;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @date 2019/11/6 11:41
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@RestController
public class SpoutDataController {

    @RequestMapping("/sendData")
    public ResponseData sent(@RequestBody RequestData data) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        headers.add("Br-Accept-Encoding", "gzip");
        headers.add("ProtoTYPE", "json");
        HttpEntity<byte[]> entity = new HttpEntity<>(data.getData().toString().getBytes(), headers);
        if (null == data.getConcurrenceNum() && data.getTime() == null) {
            ResponseEntity<String> response = restTemplate.postForEntity(data.getUrl(), entity, String.class);
            HttpStatus code = response.getStatusCode();
            System.out.println(code);
            return ResponseData.SUCCESS;
        }
        return ResponseData.ERROR;
    }
}
