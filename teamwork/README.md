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

#### 使用github插件

浏览器输入$SERVER_IP:8080，可以看到jenkins的初试页面，按照提示找到初始密码，输入后设置管理员账号，然后就可以使用jenkins了

首先在自己的GitHub的setting中选择 Developer settings > Personal access tokens 点击新建一个token，输入密码后进入一个页面，输入token的名字，然后下面选择repo和 admin:repo_hook两项，确定生成后复制一下记住，因为这个只会出现这一次，之后就找不到了

在系统管理 > 系统设置中有一个github服务器，新增一个，api-url选择https://api.github.com，然后凭据中新建一个，类型选择secret text ，然后在secret一栏中输入上面生成的token，然后**选择下面的管理hook**（选中这个选项可以不用在GitHub中设置webhook，推荐直接选上），然后保存设置

新建一个项目选择一个freestyle~项目，general中选择github项目，并且输入项目的地址，源码管理中选择git，并且输入GitHub项目的地址，branch to build中可以设置为空，就是所有分支，代码库浏览器选择githubweb，ur选择项目的url（与上面项目地址url相比少了后缀——**.git**）

![1558007562310](G:\SE_420\teamwork\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558007562310.png)



构建触发器选择**GitHub hook trigger for GITScm polling**构建环境选择Use secret text(s) or file(s)，下面绑定选择上面设置的secret text就可以了

![1558007607153](G:\SE_420\teamwork\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558007607153.png)

构建操作这一项可以选择set build status  to "pending"

![1558007734403](G:\SE_420\teamwork\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558007734403.png)

构建后操作选择set GitHub commit status然后默认选项，只是在默认的what中加入一个状态判断返回的选项

![1558007671417](G:\SE_420\teamwork\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558007671417.png)

然后如果上面的那个管理hook没有选，就要在GitHub项目设置中找到webhook这一项，添加，这里的payload url必须是你的Jenkins的url加上**/github-webhook/**，比如我的是跑在8080端口，这里就要设置成**http://server_ip:8080/github-webhook/**，后面的数据形式无所谓，触发条件设置成just push就好了

如果前面选择了管理hook，那么jenkins会自动添加上这个webhook

这样的话我们的CI的环境就有了，虽然没有CI的功能，因为push之后没有jenkins没有任何操作，所以理论上因该直接status设置为success，我随便试了一下，在本地push可以触发这个hook，然后jenkins的控制台上就有这次构建的输出，然后新建一个分支并且先setupstream，然后再github网站上进行pull requset也会触发hook，然后就会有jenkins的输出提示

![1558008259620](G:\SE_420\teamwork\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558008259620.png)

check就是jenkins的check（其实并没有），然后注意到上面有个commit是小黄点，那是因为一开始我设置的就是只返回pending，那个是pending的stauts

#### references

* <https://jenkins.io/download/>
* <https://docs.docker.com/install/linux/docker-ce/ubuntu/>
* <https://www.jianshu.com/p/22b7860b4e81>

## 2019/5/16

### Build a Java app with Maven

上面的项目是一个没什么实际用户的实验，我想用maven项目试一下，用jenkins的pipeline试一下

踩坑：

新建一个jenkins项目，并把原来的项目停止，结果github的hook还是会到原来的项目上

* <https://issues.jenkins-ci.org/browse/JENKINS-35132?page=com.atlassian.jira.plugin.system.issuetabpanels%3Aall-tabpanel>

jenkins执行pipeline的时候，会建立一个docker环境，然后由于权限不够，要先把jenkins加入docker用户组里

### references

* <https://resources.github.com/whitepapers/practical-guide-to-CI-with-Jenkins-and-GitHub/>
* <https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/>
* <https://jenkins.io/doc/book/pipeline/syntax/>