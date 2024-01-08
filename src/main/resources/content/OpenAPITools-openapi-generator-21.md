#[java client, gson] Expose @SerializedName value as constant

Owner: OpenAPITools

Repo: openapi-generator

Labels: Client: Java Enhancement: Feature 

## jmini (13 May 2018)

Sometimes when you are writing tests for an API (with Rest-Assured in my case) you can not use the fluent API to build request, because you want to create an alternative request (malformed, corner case...)

You can use rest-assured [RequestSpecBuilder](http://static.javadoc.io/io.rest-assured/rest-assured/3.0.3/io/restassured/builder/RequestSpecBuilder.html) for that. You then write code like this:

```
        final String updateBody = "{"
                + "\"id\": \"" + req.getId() + "\", "
                + "\"version\": \"" + req.getVersion() + "\", "
                + "\"name\": \"" + req.getName() + "\", "
                + "\"billingAddressId\": null, "
                + "\"contactAddressId\": null, "
                + "\"metadata\": null}";

        final Account account = createSuperAdminApi().accountsUpdate().simulateQuery(true)
                .reqSpec(b -> b.setBody(updateBody))
                .executeAs(r -> r.thenReturn());
```

I would be great instead of hard-coding the json member like this, to be able to reference constants:

```
        final String updateBody = "{"
                + "\""+ Acount.SERIALIZED_NAME_Id +"\": \"" + req.getId() + "\", "
                + "\""+ Acount.SERIALIZED_NAME_Version +"\": \"" + req.getVersion() + "\", "
                + "\""+ Acount.SERIALIZED_NAME_Name +"\": \"" + req.getName() + "\", "
                + "\""+ Acount.SERIALIZED_NAME_BillingAddressId +"\": null, "
                + "\""+ Acount.SERIALIZED_NAME_ContactAddressId +"\": null, "
                + "\"metadata\": null}";
```

This way, when the API evolves, the Java compiler indicates that something needs to be changed in the test case (the same way that the fluent api is also changed).




## jmini (15 May 2018)

With #42, the constant will be more what a java developper would expect:

```
        final String updateBody = "{"
                + "\""+ Acount.SERIALIZED_NAME_ID +"\": \"" + req.getId() + "\", "
                + "\""+ Acount.SERIALIZED_NAME_VERSION +"\": \"" + req.getVersion() + "\", "
                + "\""+ Acount.SERIALIZED_NAME_NAME +"\": \"" + req.getName() + "\", "
                + "\""+ Acount.SERIALIZED_NAME_BILLING_ADDRESS_ID +"\": null, "
                + "\""+ Acount.SERIALIZED_NAME_CONTACT_ADDRESS_ID +"\": null, "
                + "\"metadata\": null}";
```

