#考虑添加本地部署的教程

Owner: krahets

Repo: hello-algo

Labels: enhancement 

## a16su (19 Dec 2022)

方便离线观看或者修改章节后可以看看修改后的效果再提交代码
我通过以下命令成功的启动了本地服务
```bash
pip install mkdocs mkdocs-material
mkdir docs/overrides
mkdocs build
mkdocs serve
```
可以考虑提供Dockerfile或者现成的镜像

## krahets (20 Dec 2022)

感谢提议！我先添加一下部署教程，Docker 先 Mark 住，后续可以搞一个 dockerfile 。

