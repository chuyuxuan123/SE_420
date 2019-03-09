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
测试使用Junit5框架，直接使用intellij中的插件进行测试
总共写了两个测试类，分别对上面类中对应的函数进行测试

```java
class DictionaryTest {

    Dictionary d1 = new Dictionary("dictionary/smalldict1.txt");

    @org.junit.jupiter.api.Test
    @DisplayName("test exists()")
    void exists() {

        assertEquals(d1.exists("code"),true);
        assertEquals(d1.exists("beg"),true);
        assertEquals(d1.exists("ics"),false);
    }
}
```

```java
class WordladderTest {

    Wordladder wl = new Wordladder(new Dictionary("dictionary/smalldict1.txt"));

    @Test
    @DisplayName("test check()")
    void check() {
        assertEquals(wl.check("code","data"),true);
        assertEquals(wl.check("code","code"),false);
        assertEquals(wl.check("happy","code"),false);
        assertEquals(wl.check("ics","beg"),false);

    }

    @Test
    void solve() {
    }
}
```
我发现的一个问题是，我用来进行BFS的方法的返回值是void类型，这导致我无法通过assertEqual来进行测试，而且也没有接口可以将中间值取出来，所以这个solve()方法没有写测试。

main方法同理也是。