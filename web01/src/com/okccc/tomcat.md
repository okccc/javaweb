### tomcat10
```shell
# Apache Tomcat是当前应用最广的JavaWeb服务器(免费),官网下载zip包直接安装 http://tomcat.apache.org/
# Servlet & Tomcat & Java对应的版本 https://tomcat.apache.org/whichversion.html
# 添加可执行权限
[root@cdh1 ~]$ cd bin/ && sudo chmod 755 *.sh
# 启动,如果页面 http://localhost:8080 打不开可能是端口被占用,vim conf/server.xml - Connector port="8088"
[root@cdh1 ~]$ ./startup.sh
# 关闭
[root@cdh1 ~]$ ./shutdown.sh
# 日志乱码：vim conf/logging.properties,修改乱码位置的encoding

# 目录结构
# bin：存放二进制可执行文件
# conf：存放各种配置文件,server.xml & web.xml(被所有web项目共用,也可以在项目自己的web.xml配置覆盖默认值)
# lib：存放tomcat需要的各种jar包比如servlet-api.jar,该目录下的jar包被所有web项目共享
# logs：存放日志文件,记录tomcat启动和关闭信息
# temp：存放临时文件
# webapps：存放web项目,tomcat自带5个项目,比如访问examples项目 http://localhost:8088/examples/index.html
# 不指定上下文路径就是ROOT项目 http://localhost:8088/index.jsp,可以修改该文件的<h2>标签内容查看浏览器页面变化
# url组成部分和项目资源对应关系：localhost 服务器硬件ip地址,8088 服务器软件tomcat端口,examples 项目名称,index.html 项目资源

# web项目部署方式
# 1.将编译好的项目直接放到webapps目录
# 2.将编译好的项目放在磁盘上的任意目录,通过conf/Catalina/localhost/*.xml指定实际路径
# <Context path="/web01" docBase="/Users/okc/IdeaProjects/javaweb/out/artifacts/web01_war_exploded" />
```
![](images/1.1_web项目标准结构.png)
