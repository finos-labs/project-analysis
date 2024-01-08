#master-slave sync model performance improve

Owner: apache

Repo: rocketmq

Labels: 

## iamzhoug37 (22 Mar 2018)

**FEATURE REQUEST**

1. the preformance of SYNC_MASTER model can be improved by decoupling the wait of slave broker fetch the message and processor threads

2. In some highly message reliability situation like orders or finance system, only SYNC_MASTER model is allowed. After I made a performance test for rocketmq,I saw the monitor data showed that the maximum number of messages per minute is 300000, and once I added the producer client number, the avg duration will increase doubled
3. After I read the broker's source code of processing produce request, I think this process can be improved by decoupling the wait of slave broker fetch the message and processor threads. My understanding is that:
	1. producer client send a produce request to the broker
   2. broker allocate a processor thread process this request, after SendMessageProcessor、DefaultMessageStore、CommitLog's process, the message wrote to the local disk 
   3. after writing to local disk, the process thread handleHA: if the broker configure SYNC_MASTER model, the request will be packaged to a GroupCommitRequest, put the GroupCommitRequest into the queue of GroupTransferService,  then the process thread begin waiting(max 5s)
   4. GroupTransferService is a Independent thread, this thread will constantly  check if any request can be responded(timeout or the message's offset less than or equals to the offset of slave broker).Once a request can be responsed,GroupTransferService thread will notify the wait process thread to response client
   5. client receive the response form broker

   
  if the 3.4 step slave broker fetch message slower slightly due to the delay of network or the delay of slave broker's disk write, the master broker's process thread will cost longer time to wait, then the master broker's throughput will reduce.

4. problem optimize method:
     1. after 3.2 step, the process should return instand of waiting for the slave broker fetch messgae
     2. the work of waiting for slave broker can be given to the GroupTransferService thread. GroupTransferService data structure change like this:
old data structure:
`private final WaitNotifyObject notifyTransferObject = new WaitNotifyObject();`
`private volatile List<CommitLog.GroupCommitRequest> requestsWrite = new ArrayList<>();`
`private volatile List<CommitLog.GroupCommitRequest> requestsRead = new ArrayList<>();`
new data structure:
`private ConcurrentSkipListMap<Long , CommitLog.GroupCommitRequest> groupCommitRequestConcurrentSkipListMap = new ConcurrentSkipListMap<>()`
The work process also has change like this:
GroupTransferService iterate the skip list constantly to check if any request can be responsed(timeout or slave has fetched the message). If any request can be responsed, the GroupTransferService thread will response the request.
      3. in 3.3 step, before the request put to the skip list, check the push2SlaveMaxOffset is greated than the need offset of request. If greated, the request will be response immediately.
5. simple optimize result:
before optimize:
300000 messages pre minute, avg cost 10 ms
![image](https://user-images.githubusercontent.com/21154201/37750593-de90ad12-2dc8-11e8-96ca-356ab0da23b0.png)
after optimize:
5000000 message pre minute, avg cost 1.5ms
![image](https://user-images.githubusercontent.com/21154201/37750596-e1fc1df6-2dc8-11e8-889a-5a98887a52a0.png)


## lindzh (28 Mar 2018)

Glad to see such improvement for RocketMQ. Could you please create a pull request to contribute your code? 

## iamzhoug37 (28 Mar 2018)

I'm willing to provide code for this issue. I'm wrting standard code that conforms to the specification. This is my first time to participate in open source project,so I need some more time...
Thank you for your comment :)

## gaoyf (28 Mar 2018)

我理解的 `SYNC_MASTER` 是同步双写，即，**消息同步写给slave才算成功，消息发送方需要根据返回状态决定消息是否重发**。
而你 @iamzhoug37 所说的改进，只是主发送线程不再等待同步消息给slave的状态，这样消息发送方就不知道是否同步成功了。
如果你不在乎是否同步给slave，只在乎是否写入本地硬盘，应该用`flushDiskType=SYNC_FLUSH brokerRole=ASYNC_MASTER`模式。
这个存储层高可用我已经进行过分析了，参考我的分析：https://blog.csdn.net/a417930422/article/details/52585204
另外咨询你一下，你的日志解析的图使用什么工具？多谢

## iamzhoug37 (28 Mar 2018)

并不是这样的，只是处理线程返回了(处理线程返回了，就可以处理下一个写入，提高吞吐)，请求的响应是由GroupTransferService线程进行的，这段时间我会把pr完善下，发出来
我这个是日志是我们公司的监控组件，叫cat(https://github.com/dianping/cat)

## zhouxinyu (31 Mar 2018)

Hi,

Actually, we have noticed this issue and are working on it, but we are very glad to see your contribution to this issue.

## iamzhoug37 (02 Apr 2018)

I will create a pull request on this weekend
Thank you :)  @zhouxinyu 


## vongosling (27 Apr 2018)

How are things going?

## iamzhoug37 (27 Apr 2018)

I'm writing test case. My company was a little busy during this time, have no time to do this. This weekend I will complete it.

## liyangbing (16 May 2018)

i understand the question, i have two question
first question

handleDiskFlush(result, putMessageResult, msg);
handleHA(result, putMessageResult, msg);

if i change the order of  two statement,  master slave flush disk parallelly，
will we  improve performance further?
handleHA(result, putMessageResult, msg);
handleDiskFlush(result, putMessageResult, msg);

second question
with the improve of performance, we will have more  processing threads
the last statement  of  "lock synchronize"   will spend more time

we use 3.2.6 version have 300+ applications,  
the following warning  exceed  20+ everyday
2018-05-16 13:36:23 WARN SendMessageThread_101 - putMessage in lock eclipse time(ms) 1382	
2018-05-16 12:22:05 WARN SendMessageThread_2 - putMessage in lock eclipse time(ms) 1111	
2018-05-16 12:16:34 WARN SendMessageThread_138 - putMessage in lock eclipse time(ms) 1060	
2018-05-16 11:24:16 WARN SendMessageThread_60 - putMessage in lock eclipse time(ms) 1154	
2018-05-16 10:02:34 WARN SendMessageThread_62 - putMessage in lock eclipse time(ms) 1310	
2018-05-16 09:21:15 WARN SendMessageThread_12 - putMessage in lock eclipse time(ms) 1573	
2018-05-16 09:11:35 WARN SendMessageThread_94 - putMessage in lock eclipse time(ms) 1046	
2018-05-16 04:38:34 WARN SendMessageThread_70 - putMessage in lock eclipse time(ms) 1327	
2018-05-16 04:08:01 WARN SendMessageThread_67 - putMessage in lock eclipse time(ms) 1084	
2018-05-16 03:57:03 WARN SendMessageThread_134 - putMessage in lock eclipse time(ms) 1074	
2018-05-16 03:11:35 WARN SendMessageThread_139 - putMessage in lock eclipse time(ms) 1882

the following warning  exceed 500+ everyday
2018-05-16 14:51:19 WARN SendMessageThread_73 - putMessage not in lock eclipse time(ms) 1015	
2018-05-16 14:51:19 WARN SendMessageThread_2 - putMessage not in lock eclipse time(ms) 1015	
2018-05-16 14:51:19 WARN SendMessageThread_132 - putMessage not in lock eclipse time(ms) 1015	
2018-05-16 14:51:19 WARN SendMessageThread_41 - putMessage not in lock eclipse time(ms) 1190	
2018-05-16 14:51:19 WARN SendMessageThread_131 - putMessage not in lock eclipse time(ms) 1011	
2018-05-16 14:51:19 WARN SendMessageThread_11 - putMessage not in lock eclipse time(ms) 1015	
2018-05-16 14:51:19 WARN SendMessageThread_124 - putMessage not in lock eclipse time(ms) 1011	
2018-05-16 14:51:19 WARN SendMessageThread_8 - putMessage not in lock eclipse time(ms) 1015	
2018-05-16 14:51:19 WARN SendMessageThread_113 - putMessage not in lock eclipse time(ms) 1015	
2018-05-16 14:51:19 WARN SendMessageThread_28 - putMessage not in lock eclipse time(ms) 1016	
2018-05-16 14:51:19 WARN SendMessageThread_63 - putMessage not in lock eclipse time(ms) 1215	
2018-05-16 14:51:19 WARN SendMessageThread_99 - putMessage not in lock eclipse time(ms) 1218	
2018-05-16 14:51:19 WARN SendMessageThread_74 - putMessage not in lock eclipse time(ms) 1219	
2018-05-16 14:51:19 WARN SendMessageThread_89 - putMessage not in lock eclipse time(ms) 1220	
2018-05-16 14:51:19 WARN SendMessageThread_32 - putMessage not in lock eclipse time(ms) 1221







## iamzhoug37 (13 Jun 2018)

first question: the flush disk and slave fetch message  are performed concurrently.
second question: I guess you are using sync_flush,the flush of dis or other in lock operation cost too much time,then causes the other thread wait on lock cost too much.The lock has cost,but this cost is acceptable compared to disk flush.

## oawang (10 Jul 2018)

How are things going?

