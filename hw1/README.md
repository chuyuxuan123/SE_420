# Homework 1
用Java重写Wordladder

## 使用
运行main方法，在终端输入两个单词，如果存在ladder则会得到输出

## Java类
总共写了三个类
* Dictionary主要是提供一个接口
* Wordladder类可以实现输入检查与寻找ladder
* Main类就是获取输入

### Dictionary
以一个文件路径作为构造函数参数，将文件中的单词读入到ArrayList中

在字典中查询
```java
public boolean exist(String s)
```

### Wordladder
检查输入的单词是否满足要求
```java
public boolean check(String w1, String w2)
```

用BFS查找ladder
```java
public void solve(String w1, String w2)
```

### Main
只有一个main方法

## 测试

