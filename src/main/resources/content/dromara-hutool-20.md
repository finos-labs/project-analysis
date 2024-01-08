#关于BloomFilter部分的一些意见

Owner: dromara

Repo: hutool

Labels: enhancement 

## 970655147 (13 Apr 2017)

	IntMap, LongMap, 求商, 求余可以使用位操作优化, LongMap中add, remove中似乎是存在问题[强转int, 丢失数据], 缺少对于range的校验
	BitSetBloomFilter 扩展一点可以增加自定义的HashFunction, 以及使用一些预定义的HashFunction, init的时候 提供类似于LineTokenizer接口, 对于一行数据应该做校验, 提供类似于LineFilter
	filter包里面的单个hashFunc的BloomFilter实现似乎是没有太大的意义, hash冲突的可能性太大了
	BitMapBloomFilter.add方法对于结果的处理有问题, 还有一些写法层面上的可以改进的地方

## looly (13 Apr 2017)

感谢你的意见。
1. 位操作后续版本会做优化
2. 强转int是数组下标限制，有更好的建议欢迎pull request
3. 现在就是自定义hash算法，可以继承AbstractFilter实现自定义hash算法，BitSetBloomFilter中确实欠缺自定义。

## 970655147 (15 Apr 2017)

哦, 我上面描述的一些地方可能不清晰吧
关于"2", 我还以为我看错了, 又回去看了一下
"强转int是数组下标限制" : 这里我指的是"longs[r] = (int) (longs[r] | (1 << c));" 这类代码
关于"3", 可能looly 或者是我对于bloomFilter的理解有偏差吧
我理解的bloomFilter是类似于这里的"BitSetBloomFilter"[不过这里面将hashFunc封装死了, 算是一种bloomFilter的实现吧][组合了多个HashFunc, 当然如果用户喜欢的话, 只指定一个也随他], 然后关于这个AbstractFilter, 我个人觉得更应该作为一个HashFunc的"接口", 而不是BloomFilter, 然后下面的ELF, FNV作为HashFunc的实现, 然后 这里的"BitMapBloomFilter", 算是一个组合的BloomFilter

关于上面的BitMapBloomFilter的写法问题 
1. "boolean flag = true; flag |= filter.add(str);"   // always true

## looly (16 Apr 2017)

确实。而且我发现BloomFilter代码还有很多需要优化的地方，最近我会做一次review，也希望你多提意见~~

