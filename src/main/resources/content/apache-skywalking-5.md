#DataSender放弃NIO，使用标准Socket

Owner: apache

Repo: skywalking

Labels: wontfix 

## wu-sheng (04 Dec 2015)

NIO不适合当前场景，应当移除


## wu-sheng (06 Dec 2015)

客户端SDK已经使用多种机制，保证单个发送线程使用单一的socket连接，无需使用NIO复用连接。
只有服务端使用NIO，才有实际价值。


## wu-sheng (07 Dec 2015)

DataSender依然会被多线程使用，用于发送。NIO依然能提供更好的性能。所以此改进需求作废


