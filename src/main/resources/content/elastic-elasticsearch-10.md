#Query http listeners

Owner: elastic

Repo: elasticsearch

Labels: >enhancement v0.05.0 

## clintongormley (14 Feb 2010)

In the same way as you can find out if a node is a data node or not, it'd be good to tell if a node has http enabled or not.

One of the things I'd like to be able to do, is to query one node about
the other http enabled nodes in the cluster (in the same was as you can
find out which nodes are data nodes)

In other words, one of my clients starts up, queries the 'main' node
about which listeners are available, then randomly selects one of those
nodes.

The idea is to spread the load between the nodes, and also, if a node
goes down, then my client already has a list of other nodes that it can
try connecting to.

thanks

Clint


## kimchy (14 Feb 2010)

Make sense. This will be part of the admin cluster node info API (REST is: http://localhost:9200/_cluster/nodes?pretty=true).

The json will be:

```
{
  "clusterName" : "elasticsearch",
  "nodes" : {
    "mackimchy-45484" : {
      "name" : "Commander Kraken",
      "transportAddress" : "inet[10.0.0.1/10.0.0.1:9300]",
      "dataNode" : true,
      "httpAddress" : "inet[/10.0.0.1:9200]"
    },
    "mackimchy-13357" : {
      "name" : "Ramshot",
      "transportAddress" : "inet[/10.0.0.1:9301]",
      "dataNode" : true,
      "httpAddress" : "inet[/10.0.0.1:9201]"
    }
  }
}
```

Note that it will now wrap the nodes in the "nodes" element for simpler usage.


## kimchy (14 Feb 2010)

Query http listeners. Closed by b5f3fc9ae1a68a9114acf1ef2bc9bc4d90ad1bea.


## kimchy (14 Feb 2010)

Ohh, and I added an http parameter to include node settings, just use: http://localhost:9200/_cluster/nodes?settings=true


