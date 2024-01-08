#MD5加密是这样写吗？

Owner: Blankj

Repo: AndroidUtilCode

Labels: help wanted 

## ssyijiu (03 Aug 2016)

对字符串 ‘“lxm” 进行MD5加密

getMD5 = 8fae1645fdb13cc84924ae63b08584ff, time = 6
md5 = 8fae1645fdb13cc84924ae63b08584ff, time = 0
encryptMD5 = -7051e9ba024ec337b6db519c4f7a7b01, time = 0

上面两个是以前用过的算法加密结果是一样的，在cmd5.com查询原文也是正确的，最后一个是你的算法的结果。


## Blankj (03 Aug 2016)

好，我去看看，改了跟你说哈，可进群交流哈


## Blankj (03 Aug 2016)

问题已发现，我转的是有符号位的16进制，所以造成不同，第一位小于8的话结果是相同的，之后代码会更新的


## Blankj (03 Aug 2016)

加解密已进一步优化完毕，主要昨天匆匆测了一遍，测试量有点小，现在MD5和SHA都可以了


## ssyijiu (04 Aug 2016)

@Blankj 还有点建议，感觉MD5做的太多了，它本身只是一个加密算法，只负责加密就好了，不应该做过多的其他事情（非空判断），这些应该由使用者在进行MD5之前自己判断。还有希望重载一个getMD5(String data, saltValue) MD5加密加盐。仅供参考。


## Blankj (05 Aug 2016)

我一直在纠结要不要判空，你这么一说我就不加了，ok，重载会跟上


## Blankj (05 Aug 2016)

已加


