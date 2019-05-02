# Homework 3

## 初试

### 过程记录

我先是新建了两个spring boot项目，一个项目（服务一）与homework1大致相同，主要是负责wordladder部分，然后第二个项目（服务二）是用spring security的一个简单示例，用户密码等信息存在内存中



一开始想的微服务就是两个项目，用户同时把用户名密码和wordladder的请求一起发给服务一，然后服务一用用户名密码请求服务二，从服务二得到的请求来判断是否应该进行wordladder的查找



在服务之间的通信时用的RestTemplate，装配好username和password信息后用post方法发送给服务二的/login接口，如果验证登录成功就把response的header添加一项{"login":"success"}，这样服务一就可以知道是否验证成功了

### 改进

这个验证的思路非常的奇怪，需要把用户名密码和wordladder的信息一起发给服务一，这样感觉违反了微服务那种解耦比较明显的特点，理想的情况是在请求wordladder时，服务一会向服务二发一个认证请求，服务二接受之后根据是否认证给服务一发送一个回应，然后服务一决定是否调用wordladder的模块

