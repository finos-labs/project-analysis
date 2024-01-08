#UUIDGenerator 's magic number?

Owner: apache

Repo: incubator-seata

Labels: type: bug 

## andaren (10 Jan 2019)

com.alibaba.fescar.server.UUIDGenerator
该类中
generateUUID()方法中2000000000（9个0）是因为写错吗？还是没有用到UUID_INTERNAL（8个0）？

## sharajava (11 Jan 2019)

It's a stupid bug, Thanks for pointing it out. 👍 

BTW, It's not a very good design but works in fact. Type of UUID is long, we know long scope it about 200000000, sure we pick 200000000 as the UUID boundary.

Also, welcome you to share your UUID generator impl with us.

## andaren (11 Jan 2019)

In java:

Name | Width | Range
-- | -- | --
long | 64 | –9,223,372,036,854,775,808 to 9 ,223,372,036,854,775,807
int | 32 | –2,147,483,648 to 2,147,483,647
short | 16 | – 32,768 to 32,767
byte | 8 | – 128 to 127

e..so, why is 200000000? ^_^

## sharajava (14 Jan 2019)

Oh, sorry I made a mistake here. It was 2000000000.

## sharajava (14 Jan 2019)

Such design is by history, we made transactionId with type of int in the early version. For compatibility, we choose 20 0000 0000 as the internal number.

## sharajava (14 Jan 2019)

In the Fescar project, we don't need to be under such limit, I think. I will try to provide a fix.

