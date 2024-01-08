#compilation jdk version

Owner: alibaba

Repo: nacos

Labels: 

## irusist (29 Jul 2018)

我看client代码是jdk8编译的，是否能提供jdk7编译的版本

## jasonsuzhou (30 Jul 2018)

I think this project only support 1.8 or higher, I can see there is a JVM parameter in the startup command which is "MetaspaceSize", and this parameter was introduced since JDK1.8.

## xuechaos (31 Jul 2018)

@jasonsuzhou is right, you need JDK1.8 or higher, thank you.

