#Query DSL: Bool query/filter to be valid JSON

Owner: elastic

Repo: elasticsearch

Labels: >enhancement v0.05.0 

## kimchy (12 Feb 2010)

Currently, the bool query is not a valid Javassctipt (still valid JSON though...) since indicating two must clauses uses the same field name for a JSON object. The old way should still be supported, but, we should also allow for something like this:

Currently, the bool query is not a valid Javassctipt (still valid JSON though...) since indicating two must clauses uses the same field name for a JSON object. The old way should still be supported, but, we should also allow for something like this:

```
{
    bool : {
        must : [
            {
                queryString : {
                    defaultField : "content",
                    query : "test1"
                }
            },
            {
                queryString : {
                    defaultField : "content",
                    query : "test4"
                }
            }
        ],
        mustNot: {
            queryString : {
                defaultField : "content",
                query : "test2"
            }
        },
        should: {
            queryString : {
                defaultField : "content",
                query : "test3"
            }
        }
    }
}
```


## simonw (12 Feb 2010)

It's definitely still invalid JSON - the JSON spec on http://www.json.org/ is clear that double quotes are required around string values, including object keys. In practice it's not a huge problem that elasticsearch accepts invalid JSON for the queries though, provided it also accepts valid JSON. The JSON output by elasticsearch uses quotes in the right places and is absolutely fine.

Python's JSON parser is strict by default, and throws the following exception if I feed in the above example:

ValueError: Expecting property name: line 2 column 5 (char 6)


## kimchy (12 Feb 2010)

The invalid part I was talking about is the usage of the same field name twice within an object. 

Regarding the quotes on field names, you are absolutely correct. ES does accept field names that are either quoted or not, for two reasons:
1. Less text on the wire / simplifies writing examples :).
2. Makes direct Javascript usage simpler.


## clintongormley (19 Feb 2010)

Actually, non-unique key names are invalid JSON.  See section 2.2 in http://www.ietf.org/rfc/rfc4627.txt?number=4627

So whenever you have repeatable items, you should provide both:
    { key: value } 
    { keys: [ value_1, value_n ] }


## clintongormley (19 Feb 2010)

or:

```
{ key: value }  | { key: [ value_1, value_n] }
```


## kimchy (20 Feb 2010)

They say SHOULD not must :). In any case, I will make sure in the future that the SHOULD, with all its uppercase glory, is maintained :).


