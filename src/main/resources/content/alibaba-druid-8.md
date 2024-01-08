#访问 http://localhost:8080/test/druid/会无显示

Owner: alibaba

Repo: druid

Labels: 

## wendal (03 Aug 2012)

com.alibaba.druid.support.http.StatViewServlet的77行:

```
if (path.length() == 0) {
```

改为

```
if (path.length() == 0 || "/".equals(path)) {
```

github突发故障,无法fork这个项目,本来想提交pull req的,哎...


## sandzhang (03 Aug 2012)

3q，马上改


## sandzhang (03 Aug 2012)

already commit 


