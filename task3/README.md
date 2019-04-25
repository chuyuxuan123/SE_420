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