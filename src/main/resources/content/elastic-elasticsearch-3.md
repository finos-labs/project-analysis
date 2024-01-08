#Transport: Support local (JVM level) transport

Owner: elastic

Repo: elasticsearch

Labels: >feature v0.05.0 

## kimchy (11 Feb 2010)

Allow to have a JVM (well, actually class loader) level transport for simple testing / embedding of a single node (which, potentially exists with other nodes in the same class loader).

Enable it using:

```
transport:
    type: local
```

Or using:

```
node:
    local: true
```

(which will also enable other modules to be local, such as the discovery)


## kimchy (11 Feb 2010)

Transport: Support local (JVM level) transport. Closed by 847db717c66509a817e1b965226ee1e44c08918d.


