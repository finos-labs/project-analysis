#在监控过程中调整流控规则，实时监控监控图中b_qps Y坐标显示错乱

Owner: alibaba

Repo: Sentinel

Labels: kind/bug area/dashboard 

## yihongstar (02 Aug 2018)

<!-- Here is for bug reports and feature requests ONLY! 

If you're looking for help, please check our mail list and the Gitter room.
-->

## Issue Description

Type: bug report

### Describe what happened (or what feature you want)

在监控过程中调整流控规则，实时监控监控图中b_qps Y坐标显示错乱

### Describe what you expected to happen

1、启动dubbo适配器，初始单机阈值为10
2、启动dubbo 消费者发送数据
3、在发送过程中将单机阈值调为80，b_qps显示错乱，p_qps正常


