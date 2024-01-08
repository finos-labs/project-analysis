#canal MemoryStore支持按内存大小定义

Owner: alibaba

Repo: canal

Labels: feature 

## agapple (18 Mar 2013)

canal v1.0.2版本支持的MemoryEventStoreWithBuffer，是通过定义bufferSize来控制内存使用，但遇到大文本字段时，单纯按照记录数来控制就很容易爆内存. 

所以，需要支持基于内存大小管理的EventStore，同时支持按内存大小获取数据，方便client进行内存控制. 

MemoryEventStoreWithBuffer.setBufferMemSize()


