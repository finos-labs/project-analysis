#Index variable is not reset when multiple foreach loops are used in a single insert

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## jgorinsky (09 Mar 2013)

I have multiple for each loops in a single insert as shown in Mapper.xml below. In 3.1.1 and before, the resulting sql would look like

``` sql
insert into users (id, name, first_attr_1, first_attr_2, second_attr_1, second_attr_2) ...
```

In 3.2.0, an exception is thrown because the resulting sql is:

``` sql
insert into users(id, name, first_attr_1, first_attr_2, second_attr_3, second_attr_4) ...
```

It seems that the index is not being reset before each foreach. A patch with a test for this issue is at https://gist.github.com/jgorinsky/5126131
### Mapper.xml

``` xml
<insert id="insertUser" parameterType="org.apache.ibatis.submitted.multipleiterates.User">
        insert into users
        (id,
        name,
        <foreach item="attr" index="index" collection="firstAttr" separator=",">
            first_attr_${index + 1}
        </foreach>,
        <foreach item="attr" index="index" collection="secondAttr" separator=",">
            second_attr_${index + 1}
        </foreach>
        )
        values(
        1,
        'User1',
        <foreach item="attr" index="index" collection="firstAttr" separator=",">
            #{attr}
        </foreach>,
        <foreach item="attr" index="index" collection="secondAttr" separator=",">
            #{attr}
        </foreach>
        )
    </insert>
```


