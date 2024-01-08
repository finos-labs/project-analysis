#Two small problems I discovered while reading the source code

Owner: YunaiV

Repo: ruoyi-vue-pro

Labels: bug 

## iootclab (06 May 2021)

Thank you Yunaiv, I just checked the source code and found two small problems:
1. The name of the framework module package is misspelled, e.g." cn.iocoder.yudao.framework.common.util. sping ": sping -> spring ?

2. Can we define the type of "deleted" field to be TINYINT(1)? Based on personal experience, using the BIT type can lead to many development pitfalls.

Now I am busy preparing for something very important to me, so I can only put forward issues first, please forgive me!

## YunaiV (06 May 2021)

英语比较差，我就中文回复啦。

1. 感谢提出拼写错误。我的锅，啊哈哈哈。

2. tinyint 没问题的，实际 mysql 的占用来说，两者都是 1 字节。我当时选择使用 deleted 为 bit 的原因是，想体现出“是否”的语义。

