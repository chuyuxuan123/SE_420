# Task2-Find the resources comsumption of REST service
## With Spring Actuator
上次作业使用了spring boot内部的一个模块spring actuator，这个可以监测service的运行情况，默认暴露出的endpoint只有/health与/info，通过配置application.properities文件，加上
```java
management.endpoints.web.exposure.include=*
```
可以暴露出全部端口

这次要用到的是/actuator/metrics
> Spring Boot Actuator provides dependency management and auto-configuration for Micrometer, an application **metrics** facade that supports numerous monitoring systems

为了方便，我就直接使用了homework2搭建好的项目，启动之后，访问`http://localhost:8080/actuator/metrics`就可以看到各项指标了
![image1](./image/1.png)
可以看到，大部分指标都是针对JVM和tomcat的，也有一些系统方面的指标

比如访问`http://localhost:8080/actuator/metrics/jvm.memory.max`可以看到java虚拟机的最大内存，访问`http://localhost:8080/actuator/metrics/jvm.memory.used`可以看到使用的内存
![max memory](./image/2.png)
![used](./image/3.png)
图中数量的单位是byte，换算一下，内存的使用情况是157.6M/3267M

当然这个数字不是固定的，短时间内多次请求这一端口，可以看到内存的使用会变大

访问`http://localhost:8080/actuator/metrics/system.cpu.usage`可以看到CPU的使用情况
![cpu usage](./image/4.png)
当短时间多次访问时也能看出cpu的使用在上升

Spring Actuator为我们提供了很多查看service情况的端口，不过查看的方式比较麻烦，效果也不太直观



## References
* https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-metrics.html
* https://www.jianshu.com/p/e9ce05b44150
