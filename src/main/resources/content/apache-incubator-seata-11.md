#Is this a potential bug ?

Owner: apache

Repo: incubator-seata

Labels: 

## jiayangchen (10 Jan 2019)

in the following code, i guess what actually it wants to convey is **!inGlobalTransaction()**
```java
public static boolean inGlobalTransaction() {
        return CONTEXT_HOLDER.get(KEY_XID) != null;
    }

    public static void assertNotInGlobalTransaction() {
        if (inGlobalTransaction()) {
            throw new ShouldNeverHappenException();
        }
    }
```


## slievrly (10 Jan 2019)

@jiayangchen it‘s correct，not mistake。

