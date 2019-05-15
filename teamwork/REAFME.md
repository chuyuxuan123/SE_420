# SE_420 团队项目记录
## 2019/5/15
这周的milestone是要完成一个CI的环境（continuous integration）

我先选择的是Jenkins+Github的平台自己搭建

### 服务器初试
为了这次作业我第一次去阿里云买了一台学生机，9.5一个月其实也是非常的划算，刚买完的时候我是不知道怎么用的，然后看的是阿里云上面的简单的教程，有点差别就是上面的教程大部分是针对centos的系统，而我选的是Ubuntu，不过两者殊途同归操作都差不多

在windows上访问服务器用的是putty客户端连接也非常的方便，也能够传输文件

阿里云会默认暴露出三个端口 http:80，https:443，ssh:22
可以直接用22端口和账户密码连接到服务器，接着就能和用命令行一样操作了

连接到服务器后就是搭建环境，首先安装docker，然后安装jenkins，然后把jdk也装了上去，安装过程就是参考各个项目官网上面的文档，然后直接启动jenkins就可以了

### 配置jenkins
这里注意的是本来服务器的防火墙是没有激活的，可以用`ufw status`查看，然后不知道安装的哪一步里把防火墙打开了，所以这里要**把8080端口暴露出去，直接`ufw allow 8080`**，然后非常坑的是，**在阿里云服务器的管理端还要把8080添加到自定义防火墙中**

然后一顿设置（这个明天再补充），然后就可以跑了