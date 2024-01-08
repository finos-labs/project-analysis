#有几处觉得不太妥当的地方~

Owner: Snailclimb

Repo: JavaGuide

Labels: good first issue 

## changfubai (28 Aug 2018)

感谢提供如此详尽的材料~    

在 [hasmmap源码分析](https://github.com/Snailclimb/Java-Guide/blob/master/Java%E7%9B%B8%E5%85%B3/HashMap.md)这一节中，我觉得有几处不妥，请大佬们指教   


内部结构分析-->JDK1.8之前--> JDK1.8之前HashMap底层是数组和链表结合在一起使用也就是链表散列。HashMap通过key的hashCode来计算hash值，当hashCode相同时，通过“拉链法”解决冲突。   

> 这里我觉得表述不是很妥当，"HashMap通过key的hashCode来计算hash值，当hashCode相同时，通过“拉链法”解决冲突" hashmap 通过key的hashCode经过扰动函数处理过后得到hash值，当hash值相同时，通过拉链法解决冲突，不是hashCode相同时。因为不同的hashCode也可能扰动处理成相同的hash值。

下面一段：简单来说，JDK1.8之前HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的，如果定位到的数组位置不含链表（当前entry的next指向null）,那么对于查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度依然为O(1)，因为最新的Entry会插入链表头部，急需要简单改变引用链即可，而对于查找操作来讲，此时就需要遍历链表，然后通过key对象的equals方法逐一比对查找.

> 这里 "如果定位到的数组位置不含链表（当前entry的next指向null）"  括号里“当前entry的next指向null”描述不当，如下源码，for循环进入的条件是e!=null ，即判断数组有没有元素，只要有，就会开始链表的判断，不管链表有多长。    
> 这里 "如果定位到的数组包含链表，对于添加操作，其时间复杂度依然为O(1)" 这个表述不太合理，因为不管插入，删除还是查询，都是先遍历一遍链表，所以复杂度应该是一样的。具体可以看下面的源码~~

    public V put(K key, V value)
        if (table == EMPTY_TABLE) { 
        inflateTable(threshold); 
    }  
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        for (Entry<K,V> e = table[i]; e != null; e = e.next) { // 先遍历
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue; 
            }
        }

        modCount++;
        addEntry(hash, key, value, i);  // 再插入
        return null;
    }


再次感谢，希望不要把我当杠精~。~

## calebman (30 Aug 2018)

真仔细~

## Snailclimb (31 Aug 2018)

