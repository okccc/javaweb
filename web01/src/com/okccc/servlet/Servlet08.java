package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/11 10:22:41
 * @Desc: Cookie和Session配合实现会话管理
 *
 * 会话管理
 * HTTP协议是无状态的,服务器为了辨别用户身份会给客户端返回cookie值,客户端再次请求时会携带这个cookie
 *
 * cookie的时效性
 * 会话级cookie：服务器没有指定存活时间,浏览器的cookie存在内存,浏览器开着就一直在,浏览器关闭就释放(默认)
 * 持久化cookie：服务器明确指定存活时间,浏览器的cookie存在硬盘,不受浏览器关闭影响,到时间自动释放
 *
 * cookie的提交路径
 * 访问互联网资源时不能每次都把所有cookie带上,可以对cookie的路径进行设置,访问不同资源携带不同cookie
 *
 * cookie放在浏览器容易暴露,所以不能放敏感信息
 * session放在服务器更安全,服务器会为每个客户端创建session对象,并将其id即JSESSIONID以cookie的形式发送给客户端
 *
 * session的时效性
 * 用户量增大之后服务器要创建很多session对象,如果不及时释放服务器的内存很快就会耗尽
 * session默认存活时间30分钟,在tomcat/conf/web.xml配置,也可以在当前项目的web.xml配置将其覆盖,或者通过api设置
 *
 * 张三第一次去银行办业务,银行会为张三开户(session),并给张三发送一张银行卡(cookie)
 * 张三后面再去银行办业务,就会携带这个银行卡(cookie),银行根据银行卡找到张三的账户(session)
 */
@WebServlet(value = "/servlet08")
public class Servlet08 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------- 测试cookie,先注释session ---------------");
        // 创建cookie对象
        Cookie cookie1 = new Cookie("c1", "v1");
        Cookie cookie2 = new Cookie("c2", "v2");

        // 设置cookie存活时间(秒)
        cookie1.setMaxAge(60);

        // 设置cookie提交路径,只在访问servlet10时才携带
        cookie2.setPath("/servlet10");

        // 将cookie放入响应对象
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        System.out.println("--------------- 测试session,先注释cookie ---------------");
        // 获取session对象
        // getSession()处理逻辑：判断请求中是否携带JSESSIONID
        // 是：根据JSESSIONID去服务端找对应的HttpSession对象,找到就直接返回,没找到就创建新的HttpSession对象并将JSESSIONID以cookie的形式放入HttpServletResponse对象
        // 否：创建新的HttpSession对象并将JSESSIONID以cookie的形式放入HttpServletResponse对象
        HttpSession session = request.getSession();

        // 获取JSESSIONID
        System.out.println("jsessionid = " + session.getId());
        // 判断是否是新创建的session
        System.out.println("isNew = " + session.isNew());

        // 设置session最大闲置时间(秒),60秒没使用就失效,如果60秒内有使用会刷新新的60秒,和cookie.setMaxAge()不一样
        session.setMaxInactiveInterval(60);
    }

}
