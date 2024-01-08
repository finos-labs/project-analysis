#Wrong java type with one-to-one association element

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## emacarron (05 Mar 2013)

See http://code.google.com/p/mybatis/issues/detail?id=781

Reported by m.geri1974, Feb 20, 2013
Hi i am using the mybatis version mybatis-3.1.1, 

I have a problem with one-to-one association element.

To reproduce the problem, create a mapper like the following:

<mapper namespace="org.mybatis.example.mappers.AssociationBugMapper">

```
<resultMap id="sampleHashResult" type="hashmap">
<result property="f1" column="f1" />
<result property="f2" column="f2" />
<association property="a1" javaType="java.lang.String"
    column="{param1=f1}" select="associationTest" />
<association property="a2" javaType="java.lang.String"
    column="{param1=f1}" select="associationTest" />
</resultMap>

<select id="sample" resultMap="sampleHashResult">
    SELECT 'field1' as f1, 10000 as f2
</select>

<select id="associationTest" resultType="java.lang.String">
    select 'test'
</select>
```

</mapper>

Running the code below:

List<HashMap> results = session.selectList("sample");

for (HashMap r : results) {
    System.out.println("a1 class: " + r.get("a1").getClass());
    System.out.println("a2 class: " + r.get("a2").getClass());
}

I expected to see the following output:

a1 class: class java.lang.String
a2 class: class java.lang.String

While it prints: 

a1 class: class java.lang.String
a2 class: class java.util.ArrayList         

It seems that after the first use of the 'association', which returns the expected String result type, then it returns an ArrayList of one element containing the String value (and it's an unexpected behaviour for my application).

I attached the full working eclipse project sample. Let me know if you need any further details.

Thanks.


