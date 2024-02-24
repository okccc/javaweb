package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/5 16:59:43
 * @Desc: 乱码问题
 *
 * ASCII: 英文和一些通用符号使用的编码,因为所有字符集都兼容ASCII,所以不存在英文乱码
 * GB2312: 对ASCII的中文扩展
 * GBK/GB18030: 包括了GB2312的所有内容,同时又增加了近20000个新的汉字和符号
 * Unicode: 包括了全球的符号和编码,每个字符用3~4个字节表示,浪费空间
 * UTF-8(Unicode Transformation Format): 可变长的编码方式,互联网使用最广泛的Unicode实现方式,根据语种决定字符长度,字母1个字节,汉字3个字节
 *
 * 乱码是因为数据的编码和解码使用了不同的字符集,计算机存储的数据都是0/1,字符集就相当于字典,将人能看懂的字符翻译成计算机能看懂的0/1
 * 但是各个字符集规则不一样,Windows默认gbk中文占2个字节,Linux默认utf8中文占3个字节,如果先用gbk编码再用utf8解码就会出现中文乱码
 *
 * 修改代码可以点击Services的三个绿色小箭头Deploy All热部署,修改Tomcat配置必须右上角重启服务器
 *
 * HTML乱码
 * Intellij IDEA - Settings - Editor - File Encodings - Global Encoding & Project Encoding - <meta charset="UTF-8">
 *
 * Tomcat控制台乱码
 * Server/Tomcat Catalina Log乱码修改tomcat/conf/logging.properties对应位置的encoding,让tomcat适应当前系统(Windows/Linux)
 */
// http://localhost:8088/encoding.html
@WebServlet(value = "/servlet06")
public class Servlet06 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------- 测试请求乱码 ---------------");
        // Tomcat10默认使用UTF-8对请求数据进行解码,如果客户端提交数据时以GBK对请求参数进行编码就会出现乱码

        // 方式1.后端获取请求参数前先设置解码字符集和前端编码字符集保持一致(不推荐)
//        request.setCharacterEncoding("GBK");

        // 方式2.前端发送请求时使用UTF-8对请求参数(get请求在url后面,post请求在请求体中)进行编码<meta charset="UTF-8">(推荐)
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        System.out.println("--------------- 测试响应乱码 ---------------");
        // Tomcat10默认使用UTF-8对响应数据进行编码,如果客户端接收数据时以GBK对响应数据进行解码就会出现乱码

        // 方式1.设置响应体编码字符集和客户端解码字符集保持一致,但是客户端解码字符集是无法预测的(不推荐)
//        response.setCharacterEncoding("GBK");
        
        // 方式2.设置Content-Type响应头,告诉客户端使用指定字符集解析响应体,这样就不用管客户端用的什么浏览器和字符集(推荐)
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("你好");
    }

}
