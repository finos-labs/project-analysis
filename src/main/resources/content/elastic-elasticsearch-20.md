#flush_index returns both success and failure

Owner: elastic

Repo: elasticsearch

Labels: 

## clintongormley (16 Feb 2010)

while running the default server, just started with

```
./bin/elasticsearch -f
```

I create an index, then flush it, and it returns:

```
'{
   "ok" : true,
   "_shards" : {
      "failed" : 5,
      "successful" : 5,
      "total" : 10
   }
}
'
```

Why do 5 fail?


## kimchy (16 Feb 2010)

Its because, by default, an index is created with 5 shards, each with one replica. When you start a single node, only 5 shards are allocated on that node, when you start another one, the other 5 replicas will be allocated. The failed ones refer to the 5 shards that have not been started yet.


## clintongormley (17 Feb 2010)

Thanks for the explanation


## kimchy (18 Feb 2010)

Closing this. Later on, I will add explicit explanation in the response as to why each shard failed...


## kimchy (24 Feb 2010)

Just an update, responses now return failure reasons which will make things a bit more usable.


