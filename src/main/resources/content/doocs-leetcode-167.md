#第三题不解，关于p指针

Owner: doocs

Repo: leetcode

Labels: 

## cnkeeper (29 Apr 2019)

[原文](https://github.com/doocs/leetcode/tree/master/solution/0003.Longest%20Substring%20Without%20Repeating%20Characters)
你有提到为了防止p指针回溯，不太清除这是为什么。)
```java
if(map.containsKey(chars[q])){
 p=map.get(chars[q])+1;
}
map.put(chars[q],q);
max=Math.max(max,q-p+1);
```
这样有什么问题吗，求解！

## yanglbme (29 Apr 2019)

有问题的，因为前面遍历过的字符都放入 map 中了，你直接使用 map.get(chars[q]) + 1 获得下标，并赋给 p，p 指针就可能往回走了。我那里举了个例子 abba，如果是下面这种情况。
```
a b b a
    ↑ ↑
    p q
```

p 此时指向第二个 b，q 指向最后的 a，此时 map中包含 chars[q]，即包含a，此时你那样赋值的话，p=0+1，即p=1，p指针往回走了。


## yanglbme (29 Apr 2019)

要确保 p,q 之间不包含重复字符，你p指针回溯过后，p，q之间就包含两个字符b了。

## cnkeeper (29 Apr 2019)

> 有问题的，因为前面遍历过的字符都放入 map 中了，你直接使用 map.get(chars[q]) + 1 获得下标，并赋给 p，p 指针就可能往回走了。我那里举了个例子 abba，如果是下面这种情况。
> 
> ```
> a b b a
>     ↑ ↑
>     p q
> ```
> p 此时指向第二个 b，q 指向最后的 a，此时 map中包含 chars[q]，即包含a，此时你那样赋值的话，p=0+1，即p=1，p指针往回走了。

豁然开朗，谢谢回复！

