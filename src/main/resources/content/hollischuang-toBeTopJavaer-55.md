#为什么说Java中只有值传递   这个章节出发点就不对吧

Owner: hollischuang

Repo: toBeTopJavaer

Labels: 

## LuoMaster (15 Apr 2020)

如果传递的是个对象    ，在调用的方法中修改了某个字段值   无论是当前方法还是被调用的方法这个字段值都已经被修改了吧

## ChubChen (15 Apr 2020)

Java中其实还是值传递的，只不过对于对象参数，值的内容是对象的引用。

## yukangming (15 Apr 2020)

我觉得是这样的，对象参数里面存储的是对象引用的地址值。依然属于值传递。

## LuoMaster (16 Apr 2020)

@yukangming @ChubChen 两位这样说能理解了  可是作者这个例子也有问题    以下是粘贴的原文 

我们举一个实际的代码例子：

public static void main(String[] args) {
  ParamTest pt = new ParamTest();

  User hollis = new User();
  hollis.setName("Hollis");
  hollis.setGender("Male");
  pt.pass(hollis);
  System.out.println("print in main , user is " + hollis);
}

public void pass(User user) {
  user = new User();
  user.setName("hollischuang");
  System.out.println("print in pass , user is " + user);
}
复制ErrorOK!
上面的代码中，我们在pass方法中，改变了user对象，输出结果如下：

print in pass , user is User{name='hollischuang', gender='Male'}
print in main , user is User{name='Hollis', gender='Male'}

## yukangming (16 Apr 2020)

@LuoMaster 哈哈老哥我没看那个代码，只是点进来看的时候看到你的issue了(▼皿▼#)

## LuoMaster (16 Apr 2020)

> @LuoMaster 哈哈老哥我没看那个代码，只是点进来看的时候看到你的issue了(▼皿▼#)

额 好像是我看错了 他在方法里面又new了一个user   

## LuoMaster (16 Apr 2020)

我错了    方法里new了一个user  改变的是新创建的user对象    应该就如同前面两位老哥说的  证明java中是值传递 只不过是对象的时候 这个值传递的是对象的引用   。方法体中新创建个引用 赋值给当前传递进来的user main方法的user变量也不会指向新创建的对象  

