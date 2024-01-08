#Create Mapping API: Automatically create indices

Owner: elastic

Repo: elasticsearch

Labels: >enhancement v0.05.0 

## kimchy (15 Feb 2010)

In a similar manner that the index operation automatically create the index, the mapping API should do the same. This can be disable using the setting 'action.autoCreateIndex'.


## kimchy (15 Feb 2010)

Create Mapping API: Automatically create indices. Closed by 9a9ce99364fcf7d77d81b5300530fac2ee435f37.


## rectalogic (17 Mar 2015)

Automatically creating the index doesn't seem to work with elasticsearch 1.4.4

```
curl -XPUT http://localhost:9200/twitter/tweet/_mapping -d '
> {
>     tweet : {
>         properties : {
>             message : {type : "string", store : "yes"}
>         }
>     }
> }
> '
{"error":"IndexMissingException[[twitter] missing]","status":404}
```


## markwalkom (17 Mar 2015)

This is an issue that was closed 5 years ago!

If you have problems then I'd suggest trying the Elasticsearch mailing list - https://groups.google.com/forum/#!forum/elasticsearch


