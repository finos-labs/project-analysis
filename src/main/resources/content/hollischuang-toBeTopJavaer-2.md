#关于“深入理解 Java 内存模型（四）——volatile”相关问题

Owner: hollischuang

Repo: toBeTopJavaer

Labels: 

## zengxiangjiang (11 Jan 2019)

您好：
     我正在读您写的您写的“深入理解 Java 内存模型（四）——volatile”，发现文章里面写道：“ 
•     原子性：对任意单个 volatile 变量的读 / 写具有原子性，但类似于 volatile++ 这种复合操作不具有原子性。
    ”，这个怎么理解？  很疑惑。 我看其他资料volatile修饰的变量只具有可见性，并不具有原子性。
   期待您的回复。

## DaveBoy (27 Jan 2019)

首先，volatile确实不具有原子性，但是对于long和double的读取好像是有原子性的（不用volatile修饰不具有原子性）。
其次“• 原子性：对任意单个 volatile 变量的读 / 写具有原子性，但类似于 volatile++ 这种复合操作不具有原子性。”这句话的正确理解是“任意变量（应该是要排除long和double，但是加了volatile就不用排除了）的读 / 写具有原子性，但i++ 是个复合操作，不具有原子性。”

## zengxiangjiang (29 Jan 2019)

您好，非常感谢您的解惑。

