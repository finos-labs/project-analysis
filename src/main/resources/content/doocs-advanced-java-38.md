#关于rabbitMQ的顺序

Owner: doocs

Repo: advanced-java

Labels: 

## kyan54 (09 Feb 2019)

感觉大神的分享，之前也遇到了相关的问题，但是感觉没有你这里说的这么复杂。这次看到这里的说法就自己写了个demo。

        advanced-java/docs/high-concurrency/how-to-ensure-the-order-of-messages.md
      
只是纯粹的使用了springboot 然后开启了一个helloObj的队列，之后循环发了10个消息
开了1个hello的监控，但是不知道为什么。。。不知道能否帮忙解答一下这块。。，代码大致如下

代码大概意思是循环10次，发送0-9的ID过来，按理说消费的时候顺序也是0-9的，但是为什么我的无序的情况

配置部分，这里只看helloObj
![image](https://user-images.githubusercontent.com/2555542/52522446-8e0bed00-2cc0-11e9-8cc7-89bfb5e4dce3.png)

模拟生产消息
![image](https://user-images.githubusercontent.com/2555542/52522460-aa0f8e80-2cc0-11e9-8007-ea2b85d47f66.png)

![image](https://user-images.githubusercontent.com/2555542/52522464-b8f64100-2cc0-11e9-9683-58e188d8b973.png)

消息消费部分
![image](https://user-images.githubusercontent.com/2555542/52522469-ca3f4d80-2cc0-11e9-83f4-b6b79d42be15.png)

打印的结果，红色部分是消息id
![image](https://user-images.githubusercontent.com/2555542/52522502-2d30e480-2cc1-11e9-96c7-f0cff68c1eb8.png)

网上一直没查到是啥原因。。但是看您的文章里说的只要是1个队列1个消费者就可以了。。我感觉我这里也是这样的。。就不知道为什么会出现顺序不一直的情况。。


## yanglbme (21 Feb 2019)

@kyan54 hello，同学，前段时间一直忙于其它事情，不知道你这个问题解决了没有。

虽然是同一个队列，发送的时候也是按顺序，但是其实可能会存在网络延迟问题，如果发送 6 的耗时比 7 的耗时大，那么 7 就会被先消费。消费端还是要做一些处理的。

