#为什么发送线程池默认只启动一个工作线程

Owner: apache

Repo: rocketmq

Labels: 

## hanmz (26 Mar 2018)

thread numbers for send message thread pool, since spin lock will be used by default since 4.0.x, the default value is 1.

多起一些线程可以提高broker的接收消息的能力。但是，这里默认只启动了一个工作线程，是为了保证生产端消息的有序吗？

## vongosling (26 Mar 2018)

This is not an issue, if you have any question, please ask the question in the user email list.http://rocketmq.apache.org/about/contact/



## Ah39 (13 Sept 2018)

减少线程竞争锁，现在合并为一个文件之后，任何时刻都只能由一个线程持有锁。所以commit log合并为一个文件，并不是一个好主意

