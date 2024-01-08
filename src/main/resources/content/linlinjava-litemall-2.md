#管理后台启动异常

Owner: linlinjava

Repo: litemall

Labels: 问题不明确 

## sujg (28 Mar 2018)

      ` ERROR  Failed to compile with 1 errors                                                                                                         
        20:33:45

        This relative module was not found:

         * ./src/main.js in multi (webpack)-dev-server/client?http://localhost:9527 webpack/hot/dev-server ./src/main.js`

跑了下后台vue，总是提示这个，请指教下

## linlinjava (29 Mar 2018)

你好，我其实主攻后台，目前对vue和vue-element-admin只是简单使用，还没有深入了解，所以对于你这个错误也比较困惑。我这里运行可以的，采用Visual Studio Code，然后打开litemall-admin文件夹，然后执行build命令，如下图所示：

![image](https://user-images.githubusercontent.com/4223779/38089808-42c560e6-3393-11e8-8fa2-f0b5bd57158c.png)

要不麻烦你再google看看，例如有这个帖子https://github.com/vuejs/vue-cli/issues/439，应该是node环境问题吧。


## sujg (30 Mar 2018)

3Q，本人mac，换了linux就ok的，估计是node环境问题，很感谢能够回答

## linlinjava (30 Mar 2018)

本人win10开发没有问题，有可能是你之前装过导致遗留环境不一致或其他。

