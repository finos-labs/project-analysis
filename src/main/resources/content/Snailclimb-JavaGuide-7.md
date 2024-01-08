#有个问题请教

Owner: Snailclimb

Repo: JavaGuide

Labels: question 

## winyiwin (13 Aug 2018)

```java
// 当桶(bucket)上的结点数大于这个值时会转成红黑树
    static final int TREEIFY_THRESHOLD = 8; 
    // 当桶(bucket)上的结点数小于这个值时树转链表
    static final int UNTREEIFY_THRESHOLD = 6; 
```

这两个值大神知道是什么依据吗?

## Snailclimb (15 Aug 2018)

我们知道红黑树（Red Black Tree） 是一种自平衡二叉查找树，红黑树和AVL树类似，都是在进行插入和删除操作时通过特定操作保持二叉查找树的平衡，从而获得较高的查找性能。

为什么是8呢？我暂时只能想到/查阅到两个答案
1. 这个值是HashMap执行查询的时候时间复杂度最低的阈值，也就是当桶(bucket)上的结点数大于8时才会有转成红黑树才有必要；
2.  理想情况下使用随机的哈希码，容器中节点分布在hash桶中的频率遵循泊松分布(具体可以查看http://en.wikipedia.org/wiki/Poisson_distribution)，* 按照泊松分布的计算公式计算出了桶中元素个数和概率的对照表，可以看到链表中元素个数为8时的概率已经非常小，再多的就更少了，所以原作者在选择链表元素个数时选择了8，是根据概率统计而选择的。（[https://blog.csdn.net/Mollychin/article/details/80444967](https://blog.csdn.net/Mollychin/article/details/80444967)
）

## maskleo (16 Aug 2018)

@winyiwin 
参见 `putVal` 方法   
```java
if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
```

当桶(bucket)上的结点数大于这个值时会转成红黑树

`resize() `  中的 `((TreeNode<K,V>)e).split(this, newTab, j, oldCap);`  

```java
if (loHead != null) {
                if (lc <= UNTREEIFY_THRESHOLD)
                    tab[index] = loHead.untreeify(map);
                else {
                    tab[index] = loHead;
                    if (hiHead != null) // (else is already treeified)
                        loHead.treeify(tab);
                }
            }
            if (hiHead != null) {
                if (hc <= UNTREEIFY_THRESHOLD)
                    tab[index + bit] = hiHead.untreeify(map);
                else {
                    tab[index + bit] = hiHead;
                    if (loHead != null)
                        hiHead.treeify(tab);
                }
            }
```
当桶(bucket)上的结点数小于这个值时树转链表

注意阅读源码。


