package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Author: okccc
 * @Date: 2023/12/4 16:57:53
 * @Desc: HttpServletRequest和HttpServletResponse
 *
 * HttpServletRequest
 * Tomcat将请求报文转换成HttpServletRequest对象,包含所有请求信息,在Tomcat调用service方法时传入
 *
 * HttpServletResponse
 * Tomcat同时创建HttpServletResponse对象,后面会转换成响应报文,包含所有响应信息,在Tomcat调用service方法时传入
 */
@WebServlet(value = "/servlet04")
public class Servlet04 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------- HttpServletRequest api ---------------");
        // 1.请求行相关
        System.out.println(request.getMethod());  // 请求方式 GET
        System.out.println(request.getScheme());  // 请求协议 http
        System.out.println(request.getProtocol());  // 协议版本 HTTP/1.1

        System.out.println(request.getRequestURI());  // 统一资源标识符 /a.html                资源
        System.out.println(request.getRequestURL());  // 统一资源定位符 http://ip:port/a.html  资源的具体路径,包含IP和端口号,与部署的具体服务器有关

        System.out.println(request.getLocalPort());   // 当前tomcat容器的端口号
        System.out.println(request.getServerPort());  // 客户端发送请求使用的端口号,如果服务器有代理就是代理服务器的端口号,没有代理就是tomcat端口号
        System.out.println(request.getRemotePort());  // 客户端软件自己也有端口号

        // 2.请求头相关
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + " = " + headerValue);
        }

        // 3.请求体相关
        // 根据参数名获取单个参数值
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 根据参数名获取多个参数值
        String[] values = request.getParameterValues("hobby");
        System.out.println("values = " + Arrays.toString(values));

        // 获取所有参数值
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            if (parameterValues.length > 1) {
                System.out.println(parameterName + " = " + Arrays.toString(parameterValues));
            } else {
                System.out.println(parameterName + " = " + parameterValues[0]);
            }
        }

        // 以上api专门用于获取key=value形式的参数,无论这些参数是在url?后面(get请求)还是在请求体中(post请求)
        // 如何获得请求体中的非键值对数据？
        // 从请求体中读取字符的字符输入流或者读取字节的字节输入流(2选1)
        BufferedReader br = request.getReader();
//        ServletInputStream is = request.getInputStream();
        System.out.println("br = " + br);

        System.out.println("--------------- HttpServletResponse api ---------------");
        // 响应行相关  HTTP/1.1  200/302/404/405/500
        response.setStatus(200);

        // 响应头相关
        String info = "<h1>hello</h1>";
        response.setContentType("text/html");
        response.setContentLength(info.getBytes().length);

        // 响应体相关
        response.getWriter().write(info);
    }

}
