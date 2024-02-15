package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/1 10:44:23
 * @Desc: Servlet运行在服务端,所以必须在WEB项目中开发,部署到Tomcat容器运行,主要负责接收请求和响应数据
 *
 * 静态资源：无需通过代码运行生成的资源,在程序运行之前就已经确定的数据,html/css/js/img/音频文件/视频文件
 * 动态资源：需要通过代码运行动态生成的资源,在程序运行之前无法确定的数据,Servlet/Thymeleaf...
 * 买蛋糕：柜台样品就是静态资源,现场定制就是动态资源
 *
 * Servlet开发步骤
 * 1.创建JavaWeb项目,将tomcat添加为项目依赖
 * 2.自定义Servlet类继承HttpServlet,重写service方法处理业务,接收请求响应数据
 * 3.在web.xml配置当前Servlet的请求映射路径
 *
 * tomcat接收请求后会将请求报文转换成HttpServletRequest对象,请求行/请求头/请求体
 * tomcat同时创建HttpServletResponse对象,后面会将其转换成响应报文响应客户端,响应行/响应头/响应体
 * tomcat根据请求路径找到对应Servlet,将其实例化后调用service方法并传入HttpServletRequest和HttpServletResponse
 */
public class Servlet01 extends HttpServlet {

    @Override
    // 建议使用debug模式运行,方便随时打断点调试数据
    // 查看源码发现service也是调用doGet/doPost,继承HttpServlet要么重写service方法,要么重写doGet/doPost方法
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取请求参数,无论参数是在url?后面还是在请求体中
        String username = request.getParameter("username");

        // 2.处理业务逻辑
        String info = "<h1>YES</h1>";
        if ("grubby".equals(username)) {
            info = "<h1>NO</h1>";
        }

        // 3.响应数据,给响应头设置Content-Type
        response.setContentType("text/html");
        response.getWriter().write(info);
    }

}
