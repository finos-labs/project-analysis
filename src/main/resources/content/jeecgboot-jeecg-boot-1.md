#菜单无法获取上级目录 PermissionModal.vue

Owner: jeecgboot

Repo: jeecg-boot

Labels: 

## kangshifu1 (26 Feb 2019)

  queryTreeList().then((res)=>{
          if(res.success){
            that.treeData = [];
            for(let a=0;a<res.result.treeList.length;a++){  // res.result.treeList.length 此处修改即可
              let temp = res.result.treeList[a]; // res.result.treeList.length 此处修改即可
              temp.isLeaf = temp.leaf;
              that.treeData.push(temp);
            }
          }

![image](https://user-images.githubusercontent.com/12906159/53385731-6902c400-39ba-11e9-80cf-d8111c23e85f.png)


## zhangdaiscott (26 Feb 2019)

感谢

