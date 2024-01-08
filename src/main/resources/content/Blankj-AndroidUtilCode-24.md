#sp工具类相关

Owner: Blankj

Repo: AndroidUtilCode

Labels: question 

## ssyijiu (11 Aug 2016)

private static SharedPreferences sp;

private static SharedPreferences getInstance(Context context) {
        if(sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp;
}

获取sp推荐这样写的写法，效率高一点。


## Blankj (11 Aug 2016)

你这是单例了，这样sp的name不能修改了


## ssyijiu (11 Aug 2016)

怎么说呢，代码量大到一定程度后，自己写的也不敢确定sp的name在哪里改了，所以现在这样 我要是用的话在get和put之前肯定会先初始化一下sp的name，但这样又很麻烦，不这样心里不放心。 


## ssyijiu (11 Aug 2016)

建议这样，大多数用一个单例的sp，有特殊保存比如token还是自己重新封装好:

```
   // 如果需要一个单独的SharedPreferences来保存某些数据，例如：token，可以这样：
private static final String PRE_TOKEN = "token";
private static SharedPreferences getInstance(Context context, String preferenceName) {
    if(sp == null) {
        sp = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }
    return sp;
}

public static void putToken(Context context, String token) {
    sp = getInstance(context,PRE_TOKEN);
    sp.edit().putString(PRE_TOKEN, token).apply();
}

public static String getToken(Context context) {
    sp = getInstance(context,PRE_TOKEN);
    return sp.getString(PRE_TOKEN,"");
}
```


## Blankj (11 Aug 2016)

保存特殊的只需要改name就好了挺方便的，单例的话就修改不了，还要重新开一个单例，其实单例只是为了复用上次存在的值，而这个单例的区别就是getSharedPreferences这一个步骤，你是直接读取上次的sp，而我是调用getSharedPreferences获得，性能基本没区别啦，考虑到使用方便就不单例了


