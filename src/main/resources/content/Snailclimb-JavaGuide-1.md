#欢迎大家提建议啊~~~

Owner: Snailclimb

Repo: JavaGuide

Labels: 

## Snailclimb (06 Jun 2018)

欢迎大家提建议啊~~~
一个好的开源文档的诞生一个人的努力肯定是不够的，所以在这里希望能有人帮忙一起完善。

## YunaiV (23 Jun 2018)

你好。我是芋道源码的博主。请问可以转载你的文章么？

## Guanzhou-Ke (08 Jul 2018)

spring系列可能分拣出来比较好点，可以增加容器的相关知识点，tomcat jetty这些。

## aiengelangte (02 Aug 2018)

厉害了 

## Snailclimb (02 Aug 2018)

后续会考虑增加Tomcat相关知识以及考虑主流框架部分的完善，最近可能没时间。

## lukesama (11 Aug 2018)

能麻烦加一下进程间通讯方式之类的文章嘛

## Snailclimb (11 Aug 2018)

@lukesama 不好意思 目前可能没有时间添加，这段时间忙玩之后会考虑添加操作系统方面的知识。嘿嘿 ，你也可以将这些知识总结出来然后给我PR啊！

## 2251999759 (12 Aug 2018)

好厉害，可以跟着大佬一块学习吗？

## Snailclimb (16 Aug 2018)

欢迎一起学习交流 @2251999759 

## im-Luxqi (23 Aug 2018)

非常厉害

## thangl3 (24 Aug 2018)

I hope it has version English

## jounghu (25 Sept 2018)

HashMap 长度是2的幂次方，文档写的有误

![hashmap](https://user-images.githubusercontent.com/30997324/46019216-2b99c200-c10e-11e8-8c95-8e60d8b23a69.png)

HashMap的长度在初始化的时候定义为：

![hashmap2](https://user-images.githubusercontent.com/30997324/46019314-64d23200-c10e-11e8-9ccc-59f8df188dd8.JPG)

tableSizeFor() 会把将cap容量扰动为2的幂次方

```java
 /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```
希望改进






## yzsunlight (28 Sept 2018)

建议你整体可以 从入门到基础到深入，  建议书单更加完善

## Snailclimb (29 Sept 2018)

@jounghu  你好！HashMap 用你说的那个函数初始化的时候，如果你给的容量不是2的幂次方 的会把 容量默认变为 2的幂次方。但是这不是 HashMap 长度是2的幂次方的原因。

## jounghu (10 Oct 2018)

@Snailclimb 那请问原因是什么？

## MutualExclusion (14 Oct 2018)

如何在这个项目中添加图片，毕竟人对于图片的理解远远要超过对文字理解的速度

## YangYangDai (18 Oct 2018)

java只有值传递   基本数据类型是值传递  引用类型是引用类型的值(内存地址的值)传递

