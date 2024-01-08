#ArrayList 一节中，扩容因子和源码不同

Owner: crossoverJie

Repo: JCSprout

Labels: question 

## rosuH (16 May 2018)

# 问题所在章节 `ArrayList/Vector`

# 问题描述

在该章节的`ArrayList`小节的 *扩容* 部分，为什么不使用`ArrayList`的源码呢？

> 其实扩容最终调用的代码:
```java
private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```

如上的扩容因子为`3/2`，源码里面是为：
```java
int newCapacity = (oldCapacity * 3)/2 + 1;
```

在源码里找了一下，没有找到`grow()`方法，所以应该是你自己的实现吗？
即便扩容因子没有硬性规定...但是是不是遵循源码会好一些？

[java/util/ArrayList.java](http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/ArrayList.java)
- - - - - 
感谢~


## rosuH (16 May 2018)

原来是因为我看的源码是`OpenJDK`的源码，而楼主的是`Sun JDK`的。

## crossoverJie (16 May 2018)

@rosuH 是的，目前没有看过 OpenJDK 的源码，毕竟生产上用的还是官方的。

## rosuH (16 May 2018)

@crossoverJie 我还是 too young.:joy:

