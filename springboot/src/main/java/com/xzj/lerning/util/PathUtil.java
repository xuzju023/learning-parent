package com.xzj.lerning.util;

import com.xzj.lerning.LerningApplication;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathUtil {
    /**
     * @param request
     * @return 返回结果类似于 “F:\workSpace\bookQr\src\main\webapp\”
     */
    public static String getAppRootPath(HttpServletRequest request) {
        //ServletActionContext.getServletContext().getRealPath("/")+"upload";
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 自定义文件保存路径
     *
     * @param request
     */
    public static String getCustomRootPath(HttpServletRequest request) {
        String path = "";
        Properties prop = new Properties();
        InputStream in = LerningApplication.class.getResourceAsStream("/config/jdbc.properties");
        try {
            prop.load(in);
            path = prop.getProperty("FILE_PATH").trim();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * @param request
     * @return http://www.qh.com:8080/projectName
     */
    public static String getHttpURL(HttpServletRequest request) {
        StringBuffer buff = new StringBuffer();
        buff.append("http://");
        buff.append(request.getServerName());
        buff.append(":");
        buff.append(request.getServerPort());
        buff.append(request.getContextPath());
        return buff.toString();
    }

    public static void main(String[] args) {
        String a = null;
        if (a==null||a.isEmpty()) {
            System.out.println(a);
        }
    }
}
