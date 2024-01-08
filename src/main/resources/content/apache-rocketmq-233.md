#DefaultMessageStore#isTheBatchFull关于maxTransferBytesOnMessageInMemory的疑惑

Owner: apache

Repo: rocketmq

Labels: 

## dingwpmz (07 Mar 2018)

DefaultMessageStore#isTheBatchFull
private boolean isTheBatchFull(int sizePy, int maxMsgNums, int bufferTotal, int messageTotal, boolean isInDisk) { 

if (0 == bufferTotal || 0 == messageTotal) {
return false;
}

if (maxMsgNums <= messageTotal) {
return true;
}

if (isInDisk) {
if ((bufferTotal + sizePy) > this.messageStoreConfig.getMaxTransferBytesOnMessageInDisk()) {
return true;
}

if (messageTotal > this.messageStoreConfig.getMaxTransferCountOnMessageInDisk() - 1) {
return true;
}
} else {
if ((bufferTotal + sizePy) > this.messageStoreConfig.getMaxTransferBytesOnMessageInMemory()) {
return true;
}

if (messageTotal > this.messageStoreConfig.getMaxTransferCountOnMessageInMemory() - 1) {
return true;
}
}

return false;
}
首先对参数进行一个说明：
sizePy ：当前消息的字节长度
maxMsgNums : 本次拉取消息条数
bufferTotal : 已拉取消息字节总长度，不包含当前消息
messageTotal ： 已拉取消息总条数
isInDisk ：当前消息是否存在于磁盘中
具体处理逻辑：
1）如果bufferTotal 和messageTotal 都等于0，显然本次拉取任务才刚开始，本批拉取任务未完成，返回false
2）如果maxMsgNums <= messageTotal,返回true,表示已拉取完毕
3）接下来根据是否在磁盘中，会区分对待
如果该消息存在于磁盘而不是内存中：
如果已拉取消息字节数 + 待拉取消息的长度 》 maxTransferBytesOnMessageInDisk (MessageStoreConfig)，默认64K，则不继续拉取该消息，返回拉取任务结束。
如果已拉取消息条数 > maxTransferCountOnMessageInDisk (MessageStoreConfig)默认为8，也就是，如果消息存在于磁盘中，一次拉取任务最多拉取8条。
如果该消息存在于内存中，对应的参数为maxTransferBytesOnMessageInMemory 、maxTransferCountOnMessageInMemory。
这里为什么要这么做呢？
我有三个不明白的地方：
1、checkInDiskByCommitOffset 这个方法，本身的合理性？因为就是设置一下accessMessageInMemoryMaxRatio，就设置一个值，，整个RocketMQ
并没有根据这值去置换内存中的消息到磁盘，，整个消息的存放都是基于MappedByteBuffer，内存映射，这机制是如何实现的呢？
2、就算消息存在于磁盘中，在这里设置这个传输阔值意义在哪？反正消息总是需要去拉取的，而且，这专门的拉取线程去做的事情，就算担心传输慢，会引发什么问题？
3、内存还要设置阔值，我也不是很明白，直接从内存中取，这么快，为什么要限制呢？不是求之不得吗？

愿大神们指点，谢谢。

## zhouxinyu (07 Mar 2018)

Hi,

The issue tracker is ONLY used for bug report and feature request.
Any question or RocketMQ proposal please use our [mailing lists](http://rocketmq.apache.org/about/contact/).

