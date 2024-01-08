#rocketMq will support master/slave switch when master is broke down in fuature version

Owner: apache

Repo: rocketmq

Labels: 

## lebron374 (22 Mar 2018)

in current version，a broker cluster include 3 master-slave brokers，when use order message，one broker down will cause message send to other broker，so during the switch order message will not keep order。

so i just ask will feature version support master/slave switch automatic

## vongosling (26 Mar 2018)

we will create another solution for HA. BTW, please ask the question as our template instruction.

