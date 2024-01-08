#ensureCapacity方法存在的意义在哪？

Owner: Snailclimb

Repo: JavaGuide

Labels: question 

## changchangjie (10 Oct 2018)

对于ArrayList添加大量元素，最好调用ensureCapacity方法进行提前扩容，为啥不直接用有参构造方法呢，这个方法存在的意义在哪呢？

## Snailclimb (10 Oct 2018)

ensureCapacity（）方法的源码如下：

```java
/**
      *如有必要，增加此ArrayList实例的容量，以确保它至少可以容纳由minimum capacity参数指定的元素数。
     *
      * @param minCapacity所需的最小容量（也就是minimum capacity）
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // any size if not default element table
            ? 0
            // larger than default for default empty table. It's already
            // supposed to be at default size.
            : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }
```

