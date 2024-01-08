#数据新增

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## kl720 (05 Mar 2019)

数据新增时，使用了DictSelectTag设置几个字典字段，无法获取字典选择的值，保存失败

## zhangdaiscott (05 Mar 2019)

描述有点简单，把代码贴出来

## kl720 (05 Mar 2019)

> 描述有点简单，把代码贴出来
我截了个图，看说得清楚不，我现在解决办法是单独存选的字典值，然后保存再传给formData：         formData.quality = this.dictModel.quality
formData.siteLevel = this.dictModel.siteLevel
![1](https://user-images.githubusercontent.com/3956096/53802982-3fc7d200-3f7e-11e9-9c3c-3fac8b144097.png)
![2](https://user-images.githubusercontent.com/3956096/53802988-435b5900-3f7e-11e9-9429-f3c8f2cb82b4.png)


## zhangdaiscott (06 Mar 2019)

收录，我们尽快解决

## zhangdaiscott (09 Apr 2019)

已改，下个版本发布

