#RegisterPersistenceThread没有合理的使用offset文件和offset.bak文件

Owner: apache

Repo: skywalking

Labels: bug 

## wu-sheng (23 Nov 2015)

offset.bak文件没有发挥备份作用。在offset或offset.bak写入失败后，没有做相应处理


## mircoteam (24 Nov 2015)

haha


## ascrutae (30 Nov 2015)

已经解决


## wu-sheng (01 Dec 2015)

offset与offset.bak依然在文件写入错误的情况下，可能造成offset文件读写不正确。


## wu-sheng (18 Aug 2016)

2.0版本将不再存在此文件。将移除此内容，直接进行本地入库处理。使用新的分布式模式。


