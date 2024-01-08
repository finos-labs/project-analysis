#mysql 依赖降级

Owner: apolloconfig

Repo: apollo

Labels: 

## secwang (09 Dec 2016)

降级到 mysql 5.5.40 可行么, on update 我们是不是可以绕过去，我可以做这部分的支持工作


## nobodyiam (09 Dec 2016)

目前来看，主要是用到了两个高级特性：

1. utf8mb4 - 这个需要5.5版本以上
2. 一张表有多个timestamp的default值 - 这个需要5.6.5以上

所以，理论上而言，使用5.5.40也是可以的，修改一下建表语句就可以了。
把一张表中多个timestamp的default值去掉一个就可以了，代码里面应该都会设置默认值的。

## secwang (09 Dec 2016)

utf8mb4这个没问题，timestamp 这个问题我，确认下，需要代码patch么

## nobodyiam (09 Dec 2016)

应该是不需要的，可以试一下把所有的`DataChange_CreatedTime`字段上的DEFAULT CURRENT_TIMESTAMP COMMENT改为DEFAULT '2000-01-01 00:00:00'。

从[代码](https://github.com/ctripcorp/apollo/blob/master/apollo-common/src/main/java/com/ctrip/framework/apollo/common/entity/BaseEntity.java)上看，创建时间都会被程序设置的。

    @PrePersist
    protected void prePersist() {
        if (this.dataChangeCreatedTime == null) dataChangeCreatedTime = new Date();
        if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = new Date();
    }

## secwang (09 Dec 2016)

我实验下

## secwang (09 Dec 2016)

可行，附上我的更改脚本。 
```
cd ~/apollo-build-scripts/sql
 perl -pi -e 's/\`DataChange_CreatedTime\` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP/\`DataChange_CreatedTime\` timestamp NOT NULL DEFAULT \'2000-01-01 00:00:00\'/g' *
```

## secwang (18 Dec 2016)

5.5 还存在 fulltext key 不支持 innodb 的问题，需要改为key

## nobodyiam (22 Dec 2016)

@secwang [PR143](https://github.com/ctripcorp/apollo/pull/493)把fulltext key改为key了。

## secwang (23 Dec 2016)

哈哈，感谢

## lyg123 (01 Mar 2017)

@nobodyiam 
DEFAULT CURRENT_TIMESTAMP 改成 DEFAULT '2000-01-01 00:00:00' 吧？
我看你的有COMMENT。

## nobodyiam (01 Mar 2017)

@lyg123 comment无所谓的

## pgssb (08 Apr 2020)

我刚刚升级了

