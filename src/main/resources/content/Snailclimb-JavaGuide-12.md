#关于值传递与引用传递

Owner: Snailclimb

Repo: JavaGuide

Labels: enhancement 

## Hogantry (28 Aug 2018)

java中没有引用传递，只有值传递。作者最好改一下，以免误导新人。具体关于什么是引用传递，可以去复习下C语言的指针，对比着看。PS:java的引用不可简单的与C的指针划等号

## Snailclimb (28 Aug 2018)

  @Hogantry 我是这样理解的：说 Java 中只有值传递这个东西是因为引用传递的时候传递的也是值只不过是地址值！但是这不代表Java中并没有引用传递啊！
我个人觉得“Java中只有值传递，没有引用传递” 这个说法有点牵强。
**不知各位有没有更好的看法呢？**


## Sod-Momas (28 Aug 2018)

我觉得可以从Java的别名现象说起了,
一开始不要引入值传递和引用传递两个概念,
而是把现象说清楚,
在最后结论说明这个现象
分别称为Java中的值传递与引用传递.


## HiAscend (03 Sept 2018)

我找到了[百度百科](https://baike.baidu.com/item/%E5%BC%95%E7%94%A8%E4%BC%A0%E9%80%92/2880658)的解释，维基百科中暂无。
下面是我的理解：
（1）基本数据类型传值，对形参的修改不会影响实参；
（2）引用类型传引用的值，形参和实参指向同一个内存地址（同一个对象），所以对参数的修改会影响到实际的对象；
（3）String, Integer, Double等immutable的类型特殊处理，可以理解为传值，最后的操作不会修改实参对象。
结论：Java中都是值传递，没有引用传递

## debugjoker (07 Sept 2018)

关于值传递还是引用传递，在Java核心技术卷Ⅰ基础知识第十版第四章4.5小节有详细的说明。我这里放个截图不懂的可以看下![截图](https://i.loli.net/2018/09/07/5b920d8d12469.png)

## zycR10 (13 Sept 2018)

其实java传递的既不是引用，也不是对象，而传递的是引用的copy，所以比如一个方法传入了一个object，然后在方法里令object = new Object（），其实原本的对象并不会变，因为只是把copy出来的引用赋给了新对象，原来的引用其实并没有变。所以说成值传递和拷贝引用传递很多人可能会更容易理解一些，当然如果是新人，可能更加糊涂了，仁者见仁智者见智吧，但是我个人认为简单的分成值传递和引用传递应该算是一个错误答案，欢迎各位来讨论。

## Snailclimb (27 Sept 2018)


首先回顾一下在程序设计语言中有关将参数传递给方法（或函数）的一些专业术语。**按值调用(call by value)表示方法接收的是调用者提供的值，而按引用调用（call by reference)表示方法接收的是调用者提供的变量地址。一个方法可以修改传递引用所对应的变量值，而不能修改传递值调用所对应的变量值。**  它用来描述各种程序设计语言（不只是Java)中方法参数传递方式。

**Java程序设计语言总是采用按值调用。也就是说，方法得到的是所有参数值的一个拷贝，也就是说，方法不能修改传递给它的任何参数变量的内容。**

**下面通过 3 个例子来给大家说明**

### example 1 


```java
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;

    swap(num1, num2);

    System.out.println("num1 = " + num1);
    System.out.println("num2 = " + num2);
}

public static void swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;

    System.out.println("a = " + a);
    System.out.println("b = " + b);
}
```

**结果：**

```
a = 20
b = 10
num1 = 10
num2 = 20
```

**解析：**

![example 1 ](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-9-27/22191348.jpg)

在swap方法中，a、b的值进行交换，并不会影响到 num1、num2。因为，a、b中的值，只是从 num1、num2 的复制过来的。也就是说，a、b相当于num1、num2 的副本，副本的内容无论怎么修改，都不会影响到原件本身。

**通过上面例子，我们已经知道了一个方法不能修改一个基本数据类型的参数，而对象引用作为参数就不一样，请看 example2.**


### example 2

```java
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		System.out.println(arr[0]);
		change(arr);
		System.out.println(arr[0]);
	}

	public static void change(int[] array) {
		// 将数组的第一个元素变为0
		array[0] = 0;
	}
```

**结果：**

```
1
0
```

**解析：**

![example 2](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-9-27/3825204.jpg)

array 被初始化 arr 的拷贝也就是一个对象的引用，也就是说 array 和 arr 指向的时同一个数组对象。 因此，外部对引用对象的改变会反映到所对应的对象上。


**通过 example2 我们已经看到，实现一个改变对象参数状态的方法并不是一件难事。理由很简单，方法得到的是对象引用的拷贝，对象引用及其他的拷贝同时引用同一个对象。**

**很多程序设计语言（特别是，C++和Pascal)提供了两种参数传递的方式：值调用和引用调用。有些程序员（甚至本书的作者）认为Java程序设计语言对对象采用的是引用调用，实际上，这种理解是不对的。由于这种误解具有一定的普遍性，所以下面给出一个反例来详细地阐述一下这个问题。**


### example 3

```java
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student s1 = new Student("小张");
		Student s2 = new Student("小李");
		Test.swap(s1, s2);
		System.out.println("s1:" + s1.getName());
		System.out.println("s2:" + s2.getName());
	}

	public static void swap(Student x, Student y) {
		Student temp = x;
		x = y;
		y = temp;
		System.out.println("x:" + x.getName());
		System.out.println("y:" + y.getName());
	}
}
```

**结果：**

```
x:小李
y:小张
s1:小张
s2:小李
```

**解析：**

交换之前：

![](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-9-27/88729818.jpg)

交换之后：

![](http://my-blog-to-use.oss-cn-beijing.aliyuncs.com/18-9-27/34384414.jpg)


通过上面两张图可以很清晰的看出： **方法并没有改变存储在变量 s1 和 s2 中的对象引用。swap方法的参数x和y被初始化为两个对象引用的拷贝，这个方法交换的是这两个拷贝**

### 总结

Java程序设计语言对对象采用的不是引用调用，实际上，对象引用是按
值传递的。

下面再总结一下Java中方法参数的使用情况：

- 一个方法不能修改一个基本数据类型的参数（即数值型或布尔型》
- 一个方法可以改变一个对象参数的状态。
- 一个方法不能让对象参数引用一个新的对象。


### 参考：

《Java核心技术卷Ⅰ》基础知识第十版第四章4.5小节

