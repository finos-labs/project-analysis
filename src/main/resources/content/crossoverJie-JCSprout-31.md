#如何插入订单详情呢

Owner: crossoverJie

Repo: JCSprout

Labels: question 

## MisterDeng (11 May 2018)

肯定是遍历购物车产品插入订单详情了，arraylist遍历只能加锁实现了吗？我想用消息队列插入，但是算总价还是需要遍历

## crossoverJie (11 May 2018)

@MisterDeng 你问的是什么问题呢？秒杀那篇的嘛？

## MisterDeng (11 May 2018)

不是秒杀系统，普通的购物车购买，但是高并发下会有很多问题，所以请教下你

## crossoverJie (11 May 2018)

@MisterDeng 好的 明白了。

但是我不是很清楚你的问题，是指并发写入购物车的问题嘛？仔细再说下呢？

## MisterDeng (11 May 2018)

流程是：遍历购物车（计算总价，插入订单商品详情表）--写入订单表--遍历购物车（扣库存）
整个流程是在事务中进行的。

## crossoverJie (11 May 2018)

我理解的购买流程是：
- 购物车可以随便加，反正也不会涉及到共享数据。
- 在真正提交订单的时候再用乐观锁的方式去下订单。

## crossoverJie (18 May 2018)

@MisterDeng 如果没有其他问题我就 close 了，需要再打开。

