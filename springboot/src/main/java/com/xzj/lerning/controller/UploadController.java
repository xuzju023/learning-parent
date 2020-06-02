package com.xzj.lerning.controller;

import com.xzj.lerning.model.FileRes;
import com.xzj.lerning.util.FileMd5Util;
import com.xzj.lerning.util.NameUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UploadController {
    /**
     * 上传文件
     *
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/upload")
    public Map<String, Object> upload(
            HttpServletRequest request, @RequestParam(value = "data",required = false)
            MultipartFile multipartFile) throws IllegalStateException, IOException, Exception {
        String action = request.getParameter("action");
        String uuid = request.getParameter("uuid");
        String fileName = request.getParameter("name");
        int size =  Integer.valueOf(request.getParameter("size"));//总大小
        int total = Integer.valueOf(request.getParameter("total"));//总片数
        int index = Integer.valueOf(request.getParameter("index"));//当前是第几片
        String fileMd5 = request.getParameter("filemd5"); //整个文件的md5
        String date = request.getParameter("date"); //文件第一个分片上传的日期(如:20170122)
        String md5 = request.getParameter("md5"); //分片的md5
        //文件夹路径
        //String saveDirectory= FileOpera.CreatePath(date,uuid);
        String saveDirectory= null;
        //分片文件
        File path = new File(saveDirectory);
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(saveDirectory, uuid + "_" + index);
        //根据action不同执行不同操作. check:校验分片是否上传过; upload:直接上传分片
        Map<String, Object> map = null;
        if("check".equals(action)){
            String md5Str = FileMd5Util.getFileMD5(file);
            //已经上传部分文件 断点续传
            if (md5Str != null && md5Str.length() == 31) {
                //查询临时表 当前片段MD5是否存在
                //List<FileRes> li= resService.select(md5,file.getPath());
                List<FileRes> li= null;
                //查看当前片段是否上传
                map = new HashMap<>();
                //map=FileOpera.FileExit(li);
                //返回执行下一段
                return map;
            }else {
                //重新上传一次
                map = new HashMap<>();
                map.put("flag", "1");
                map.put("fileId", uuid);
                map.put("status", true);
                return map;
            }
        }else if("upload".equals(action)){
            map = new HashMap<>();
            int a = -1;
            //未上传
            //删除文件后上传	 
            if (file.exists()) {
                file.delete();
            }
            multipartFile.transferTo(new File(saveDirectory, uuid + "_" + index));
            //添加进数据库
            FileRes fileRes = new FileRes();
            fileRes.setUuid(uuid);
            fileRes.setPath(file.getPath());
            fileRes.setSize(size);
            fileRes.setMd5(md5);
            fileRes.setStatus(1);
            fileRes.setCreateTime(new Timestamp(System.currentTimeMillis()));
            //a=resService.insert(fileRes);
            a=1;
            if(a<0) {//片段失败重新上传
                map = new HashMap<>();
                map.put("flag", "4");
                map.put("fileId", uuid);
                map.put("status", false);
                return map;
            }
        }
        if (path.isDirectory()) {
            File[] fileArray = path.listFiles();
            if (fileArray != null) {
                if (fileArray.length == total) {
                    int success=-1;
                    //分块全部上传完毕,合并
                    String suffix = NameUtil.getExtensionName(fileName);
                    //File newFile = new File(FileOpera.Createfolder(date), uuid + "." + suffix);
                    File newFile = new File("", uuid + "." + suffix);
                    
                    FileOutputStream outputStream = new FileOutputStream(newFile, true);//文件追加写入
                    byte[] byt = new byte[10 * 1024 * 1024];
                    int len;
                    FileInputStream temp = null;//分片文件
                    for (int i = 0; i < total; i++) {
                        int j = i + 1;
                        temp = new FileInputStream(new File(saveDirectory, uuid + "_" + j));
                        while ((len = temp.read(byt)) != -1) {
                            outputStream.write(byt, 0, len);
                        }
                    }
                    //关闭流
                    temp.close();
                    outputStream.close();
                    //修改FileRes记录为上传成功
                    FileRes fileRes = new FileRes();
                    fileRes.setStatus(1);
                    fileRes.setMd5(fileMd5);
                    fileRes.setPath(newFile.getPath());
                    fileRes.setSize((int) newFile.length());
                    fileRes.setSuffix(NameUtil.getExtensionName(fileName));
                    //success =fileResService.update(fileRes);
                    success=1;
                    map=new HashMap<>();
                    map.put("fileId", uuid);
                    map.put("flag", "5");
                    if(success>0) {
                        map.put("size",fileRes.getSize());
                        //map.put("timelength",fileRes.getTimeLength());
                        map.put("type",fileRes.getSuffix());
                        map.put("status", true);
                        map.put("path",fileRes.getPath());
                    }else {
                        map.put("status", false);
                    }
                    return map;
                }else if(index == 1){
                    //文件第一个分片上传时记录到数据库
                    FileRes fileRes = new FileRes();
                    fileRes.setMd5(fileMd5);
                    //List<FileRes> list = fileResService.selectByMd(fileRes);
                    List<FileRes> list=null;
                    String name = NameUtil.getFileNameNoEx(fileName);
                    if (name.length() > 50) {
                        name = name.substring(0, 50);
                    }
                    fileRes.setName(name);
                    fileRes.setSuffix(NameUtil.getExtensionName(fileName));
                    fileRes.setUuid(uuid);
                    //fileRes.setPath(FileOpera.Createfolder(date) + File.separator + uuid + "." + fileRes.getSuffix());
                    fileRes.setSize(size);
                    fileRes.setStatus(0);
                    fileRes.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    if (list == null || list.size() == 0) {
                        //this.fileResService.insert(fileRes);
                    }else {
                        //this.fileResService.update(fileRes);
                    }
                }
            }
        }
        map = new HashMap<>();
        map.put("flag", "3");
        map.put("fileId", uuid);
        map.put("status", true);
        return map;
    }


    /**
     * 上传文件前校验
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/isUpload")
    public Map<String, Object> isUpload(HttpServletRequest request,MultipartFile multipartFile) throws Exception {
        String md5 = request.getParameter("md5");
        //查看本文件是否存在
        FileRes fi = new FileRes();
        fi.setMd5(md5);
        fi.setStatus(1);
        //List<FileRes> list = fileResService.selectByMd(fi);
        //return FileOpera.FileExit(list);
        return null;
    }

}