@changfubai 非常非常感谢！ [hasmmap源码分析](https://github.com/Snailclimb/Java-Guide/blob/master/Java%E7%9B%B8%E5%85%B3/HashMap.md) 这里我再看看  ，非常感谢你  老哥  嘿嘿

## changfubai (31 Aug 2018)

NIO部分： 六 NIO学习总结以及NIO新特性介绍 这篇文章的链接失效啦~ @Snailclimb 

## Snailclimb (01 Sept 2018)

JDK1.8 之前 HashMap 底层是 **数组和链表** 结合在一起使用也就是 **链表散列**。**HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash  值，当 hash 值相同时，通过拉链法解决冲突。**
但是相同的 hashCode 得到的hash值也必然是相同的！

**所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的 hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。**


**JDK 1.8 HashMap 的 hash 方法源码:**

这里就不贴 JDK 1.7 的 hash方法了，JDK 1.8 的 hash方法 相比于 JDK 1.7 hash 方法更加简化，但是原理不变。

  ```java
      static final int hash(Object key) {
        int h;
        // key.hashCode()：返回散列值也就是hashcode
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
  ```
关于扰动函数以及hash方法可以看看几篇文章：

- [《JDK 源码中 HashMap 的 hash 方法原理是什么？》](https://www.zhihu.com/question/20733617)
- [深入理解 hashcode 和 hash 算法](https://blog.csdn.net/qq_38182963/article/details/78940047)

JDK1.8 put以及putVal方法的源码如下：

```java
 /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    /**
     * Implements Map.put and related methods
     *
     * @param hash hash for key
     * @param key the key
     * @param value the value to put
     * @param onlyIfAbsent if true, don't change existing value
     * @param evict if false, the table is in creation mode.
     * @return previous value, or null if none
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        else {
            Node<K,V> e; K k;
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            else {
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
```

当key出现hash冲突的时候,链表中的第一个元素都是后面最新添加进来的那个,之前的则被next变量引用着。虽然这里是插入的动作,但是由于使用了链表,所以无需像数组的插入那样,进行数组拷贝。

## changfubai (01 Sept 2018)

对，我想说  当 hash 值相同时，通过拉链法解决冲突。 这样表述才是准确的。相同的 hashCode 得到的hash值也必然是相同的，也是正确的。但不同的hashcode也可能得到相同hash值，这才是为什么说之前的表述不准确的原因～ 我知道扰动函数是怎么回事，感谢呢～～

## Snailclimb (01 Sept 2018)

@changfubai 非常非常感谢你！嘿嘿！

## changfubai (01 Sept 2018)

还有两点，希望我没有讲错～  哈哈，谢谢大佬关注这么细小的问题

## Snailclimb (01 Sept 2018)

你上面给的源码是哪里的啊？我在HashMap中怎么没看到  是JDK1.8之前的Put方法吗

## changfubai (01 Sept 2018)

1.7的，这一段是介绍1.8以前的hashmap的嘛～而且关于这个复杂度，1.7 1.8都是一样的

## Snailclimb (02 Sept 2018)

嗯嗯 老哥 感谢你 ！
确实存在描述不当的问题  你看这样描述怎么样：
①如果定位到的数组位置没有元素 就直接插入。
②如果定位到的数组位置有元素就和要插入的key比较，如果key相同就直接覆盖，如果key不相同，就判断p是否是一个树节点，如果是就调用`e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);`将元素添加进入。如果不是就遍历链表插入。


```java
    //这里就是看下在要插入的位置有没有元素，index=hash % (length-1)
    if ((p = tab[i = (n - 1) & hash]) == null)
        // 将元素直接插进去
        tab[i] = newNode(hash, key, value, null);
    else {
        //这时就需要链表或红黑树了
        // e是用来查看是不是待插入的元素已经有了，有就替换
        Node<K,V> e; K k;
        // p是存储在当前位置的元素
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p; //要插入的元素就是p，这说明目的是修改值
        // 判断p是否是一个树节点
        else if (p instanceof TreeNode)
            // 把节点添加到树中（红黑树处理冲突）
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            // 这时候就是链表结构了，要把待插入元素挂在链尾（链表处理冲突）
            for (int binCount = 0; ; ++binCount) {
                //向后循环
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    // 链表比较长，需要树化，
                    // 由于初始即为p.next，所以当插入第9个元素才会树化
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                // 找到了对应元素，就可以停止了
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                // 继续向后
                p = e;
            }
        }
```

## changfubai (02 Sept 2018)

哈哈哈  简直完美了~ 感谢大佬这么忙还关注这些问题，特佩服~ 希望有机会可以参与你更多的项目~
上面你提出的表述很准确了~ 不过这个问题是1.7的hashmap 不是1.8的唷~  
不知道这样可以不 
①如果定位到的数组位置没有元素 就直接插入。
②如果定位到的数组位置有元素，遍历以这个元素为头结点的链表，依次和插入的key比较，如果key相同就直接覆盖，不同就采用头插法插入元素。

## Snailclimb (02 Sept 2018)

嗯嗯 很好！

## Snailclimb (06 Sept 2018)

JDK1.8 之前 HashMap 底层是 数组和链表 结合在一起使用也就是 链表散列。HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash 值，然后通过 (n - 1) & hash 判断当前元素存放的位置也就是该放在数组的位置（这里的 n 指的时数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。

