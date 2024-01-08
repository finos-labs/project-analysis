#代码生成vm问题

Owner: YunaiV

Repo: ruoyi-vue-pro

Labels: bug 

## starshift3 (28 May 2021)

1. src/main/resources/codegen/java/dal/do.vm 中第32行缺少  “(”
 @TableId#if (${column.javaType} == 'String')**(**type = IdType.INPUT)#end

2. src/main/resources/codegen/java/controller/vo/updateReqVO.vm 中第10行缺少 ")"
#if (${column.updateOperation} && (!${column.createOperation} || !${column.listOperationResult})
    && ${column.javaType} == "Date") **)**## 时间类型

3.src/main/resources/codegen/java/enums/errorcode.vm 为什么不直接生成可用的interface，错误码留空？目前这样生成出来一开始看的有点懵逼

4. 不是代码问题，开发时如果遇到使用字典的字段下拉菜单为空，记得在vue工程src/utils/dict.js 加上对应配置

5. “菜单管理”配置的一级菜单(menu_type=1)和二级菜单(menu_type=2)，左侧菜单中只显示二级菜单，没有一级菜单。先记录下，待定位

## YunaiV (29 May 2021)

收到。我review下这块的代码噢。

## YunaiV (19 Aug 2022)

已修复。

