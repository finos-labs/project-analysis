#About UNDO_LOG table

Owner: apache

Repo: incubator-seata

Labels: status: inactive 

## jingzhouzhao (11 Jan 2019)

The same db used by the three services in the demo, in the actual application, whether the db corresponding to each service needs to create UNDO_LOG table?
// demo中三个service使用的同一个db，在实际应用中，是否每个service对应的db都需要create UNDO_LOG table?

// ***!!!!WE STRONGLY ENCOURAGE YOU TO DESCRIBE YOUR ISSUE IN ENGLISH!!!!***

## sharajava (11 Jan 2019)

Yes, every db involved in Fescar managed distributed transaction should have a  undo_log table.

## fescar-robot (18 Jan 2019)

This issue has seen no activity in 7 days, it has been automatically closed for house-keeping purpose.

