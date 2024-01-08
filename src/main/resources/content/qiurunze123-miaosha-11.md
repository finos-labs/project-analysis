#redis中使用lua脚本也可以解决超卖问题吧。

Owner: qiurunze123

Repo: miaosha

Labels: 

## smartdj (15 Dec 2018)



## qiurunze123 (15 Dec 2018)

bingo !!!!!  可以的  有兴趣的话 可以写进来 随时欢迎！ 

## xmt1139057136 (15 Dec 2018)

想法 + 实践！贴出来。提交，会合并的！

## katncandix2 (10 Jan 2019)

atom get and incr by lua
`-- redis key
local key = KEYS[1]

-- 目标值
local tarVal = KEYS[2]

if tarVal == false
then
    tarVal = 0
end

-- 当前值
local curVal = redis.call("GET",key)

if curVal == false
then
    curVal = 0
end

-- 当前值小于目标值 则进行递增操作
if tonumber(curVal) < tonumber(tarVal)
then
    curVal = tonumber(curVal) + 1
    redis.call("SET",key,curVal)
end

return curVal
`

