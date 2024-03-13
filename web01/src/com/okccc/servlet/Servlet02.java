package com.okccc.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/2 20:45:53
 * @Desc: Servlet对象的生命周期由Tomcat管理,后期对象都是由Spring框架创建和调用,开发人员只需关注业务
 */
@WebServlet(
        value = "/servlet02",
        // 查看tomcat的web.xml搜索load-on-startup发现1~5已经被占用,建议从6开始
        // 默认值-1表示tomcat启动时不会创建和初始化该Servlet,注意观察设置前后当前Servlet创建和初始化的执行时机
        loadOnStartup = -1
)
public class Servlet02 extends HttpServlet {

    // 1.创建,第一次请求或服务启动时执行,1次
    public Servlet02() {
        System.out.println("---------- Servlet 构造器实例化 ----------");
    }

    // 2.初始化,构造完毕后执行,1次
    @Override
    public void init(ServletConfig config) {
        System.out.println("---------- Servlet 初始化 ----------");
    }

    // 3.服务,每次请求时执行,N次
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建和初始化只执行一次,说明Servlet是单例的,所以service方法不要修改成员变量,并发场景会有线程安全问题
        System.out.println("---------- Servlet 服务 ----------");
    }

    // 4.销毁,服务关闭时执行,1次
    @Override
    public void destroy() {
        System.out.println("---------- Servlet 销毁 ----------");
    }

}
