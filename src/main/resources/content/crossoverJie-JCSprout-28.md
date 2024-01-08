#LinkedList 是双向链表吧，第一个节点的 prev 指向 null，最后一个节点的 next 指向 null

Owner: crossoverJie

Repo: JCSprout

Labels: 

## 1332508387 (04 May 2018)



## crossoverJie (04 May 2018)

@1332508387 多谢提醒。
刚才仔细看了下源码，在 1.7、1.8 中确实不是循环链表，最后一个指向的 `null`

![](https://ws1.sinaimg.cn/large/006tKfTcly1fqzaf6egnzj30x60f4q4q.jpg)

等会调整下。

