#afterException in Apsect.invoke

Owner: dromara

Repo: hutool

Labels: bug 

## 970655147 (12 Apr 2017)

code sinppets in "Aspcet. invoke"

			try {
				result = ClassUtil.invoke(target, method, args);
			}catch (InvocationTargetException e) {
				afterException(args, method, args, e.getTargetException());
			}catch (Exception e) {
				throw e;//其它异常属于代理的异常，直接抛出
			}

afterException 的第一个参数, 根据上下文, 应该是target嘛, 这里写错了
关于这个afterExecption的使用, 为什么要这样约定呀


## looly (13 Apr 2017)

确认bug，3.0.4中会修复。十分感谢。
afterException也是切面的一个环境，InvocationTargetException是与业务逻辑相关的异常，是使用者导致的异常，某些情况下这种异常需要被捕获做特殊处理。

## 970655147 (15 Apr 2017)

哦, 这里是我对于InvocationTargetException的理解问题, 不好意思 ..

