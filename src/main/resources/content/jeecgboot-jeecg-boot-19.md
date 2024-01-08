#免登录访问页面

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## zhanleai (19 Mar 2019)

我的是基于vue的版本，我想不用登录，然后直接访问某个页面，并对页面进行增删改查，请问如何做？我在前后台的白名单里都加了我的访问路径，但是还是不行。请求大佬帮忙解答下，在线等。。。

## zhanleai (19 Mar 2019)

是不是基于vue的版本，无法进行免登录操作？

## zhangdaiscott (20 Mar 2019)

看常见问题贴 12
http://www.jeecg.org/forum.php?mod=viewthread&tid=7816&page=1&extra=#pid21237

## zhanleai (20 Mar 2019)

已经搞好了，感谢。补充下，常见问题贴里面还漏了一个文件需要配置：ant-design-jeecg-vue/src/permission.js，这里也需要加上
![image](https://user-images.githubusercontent.com/47999989/54657214-7e739580-4b03-11e9-8ecc-8c429de110b4.png)



## JoneZhu (28 Aug 2023)

基于Jeecg 3.5.3 再回答一波
Jeecg 的整个项目结构配置发生了很大的变化，那么如何免登录访问页面
1. 配置路由路径，代码位置在 jeecgboot-vue3-master/src/router/routes/index.ts ，并修改basicRoutes中添加你定义的路由对象
`
export const QAFormRoute: AppRouteRecordRaw = {
  path: '/qx/qa/form',
  name: 'QxQaForm',
  component: () => import('/@/views/qx/qa/components/QuestionnaireAddForm.vue'),
  meta: {
    title: '千禧问卷表单页',
    ignoreAuth: true,
  },
};
`
<img width="992" alt="image" src="https://github.com/jeecgboot/jeecg-boot/assets/4835023/d3af6a5b-0975-4425-bc33-8378e2f9dacc">

2. 配置白名单，代码位置在/jeecgboot-vue3-master/src/router/guard/permissionGuard.ts，在whitePathList中添加你的免登录页面路径
`const whitePathList: PageEnum[] = [PageEnum.QX_QA_SUCCESS,PageEnum.QX_QA_FORM];`
<img width="906" alt="image" src="https://github.com/jeecgboot/jeecg-boot/assets/4835023/b756f3ae-2eff-4d2b-93c7-59fc3301d906">


