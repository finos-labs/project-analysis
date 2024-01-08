#打包

Owner: macrozheng

Repo: mall

Labels: 

## beyond-snail (17 Dec 2018)

打包不行啊

## macrozheng (18 Dec 2018)

在pom.xml中注释掉docker的插件就可以了，如下：
```
<plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>1.1.0</version>
            </plugin>
```

