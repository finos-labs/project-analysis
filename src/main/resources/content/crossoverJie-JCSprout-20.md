#对锁的一些认知 有哪些锁

Owner: crossoverJie

Repo: JCSprout

Labels: question 

## cellardoor125 (20 Mar 2018)

如果在 setNX 之后释放锁的时候挂掉了那么这个 key 将永远挂起，等到超时之后自动删除，如果在超时时间之内这个操作还没有完成就容易发生并发问题

可以在tryLock的生成一个随机数并setNX。release的时候，比对这个随机数是否一致，一致的情况才去delete，这样在某个进程获取lock后，直到 lock expire，再去delete时不会误删其他进程获取的lock。

## crossoverJie (20 Mar 2018)

这里说的不是某个进程错删其他进程的 key 哈，而是有可能在超时时间之后 key 被自动删除，而任务还没有执行完，导致并发问题。所以想表述的是这个超时时间不好设置。

而你提到的在设置 key 的时候加上随机数，那么怎么来保证锁的排他性呢？一般情况下我们都是根据某一业务场景或者是要锁的某一个方法来设置 key ，比如是更新库存时需要锁，那么 key 可以叫做 `updateXXCount`，其他进程来设置 key 的时候也是 `updateXXCount` 发现设置不了就达到了排他性，而如果加上了随机数 A 进程过来设置时 `1234updateXXCount`，B 进程设置时 `345updateXXCount` 这样不就连锁的作用都没有了嘛？

不知道是不是我理解错你的意思了，多多交流。

## cellardoor125 (20 Mar 2018)

不好意思，我的表达有误。可以这样：

SET my_key my_random_value NX PX 30000

只有这个key不存在的时候才会设置这个key的值（NX选项的作用），超时时间设为30000毫秒（PX选项的作用） 这个key的值设为：my_random_value 一个随机数（尽量保证全局唯一）。

这样就可以达到“互斥”的目的

这个随机值就是用来保证能安全地释放锁，我们可以用下面这个Lua脚本来告诉Redis：删除这个key当且仅当这个key存在而且值是我期望的那个值：

if redis.call("get",KEYS[1]) == ARGV[1] then
        return redis.call("del",KEYS[1])
    else
        return 0
    end

这样可以避免误删其他进程得到的锁。


## crossoverJie (20 Mar 2018)

@cellardoor125 好的 明白你的意思了。

这样可以确保在超时时候之后 key 被自动删除，然后之前的进程执行完毕进行解锁时结果把其他进程加的锁给误删了，这确实是一个解决方案。

但依然会有以下两个问题：

- 并没有解决在超时之后自动解锁但进程 A 没有执行完毕导致的并发问题。
- 如何来保证值的全局唯一性呢。如果真要做到全局唯一势必会增加系统的复杂度，做不到全局唯一的话也有一定几率出现误删。

不过针对于误删这个场景不知你们已经用到生产了呢？如果有 demo 欢迎提 PR。

