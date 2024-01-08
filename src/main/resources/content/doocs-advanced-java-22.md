#关于数据库自增id的问题

Owner: doocs

Repo: advanced-java

Labels: 

## wangkezun (07 Jan 2019)

https://doocs.github.io/advanced-java/#/docs/high-concurrency/database-shard-global-id-generate
uuid不能用作主键的问题不光在于太长，而且还有非单调递增的，这样在b+树中会导致频繁的修改树结构。造成性能下降。

## yanglbme (07 Jan 2019)

@wangkezun 你好，确实是这样。谢谢分享，我之后在文档中做补充。

当然我也鼓励大家，如果觉得哪里可以做些补充的，直接提交 PR。

