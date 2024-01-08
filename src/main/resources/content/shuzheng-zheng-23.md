#zheng-upms模块去除权限没有限定角色id的问题

Owner: shuzheng

Repo: zheng

Labels: 

## greydesolate (02 Apr 2017)


这个问题是同事使用权限功能的时候发现，他新建了一个角色，然后赋予了一堆权限OK没问题。
之后他去除这个角色一些权限，发现admin的权限也随之减少了。

zheng/zheng-upms/zheng-upms-server/src/main/java/com/zheng/upms/server/controller/manage/UpmsRoleController.java
90行，少了增加角色id的条件 .andRoleIdEqualTo(id)


当前
// 减权限
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria()
            .andPermissionIdIn(deleteIds);
是否应修改为
// 减权限
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria()
            .andPermissionIdIn(deleteIds)**.andRoleIdEqualTo(id);**

## shuzheng (02 Apr 2017)

已修复！

