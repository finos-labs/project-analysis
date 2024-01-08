#使用WriteClassName 自省功能能否支持List<Entity>的形式

Owner: alibaba

Repo: fastjson

Labels: 

## yezigl (17 Jan 2013)

现在的版本使用WriteClassName序列化后，list中entity的类型会保留，但list的类型就丢失了
JSON.parseObject的时候就不能正确的返回了


