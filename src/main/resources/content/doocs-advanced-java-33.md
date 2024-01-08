#关于Elasticsearch，6.x版本一个index只能允许有一个type

Owner: doocs

Repo: advanced-java

Labels: 

## jptangchina (23 Jan 2019)

官方说明了在6.x版本以后，一个index对应一个type类型（https://www.elastic.co/guide/en/elasticsearch/reference/6.5/removal-of-types.html），这个是否有必要在文档中更新？目前文档中还是一个索引可以对应多个type

## yanglbme (23 Jan 2019)

@jptangchina 谢谢提醒 :)

原文档中的内容还将保留，而关于新版本移除 mapping types 的问题，我再在文档中做一下简单说明，提醒开发者注意。

非常感谢你的反馈。

