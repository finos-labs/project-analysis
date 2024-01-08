#客户端发送是否需要通过gzip/zlib等降低发送压力

Owner: apache

Repo: skywalking

Labels: question 

## wu-sheng (21 Dec 2015)

需要测试压缩算法的效率，以及压缩比是否能满足要求，且不足与造成客户端CPU过大的压力。


## wu-sheng (21 Dec 2015)

可参考以下的API资料：http://snowolf.iteye.com/blog/465433


## wu-sheng (18 Aug 2016)

已使用protobuf来降低网络传输成本。暂不考虑使用压缩程序。
压缩程序在高流量时，会造成大约3%-5%额外CPU开销。


