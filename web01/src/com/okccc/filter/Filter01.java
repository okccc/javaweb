package com.okccc.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/12 16:21:41
 * @Desc: Filter可以对请求和响应进行过滤,是责任链模式的典型案例,也是Web项目最实用的技术之一
 *
 * Filter的位置是在tomcat容器创建HttpServletRequest和HttpServletResponse之后,请求目标资源之前
 * 请求到达目标资源之前先经过doFilter(),该方法控制请求是否继续,到达目标资源之后响应之前还会经过该方法
 *
 * 使用场景：登录校验、网站乱码、事务控制、日志记录、性能分析
 * 生活举例：公司前台、小区保安、地铁闸机
 *
 * Filter开发步骤
 * 1.自定义类实现Filter接口,重写doFilter()方法
 * 2.在web.xml配置该Filter,或者使用@WebFilter注解
 */
public class Filter01 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 1.HttpServletRequest到达目标资源之前的功能代码,登录校验、权限控制
        System.out.println("before filterChain.doFilter() invoked");

        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 2.放行,将请求和响应对象继续传递给后续资源,如果没有这行代码那么请求到此为止
        filterChain.doFilter(request, response);

        // 3.HttpServletResponse转换成响应报文之前的功能代码
        System.out.println("after filterChain.doFilter() invoked");
    }

}
