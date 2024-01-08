#关于tree类模型的规范的建议

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## dangzhenghui1985 (09 Mar 2019)

TreeModel类 isLeaf 目前的代码有问题这个是很重要的标志是否后子节点标志
![image](https://user-images.githubusercontent.com/16914422/54072368-fdbbca80-42b4-11e9-8b9a-b66849d8817f.png)
![image](https://user-images.githubusercontent.com/16914422/54072408-4d9a9180-42b5-11e9-884a-d1afdab1578d.png)
这个是修复代码
同样的SysDepartTreeModel类没有isLeaf的方法
![image](https://user-images.githubusercontent.com/16914422/54072421-8470a780-42b5-11e9-8597-ed6c637c0427.png)
![image](https://user-images.githubusercontent.com/16914422/54072424-92bec380-42b5-11e9-81ef-648613618edc.png)
修复代码我已添加 上述两个tree类都有这个字段
SysPermissionTree 这个没有titile字段一般都是用来显示名称用的
![image](https://user-images.githubusercontent.com/16914422/54072487-5e97d280-42b6-11e9-867e-bac48a1e5496.png)
![image](https://user-images.githubusercontent.com/16914422/54072496-6a839480-42b6-11e9-95b3-9597607470eb.png)
一般tree类模型中 key title isLeaf是anti-design 的tree控件的关键字建议统一标准 这样能够适应各种情况使用



## zhangdaiscott (10 Mar 2019)

感谢，已处理

