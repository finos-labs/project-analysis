#smart git pull项目报错

Owner: crossoverJie

Repo: JCSprout

Labels: bug 

## jianghuaonline (15 Jun 2018)

**在提交issue之前请回答以下问题，谢谢!**

> 建议首先查看是否已经有类似的 Issues (提交时可删除该提示)

### 你使用的是哪个版本

### 预期结果

### 实际结果
Clone: Invalid path: MD/TCP:IP.md

### 重现结果的步骤
smart git clone项目
### 其他相关信息
smart git
version 18.1.3 #12142, installed: #12104
jdk 1.8.0_144-b01 (D:\smartgit\jre)

## crossoverJie (15 Jun 2018)

@jianghuaonline 

没用过这个工具，应该是我那个文件命名的问题。

可以直接在本地：

```shell
git clone https://github.com/crossoverJie/Java-Interview.git
```

这样肯定可以拉下来。

## aboboo520 (26 Jun 2018)

在Eclipse中clone时也报错：org.eclipse.jgit.dircache.InvalidPathException: Invalid path: MD/TCP:IP.md
TCP:IP.md命名有问题，建议重命名

## aboboo520 (26 Jun 2018)

![default](https://user-images.githubusercontent.com/19463671/41893508-865c76ea-794e-11e8-93bb-654369c2a587.png)

git clone https://github.com/crossoverJie/Java-Interview.git拉取下来之后，TCP:IP.md内容也是为空


## crossoverJie (26 Jun 2018)

@aboboo520 好的，这个可能和操作系统也有关系，我是 Mac 没有出现这个问题。

空了我换个名字。

## crossoverJie (26 Jun 2018)

@aboboo520 已经修改，再试试。

