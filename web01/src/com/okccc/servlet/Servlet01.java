package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 *
 * 注意事项
 * 1.servlet-api.jar导入方式
 * 可以手动添加到WEB-INF/lib但是没必要,因为tomcat自带这个jar包,给项目添加tomcat依赖其实就是添加servlet-api.jar
 * Provided表示项目构建时不会携带,查看编译后的WEB-INF/lib发现确实没有这个jar包,运行时由tomcat提供,但是编码时需要不然导包会报错
 *
 * 2.Content-Type响应头
 * 浏览器请求Servlet资源时打开F12发现Response Headers没有Content-Type
 * MIME类型用来告诉客户端响应的是什么类型的数据,客户端以此类型决定用什么方式解析响应体
 * 静态资源.html/.css/.png有扩展名,根据扩展名可以在tomcat的web.xml找到对应的mime-mapping设置Content-Type
 * 动态资源Servlet没有扩展名,所以要在代码里手动设置Content-Type,如果不设置浏览器默认将响应体当成"text/html"进行解析
 *
 * DefaultServlet：tomcat会根据url-pattern去找对应的Servlet,找不到就交给DefaultServlet,主要针对静态资源
 * http://localhost:8088/index.html、http://localhost:8088/static/img/a.jpg、http://localhost:8088/a/b/c/test.html
 */
@WebServlet(value = "/servlet01")  // 使用注解代替web.xml
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
