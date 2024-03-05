package com.okccc.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/12 18:13:44
 * @Desc: Filter对象的生命周期由Tomcat管理
 */
@WebFilter(
        filterName = "lifeCycleFilter",
        initParams = {@WebInitParam(name = "dateTimePattern", value = "yyyy-MM-dd HH:mm:ss")},
        urlPatterns = {"*.html", "/servlet01"},
        servletNames = {"servlet01"}
)
public class Filter02 implements Filter {

    // 1.创建,服务启动时执行,1次
    public Filter02() {
        System.out.println("---------- Filter 构造器实例化 ----------");
    }

    // 2.初始化,构造完毕后执行,1次(Filter在项目启动时会默认初始化,而Servlet可以设置load-on-startup控制是否在项目启动时初始化)
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("---------- Filter 初始化 ----------");
        // FilterConfig对象获取初始化参数
        System.out.println(filterConfig.getInitParameter("dateTimePattern"));
    }

    // 3.过滤,每次请求时执行,N次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("---------- Filter 过滤请求和响应 ----------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // 4.销毁,服务关闭时执行,1次
    @Override
    public void destroy() {
        System.out.println("---------- Filter 销毁 ----------");
    }

}
