#控制台簇点链路一条记录显示重复

Owner: alibaba

Repo: Sentinel

Labels: area/dashboard 

## zhaoxp1 (02 Aug 2018)


![default](https://user-images.githubusercontent.com/23419810/43579302-2befebb2-9684-11e8-8cc0-398addf4ac4f.PNG)

这里1条链路不存在子链路，但是显示重复

## sczyh30 (08 Aug 2018)

这里不是重复，顶层的是 Context（EntranceNode），下面是对应 Context 下的 Node

