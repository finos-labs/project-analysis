#手机号码的正则

Owner: Blankj

Repo: AndroidUtilCode

Labels: help wanted 

## MIkeeJY (04 Aug 2016)

 private static Pattern mobilePattern;
    /**
     \* 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
     \* 联通：130、131、132、145、155、156、175、176、185、186
     \* 电信：133、153、173、177、180、181、189
     \* 全球星：1349
     \* 虚拟运营商：170
     *
     \* @param mobileNo
     \* @return
     */

```
/**
 * 验证手机格式
 */
public static boolean isMobileNO(String mobiles) {


    if (mobilePattern == null) {
        mobilePattern = Pattern.compile("^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$");
    }
    return mobilePattern.matcher(mobiles).matches();
}
```


## lanseyou (04 Aug 2016)

全球星，亮了。


## MIkeeJY (04 Aug 2016)

@lanseyou 美国一家很屌的公司


## Blankj (04 Aug 2016)

ok，isDetialMobileNO诞生，简短粗暴那个也做保留，我会做区分


