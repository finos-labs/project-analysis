#job执行的进度反馈

Owner: xuxueli

Repo: xxl-job

Labels: 

## buliugu (14 Dec 2016)

执行器执行耗时job，加入进度反馈的可选接口是不是更好？现在的只能通过非实时的执行日志（需要手动刷新）来查看进度（还需要JobHandler里打好日志）

## buliugu (14 Dec 2016)

执行日志需要实时的话需要传递行号，通过ajax获取最新append到html

## xuxueli (24 Feb 2017)

你好，实时日志正在排期，支持根据行号获取新增日志并实时刷新，类似的是 Jenkins 的日志。
谢谢反馈啊  ：）

## waterWang (24 Mar 2017)

期待job进度反馈接口

## xuxueli (05 Feb 2018)

你好，任务进度，可以通过Rolling Log来监控，已经在最新版本Release发布可以获取体验。

