package com.xzj.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: XuZhiJun
 * @Description:
 * @Date: Created in 14:12 2019/6/25
 */
public class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String variables = filterConfig.getInitParameter("variables");
        System.out.println(variables);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("======= 开始执行doFilter ========");
        chain.doFilter(request, response);
        System.out.println("======= 结束执行doFilter ========");

    }

    @Override
    public void destroy() {
        System.out.println("销毁---------");
    }
}
