#offset文件中的无用文件没有被删除

Owner: apache

Repo: skywalking

Labels: bug 

## ascrutae (24 Dec 2015)

offset文件中的无用文件没有被删除，需要在启动服务的时候，与目录夹下的文件进行校验。


## ascrutae (24 Dec 2015)

已修复，在服务启动的时，将读取缓存文件夹下的所有缓存文件，只要offset文件中的项和缓存文件列表不匹配，则不添加到entries中。


