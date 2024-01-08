#mysql特定的ddl操作(删除字段，rename table等)，导致解析挂起

Owner: alibaba

Repo: canal

Labels: question wontfix 

## agapple (19 Feb 2013)

目前已知会导致挂起的ddl操作：
1. 字段删除  
2. rename table  (导致desc table时找不到对应的meta信息，无法补全Entry信息)

会导致信息错乱的ddl操作：
1. 首先删除一个字段，然后再新加一个字段(非添加到末尾)

原因：
- mysql binlog中的tablemap LogEvent，没有包含对应的column name信息，只是按照当前table中column的顺序，列出了字段类型
- 回退到ddl操作前的binlog重新开始解析，此时数据库中desc table所获取的信息就会和table map中不一致(无法进行match操作)，会导致信息混乱


## agapple (19 Feb 2013)

避免的操作：
1.  业务操作避免带删除性质的ddl，比如字段删除，rename table , drop table
2.  业务操作避免添加字段时调整顺序，只能添加到末尾. 

如果真不可避免出现出现上述操作中的情况，只要不回退canal解析位置，就可以正常往下解析，不会出现数据错乱. 
不过需要考虑：当canal解析延迟比较大，此时进行带删除性质的ddl操作，待解析的binlog中还存在对应ddl的表数据，那还是会出现问题。（尽可能选择合适的时间进行ddl操作）


## agapple (18 Mar 2013)

需要使用上避免，不是bug


## kongshanxuelin (19 Mar 2013)

最好能加入此种操作方式下，让canal能再次重新正常解析的完整步骤！


## agapple (20 Mar 2013)

如果遇到ddl带删除性质的操作，正常的话只要保证这时client和server不要有重启，那到问题不大，如果真遇上了，那只能重置位点。 

重置位点方法： 
1. 删除zookeeper的cursor节点信息 
2. 修改instance.properties配置到一个新的起始位点 
3. 重启启动server，然后启动client. 

重置位点，可能会导致这一段时间的数据丢失，只能通过人肉的方式去补这个数据了，解析出binlog，去掉一些ddl操作的信息，然后构造sql 

现在我也在考虑一个问题，是否可以考虑引入一个strict模式，如果非strict模式，针对这些ddl问题的数据，直接忽略，记录日志的方式来解决. 出问题的时候手工切换为非strict，尽可能少的跳过数据，避免大量数据丢失


