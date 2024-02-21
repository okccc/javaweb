package com.okccc.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: okccc
 * @Date: 2023/12/3 12:17:10
 * @Desc: ServletConfig和ServletContext
 *
 * ServletConfig
 * Tomcat容器会为每个Servlet创建一个ServletConfig对象,并通过init方法传入,为当前Servlet提供初始配置参数
 *
 * ServletContext
 * Tomcat容器会为每个Application创建一个ServletContext上下文对象,也叫应用域对象,可以为所有Servlet提供初始配置参数
 */
@WebServlet(value = "/servlet03", initParams = {@WebInitParam(name = "k1", value = "v1")})
public class Servlet03 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------- ServletConfig获取参数 ---------------");
        // 获取ServletConfig对象
        ServletConfig servletConfig = getServletConfig();

        // 获取初始参数
        String v1 = servletConfig.getInitParameter("k1");
        System.out.println("k1 = " + v1);

        // 获取所有初始参数
        Enumeration<String> parameterNames = servletConfig.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = servletConfig.getInitParameter(parameterName);
            System.out.println(parameterName + " = " + parameterValue);
        }

        System.out.println("--------------- ServletContext获取参数 ---------------");
        // 获取ServletContext对象,上下文对象也叫应用域对象,所以命名为application
        ServletContext application = getServletContext();
        ServletContext servletContext1 = servletConfig.getServletContext();
        ServletContext servletContext2 = request.getServletContext();
        System.out.println(application == servletContext1);  // true
        System.out.println(application == servletContext2);  // true

        // 获取初始参数
        String encoding = application.getInitParameter("encoding");
        System.out.println("encoding = " + encoding);

        // 获取所有初始参数
        Enumeration<String> appParameterNames = application.getInitParameterNames();
        while (appParameterNames.hasMoreElements()) {
            String parameterName = appParameterNames.nextElement();
            String parameterValue = application.getInitParameter(parameterName);
            System.out.println(parameterName + " = " + parameterValue);
        }

        // 获取项目上下文路径
        String contextPath = application.getContextPath();
        System.out.println("contextPath = " + contextPath);  // web01

        // 获取资源真实路径
        String realPath = application.getRealPath("static/img/a.png");
        System.out.println("realPath = " + realPath);  // /Users/okc/IdeaProjects/javaweb/out/artifacts/web01_war_exploded/static/img/a.png

        // 往应用域放数据
        application.setAttribute("orc", "grubby");
        System.out.println(application.getAttribute("orc"));  // grubby
        application.removeAttribute("orc");
        System.out.println(application.getAttribute("orc"));  // null
    }

}
