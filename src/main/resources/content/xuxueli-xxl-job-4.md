#项目启动时

Owner: xuxueli

Repo: xxl-job

Labels: 

## simplehpt (26 May 2016)

版本1.3.1
Caused by: java.lang.IllegalArgumentException: Result Maps collection already contains value for XxlJobInfoMapper.XxlJobInfo    at org.apache.ibatis.session.Configuration$StrictMap.put(Configuration.java:782)    at org.apache.ibatis.session.Configuration$StrictMap.put(Configuration.java:754)    at org.apache.ibatis.session.Configuration.addResultMap(Configuration.java:536)     at org.apache.ibatis.builder.MapperBuilderAssistant.addResultMap(MapperBuilderAssistant.java:207)   at org.apache.ibatis.builder.ResultMapResolver.resolve(ResultMapResolver.java:47)   at org.apache.ibatis.builder.xml.XMLMapperBuilder.resultMapElement(XMLMapperBuilder.java:284)   at org.apache.ibatis.builder.xml.XMLMapperBuilder.resultMapElement(XMLMapperBuilder.java:251)   at org.apache.ibatis.builder.xml.XMLMapperBuilder.resultMapElements(XMLMapperBuilder.java:243)  at org.apache.ibatis.builder.xml.XMLMapperBuilder.configurationElement(XMLMapperBuilder.java:116)   ... 83 more


## xuxueli (27 May 2016)

报错目测因为Mybatis并没有扫描到Mapper配置文件，请确定本地代码为最新源码，按照用户手册环境要求记性部署启动。
如果存在问题，请前往技术交流群(仅作技术交流)：367260654  


