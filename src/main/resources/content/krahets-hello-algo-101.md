#时间复杂度 线性对数阶python代码貌似有问题

Owner: krahets

Repo: hello-algo

Labels: 

## msk397 (13 Dec 2022)

    ```
    """ 线性对数阶 """
    def linear_log_recur(n):
        if n <= 1: return 1
        count = linear_log_recur(n // 2) + \
                linear_log_recur(n // 2)
        for _ in range(n):
            count += 1
        return count
    ```
* 当n为浮点数时，for循环报错
```
TypeError: 'float' object cannot be interpreted as an integer
```
* 我更改了一版，麻烦看一下可以不,可以的话我提交pr
```
""" 线性对数阶 """
def linear_log_recur(n):
    if n <= 1: return 1
    count = linear_log_recur(n / 2) + \
            linear_log_recur(n / 2)
    i = 0
    while i < n:
        count += 1
        i += 1
    return count
```

## krahets (13 Dec 2022)

哈喽，谢谢指正！这样可能所有带 for 循环的都要改下，`range()` 只允许传入整数，我打算做一下 int() 处理。

