package com.xzj.lerning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @date 2019/8/29 15:31
 * @author: <a href=mailto:xuzj@bonree.com>胥智钧</a>
 * @Description:
 **/
@Controller
public class ContentTypeTestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * application/octet-stream
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String test(@RequestBody byte[] file) {
        // System.out.println(file.getSize());
        //System.out.println(formatDoubleNumber((double) data.length / 1024 / 1024) + " M");
        return "ok";
    }

    /**
     * multipart/form-data
     *
     * @param myfile
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String test(@RequestParam("myfile") MultipartFile myfile) {
        logger.info("文件大小为 {} 文件名称 {}", myfile.getSize(), myfile.getOriginalFilename());
        return "ok";
    }
}
