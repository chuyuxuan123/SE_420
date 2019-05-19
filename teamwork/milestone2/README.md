# CI环境搭建

我们选择在服务器上来搭建我们的CI平台，通过运行jenkins的docker镜像来搭建平台

## Docker安装

具体安装方式参考docker官网上的在Ubuntu环境下安装Docker

* https://docs.docker.com/install/linux/docker-ce/ubuntu/

## Jenkins安装

根据官网上的步骤下载Jenkins的镜像，这一步与启动一起解决

```sh
docker run \
  -u root \ 
  --rm \ 
  -d \ 
  -p 8080:8080 \ 
  -p 50000:50000 \ 
  -v jenkins-data:/var/jenkins_home \ 
  -v /var/run/docker.sock:/var/run/docker.sock \ 
  jenkinsci/blueocean 
```

直接回下载Jenkins的镜像并启动

启动后进行初始化设置，添加管理员账号等

## Sample

### 初始化项目

以一个maven项目为例，首先在github上新建一个项目

然后在该项目的webhook中加入CI的url，比如http://server_ip:8080/github-webhook/。然后在本地新建一个spring boot项目

在jenkins的控制台上，选择新建一个多分支项目

![1558259027599](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259027599.png)

然后根据提示进行配置

![1558259086200](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259086200.png)

其实配置中只需要添加自己的GitHub账号信息即可，其他的选项可以不管

新建完成后可以点击确定。

spring boot项目的根目录上添加一个文件名为`Jenkinsfile`的文件，然后在文件中写入

```sh
pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
}
```

然后把这个项目与GitHub上的项目相关联，push上去之后，可以在GitHub上看到信息

![1558259371330](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259371330.png)

下面的叫test的check只是为了测试设置的，对上面的pipeline没有影响，点击Details可以看到jenkins发回的控制台输出

![1558259444416](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259444416.png)

### 添加测试

将Jenkinsfile修改为

```sh
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

         stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
         }
    }
}
```

然后再次push进行测试，此时项目中已经有单元测试，但是这个测试实际上什么都没有做，所以会通过

![1558259704632](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259704632.png)

在Details可以看到控制台输出

如果自己故意写一个没有通过的单元测试

![1558259850054](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259850054.png)

然后push进行测试

在GitHub上看一下结果

![1558259893424](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259893424.png)

显示测试没有通过

![1558259943163](.\img\%5CUsers%5CChuyu%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1558259943163.png)

## References

* https://jenkins.io/download/
* https://jenkins.io/doc/tutorials/build-a-java-app-with-maven/
* https://jenkins.io/doc/book/pipeline/syntax/

更详细的过程记录见

* <https://github.com/chuyuxuan123/SE_420/tree/dev/teamwork>