#JdbcType cannot be resolved!

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## morse2 (13 Mar 2013)

When I use mybatis-3.2.1 version, I find an issue when mybatis framework is resolving jdbc type. There is code fragment as below.

Mapper xml file:
<update id="updateStatus" statementType="PREPARED" parameterType="x.y.z.FooImpl">
    update upc  status = #{status,jdbcType=VARCHAR } where uidpk = #{uidPk,jdbcType=BIGINT}
</update>

When I invoke this statement, an error occurs, java.lang.IllegalArgumentException: No enum const class org.apache.ibatis.type.JdbcType.VARCHAR. Finally, I find this error is thrown from org.apache.ibatis.builder.resolveJdbcType, because parameter's jdbcType has a blank space on the right of VARCHAR. But I couldn't find the same issue in mybatis version 3.1.1.

![shotsnap](https://f.cloud.github.com/assets/2410152/252958/ea063abc-8bbc-11e2-9289-cd5b79e7beb6.png)


