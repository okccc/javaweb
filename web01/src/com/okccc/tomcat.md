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

### idea开发web项目并部署到tomcat
```shell
# idea关联tomcat,可以有多个
Intellij IDEA - Settings - Build,Execution,Development - Application Servers - Tomcat Server - Tomcat Home

# idea先创建一个空项目javaweb,然后创建普通java项目web01
File - New - Project - Java
# 检查项目的SDK,语法版本,以及项目编译后的输出目录
File - Project Structure - Project - SDK & Language Level & Compiler output
# 项目要部署到tomcat运行,添加tomcat的依赖
File - Project Structure - Modules - web01 - Dependencies - Add - Library - Application Server Libraries - Tomcat 10.1.15
# 将普通java项目转换成web项目,发现多了个带特殊标记的web目录,将废弃的index.jsp删除使用index.html代替
web01 - (double shift - Actions) - Add Framework Support - Java EE - Web Application(6.0)
# 创建resources目录并标记为资源目录,不然该目录不参与编译
web01 - resources - Mark Directory as - Resources Root
# 在web/WEB-INF下创建lib目录并标记为项目依赖,后面统一使用maven管理
web01 - web - WEB-INF - lib - Add as Library - Level - Module Library
# 查看当前项目有哪些环境依赖
File - Project Structure - Modules - web01 - Dependencies

# 将src源码和resources配置文件构建成可以发布的app,使用工具栏的Build Artifacts手动构建,或者部署到tomcat后启动服务时会自动构建(推荐)
Build - Build Artifacts - web01:war exploded - Build/Clean/Edit
Edit Configurations - Add New Configuration - Tomcat Server - Local - Deployment & Server
