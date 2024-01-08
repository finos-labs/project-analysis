#Query DSL: Terms Filter

Owner: elastic

Repo: elasticsearch

Labels: >enhancement v0.05.0 

## kimchy (09 Feb 2010)

Support terms filter which allows to configure more than one term for a specific field. For example:

```
{
    filteredQuery : {
        query : {
            term : { "name.first" : "shay" }
        },
        filter : {
            terms : {
                "name.last" : ["banon", "kimchy"]
            }
        }
    }
}
```


## kimchy (09 Feb 2010)

Support terms filter, closed by bd2b0a632bfc5aabb408e7f47cfaa52a7d1b2b50


