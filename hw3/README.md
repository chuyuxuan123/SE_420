# Homework 3

## 初试

### 过程记录

我先是新建了两个spring boot项目，一个项目（服务一）与homework1大致相同，主要是负责wordladder部分，然后第二个项目（服务二）是用spring security的一个简单示例，用户密码等信息存在内存中



一开始想的微服务就是两个项目，用户同时把用户名密码和wordladder的请求一起发给服务一，然后服务一用用户名密码请求服务二，从服务二得到的请求来判断是否应该进行wordladder的查找



在服务之间的通信时用的RestTemplate，装配好username和password信息后用post方法发送给服务二的/login接口，如果验证登录成功就把response的header添加一项{"login":"success"}，这样服务一就可以知道是否验证成功了

之后写好Dockerfile，build之后测试



### 改进

这个验证的思路非常的奇怪，需要把用户名密码和wordladder的信息一起发给服务一，这样感觉违反了微服务那种解耦比较明显的特点，理想的情况是在请求wordladder时，服务一会向服务二发一个认证请求，服务二接受之后根据是否认证给服务一发送一个回应，然后服务一决定是否调用wordladder的模块

### 踩坑

在windows下使用docker Toolbox不能映射到本地端口，必须用192.168.99.100宿主机端口来访问



Dockerfile中必须要暴露端口



在spring boot的项目中，比如服务一的请求就不能用localhost，必须用上面的那个地址



在wordladder中需要的dictionary文件，也需要在Dockerfile中copy到镜像中，并且复制到的地址要与代码中打开文件的地址相同（这一问题我在验证登录后才发现，找不到问题所在的时候用docker logs发现输出找不到文件位置，于是想出了解决方法）