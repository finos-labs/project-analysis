#redis-consistence.md 中 Cache-Aside Pattern 相关

Owner: doocs

Repo: advanced-java

Labels: 

## jinyahuan (25 Dec 2018)

Cache-Aside Pattern 更新的时候，不是先更新数据库，然后才淘汰缓存吗？

[参考文章](https://docs.microsoft.com/en-us/previous-versions/msp-n-p/dn589799(v=pandp.10))
参考的片段：
If an application updates information, it can emulate the write-through strategy as follows:
1. Make the modification to the data store
2. Invalidate the corresponding item in the cache.

## yanglbme (25 Dec 2018)

@jinyahuan 感谢反馈，这里确实写错了，稍后我修改一下~

## jiaxingzheng (13 Feb 2019)

> 为什么是删除缓存，而不是更新缓存？
原因很简单，很多时候，在复杂点的缓存场景，缓存不单单是数据库中直接取出来的值。
比如可能更新了某个表的一个字段，然后其对应的缓存，是需要查询另外两个表的数据并进行运算，才能计算出缓存最新的值的。
另外更新缓存的代价有时候是很高的。是不是说，每次修改数据库的时候，都一定要将其对应的缓存更新一份？也许有的场景是这样，但是对于比较复杂的缓存数据计算的场景，就不是这样了。如果你频繁修改一个缓存涉及的多个表，缓存也频繁更新。但是问题在于，这个缓存到底会不会被频繁访问到？
举个栗子，一个缓存涉及的表的字段，在 1 分钟内就修改了 20 次，或者是 100 次，那么缓存更新 20 次、100 次；但是这个缓存在 1 分钟内只被读取了 1 次，有大量的冷数据。实际上，如果你只是删除缓存的话，那么在 1 分钟内，这个缓存不过就重新计算一次而已，开销大幅度降低。用到缓存才去算缓存。
其实删除缓存，而不是更新缓存，就是一个 lazy 计算的思想，不要每次都重新做复杂的计算，不管它会不会用到，而是让它到需要被使用的时候再重新计算。像 mybatis，hibernate，都有懒加载思想。查询一个部门，部门带了一个员工的 list，没有必要说每次查询部门，都里面的 1000 个员工的数据也同时查出来啊。80% 的情况，查这个部门，就只是要访问这个部门的信息就可以了。先查部门，同时要访问里面的员工，那么这个时候只有在你要访问里面的员工的时候，才会去数据库里面查询 1000 个员工。



如果数据频繁更新，写的次数比读的次数还多，那就不适合用缓存吧？

> 一般说来，数据的读写比在2:1以上，即写入一次缓存，在数据更新前至少读取两次，缓存才有意义。
-- 大型网站技术架构


## yanglbme (13 Feb 2019)

@jiaxingzheng 对的，缓存一般用于支持读高并发，适合读多写少的场景。

