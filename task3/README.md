# Task3 - Containerize a spring boot application
## 安装Docker
* https://hub.docker.com/
* https://docs.docker.com/docker-hub/
* http://www.docker.org.cn/
* https://github.com/boot2docker/boot2docker
* https://www.cnblogs.com/SzeCheng/p/6822905.html

一开始找了一些资料了解一下什么是docker，看了半天不是很明白，于是直接上手做。

在官网上下载docker for windows的安装包，下载好之后告诉我说必须是win10专业版才能安装(???)，然后就去查了查，用的是Docker Toolbox来解决的

在阿里的镜像网站上下载了一个Toolbox一路默认执行安装，安装好之后桌面上多了三个快捷方式，然后发现Docker Quick start Terminal打不开。。。在安装的位置下面，有个start.sh文件，执行之后，还要下载一个boot2docker的镜像，然后默认的下载非常慢，直接手动去github上面下载，放到相应位置就好

下载好之后直接执行那个`start.sh`，他会自动帮忙配置一些东西，中间不需要有什么操作，一路下来提示安装完成

输入
```
docker run hello-world
```
他会自动从官方镜像中pull下来hello-world，然后会有输出`Hello from Docker`

命令行输入`docker`可以看到各项命令，这样，在win下用Docker Toolbox搭建的docker环境就搞好了

## Dockerize a REST Service 
* https://spring.io/guides/gs/spring-boot-docker/
* https://docs.docker.com/engine/reference/builder/
* https://www.jianshu.com/p/d05642c32929


一开始先按照spring guides上面的教程配的，加了几个plugin，但是由于环境的原因，docker并不能启动起来

于是放弃这个想法，在同学给的教程中，直接新建一个文件夹，把maven打包好的jar包与Dockerfile放在一起
- Dockerfile
```
FROM openjdk:8-jdk-alpine

VOLUME /tmp

MAINTAINER chuyuxuan

COPY demo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
```

执行`docker build -t chuyuxuan/docker .`创建image

执行`docker run -d --name docker -p 8080:8080 chuyuxuan/docker` 开始运行

不过这样的话，端口并不能映射到localhost

通过docker-machine ip default查到默认的宿主IP是192.168.99.100

在浏览器中输入192.168.99.100:8080就能看到页面了

执行`docker login`之后输入账号密码登录docker hub

然后把这个image push到dockerhub上

ps: 在执行这一些列操作前，要先运行一下docker，否则会出错

最后附上dockerhub的镜像地址https://cloud.docker.com/u/chuyuxuan/repository/docker/chuyuxuan/task3-docker