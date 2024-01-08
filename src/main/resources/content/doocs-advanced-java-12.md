#疑问：关于“ RabbitMQ 弄丢了数据” 的说法

Owner: doocs

Repo: advanced-java

Labels: 

## haiyan858 (29 Dec 2018)

持久化可以跟生产者那边的confirm机制配合起来，只有消息被持久化到磁盘之后，才会通知生产者ack了，所以哪怕是在持久化到磁盘之前，RabbitMQ 挂了，数据丢了，生产者收不到ack，你也是可以自己重发的。

注意，哪怕是你给 RabbitMQ 开启了持久化机制，也有一种可能，就是这个消息写到了 RabbitMQ 中，但是还没来得及持久化到磁盘上，结果不巧，此时 RabbitMQ 挂了，就会导致内存里的一点点数据丢失。

> 既然是在“confirm”机制的前提下，RabbitMQ 开启了持久化机制 ，通知生产者ack了，那么消息是不是就不会丢失了呢


## yanglbme (29 Dec 2018)

@haiyan858 谢谢反馈，这两段换一下顺序，应该就好理解了。

