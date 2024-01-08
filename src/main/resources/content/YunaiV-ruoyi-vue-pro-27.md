#搭建本地环境时候前端工程启动失败？请问下这个对node.js 版本有要求吗

Owner: YunaiV

Repo: ruoyi-vue-pro

Labels: question 

## weiwei91 (06 Aug 2021)

错误提示信息如下：
INFO  Starting development server...
98% after emitting CopyPlugin

 ERROR  Failed to compile with 1 error                                                                                                 11:02:20

 error  in ./src/main.js

Syntax Error: D:\IDEA_workplace\ruoyi-vue-pro\yudao-admin-ui\node_modules\eslint\lib\cli-engine\cli-engine.js:421
    } catch {
            ^

SyntaxError: Unexpected token {


 @ multi (webpack)-dev-server/client?http://10.16.84.108:80&sockPath=/sockjs-node (webpack)/hot/dev-server.js ./src/main.js


## weiwei91 (06 Aug 2021)

我本地的node.js版本时v8.15.0


## YunaiV (07 Aug 2021)

太低了，我自己本地是 14

## weiwei91 (07 Aug 2021)

确实是nodejs版本的问题，高了或者低了都有问题，建议在部署文档中说明下nodejs的版本，避免出现我这个类似的问题

