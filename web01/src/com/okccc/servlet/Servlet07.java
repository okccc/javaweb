package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/6 11:07:06
 * @Desc: 路径问题
 *
 * 目标资源路径：http://localhost:8088/web01/static/img/a.jpg
 * 相对路径不以"/"开头,受当前资源路径影响,目标资源路径 = 当前资源所在路径 + src属性值
 * 绝对路径以"/"开头,不受当前资源路径影响,目标资源路径 = 当前项目基准路径 + src属性值
 * 绝对路径要给src补充项目上下文"/web01",如果上下文变了那所有src都要改,可以定义<base>标签设置公共前缀将相对路径转换成绝对路径
 * 这样项目上下文变了只要改<base>标签就行,但是这样还是很耦合,于是生产环境通常将项目上下文设置成"/"缺省路径,就可以省略<base>标签
 *
 * 1.web/index.html
 * 当前资源路径：http://localhost:8088/index.html
 * 当前资源：index.html
 * 当前资源所在路径：http://localhost:8088/
 * 目标资源相对路径写法 <img src="static/img/a.jpg"/>
 * 当前项目基准路径：http://localhost:8088
 * 目标资源绝对路径写法：<img src="/static/img/a.jpg"/>
 *
 * 2.web/a/b/c/test.html
 * 当前资源路径：http://localhost:8088/a/b/c/test.html
 * 当前资源：test.html
 * 当前资源所在路径：http://localhost:8088/a/b/c/
 * 目标资源相对路径写法：<img src="../../../static/img/a.jpg"/>
 * 当前项目基准路径：http://localhost:8088
 * 目标资源绝对路径写法：<img src="/static/img/a.jpg"/>
 *
 * 3.web/WEB-INF/templates/a.html,受保护资源只能通过请求转发访问
 * 当前资源路径：http://localhost:8088/servlet07
 * 当前资源：servlet07
 * 当前资源所在路径：http://localhost:8088/
 * 目标资源相对路径写法：<img src="static/img/a.jpg"/>
 * 当前项目基准路径：http://localhost:8088
 * 目标资源绝对路径写法：<img src="/static/img/a.jpg"/>
 */
@WebServlet(value = "/servlet07")
public class Servlet07 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求转发访问WEB-INF下的受保护资源
        request.getRequestDispatcher("WEB-INF/templates/a.html").forward(request, response);
    }

}
