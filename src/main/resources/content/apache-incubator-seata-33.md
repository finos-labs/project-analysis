#What's the Different?

Owner: apache

Repo: incubator-seata

Labels: 

## lightClouds917 (11 Jan 2019)

There are too many distributed transaction frameworks ,like tcc_transaction,byteTcc,Hmily,raincat .I  do not know how to choose them.

## sharajava (11 Jan 2019)

will publish a wiki about principle and design later. in short, Fescar focus on easy-to-use (without requirements of design on business layer) and high-performance. 

## lightClouds917 (11 Jan 2019)

Do I need to worry about idempotency of a operation?

## tinycedar (11 Jan 2019)

> There are too many distributed transaction frameworks ,like tcc_transaction,byteTcc,Hmily,raincat .I do not know how to choose them.

Have you tried use one of above frameworks ?

## lightClouds917 (11 Jan 2019)

Hmily,raincat ,But our company is using LCN.I am trying byteTcc now.

## cullenx (11 Jan 2019)

> Do I need to worry about idempotency of a operation?

me too

## tinycedar (11 Jan 2019)

> > Do I need to worry about idempotency of a operation?
> 
> me too

No, you don't. Global transaction will be rollback if exception occurs within any branch transaction, so it it just like local transaction.

## fqdeng (11 Jan 2019)

@sharajava I wish your guys make fescar to support spring boot configuration soon.

