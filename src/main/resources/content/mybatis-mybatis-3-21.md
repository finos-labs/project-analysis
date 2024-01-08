#Allow <set> to trim comma prefix

Owner: mybatis

Repo: mybatis-3

Labels: enhancement 

## fengxx (22 Mar 2013)

Propose to change set node to allow it trim comma prefix

``` java
public class SetSqlNode extends TrimSqlNode {

  public SetSqlNode(Configuration configuration,SqlNode contents) {
    super(configuration, contents, "SET", ",", null, ",");
  }

}

```

Reason:
It is natural for users migrated from ibatis to write code like this

``` xml
UPDATE user
<set>
    <if test="name != null">
        name=#{name}
    </if>
    <if test="desc != null">
        ,desc=#{desc}
    </if>
</set>
```

Instead of

``` xml
UPDATE user
<set>
    <if test="name != null">
        name=#{name},
    </if>
    <if test="desc != null">
        desc=#{desc}
    </if>
</set>
```

The added feature will not break anything, although may use some cpu cycles and slow down a bit.


## harawata (30 Oct 2018)

I have just committed the fix.
Thank you! :D

