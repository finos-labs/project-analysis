#InitExecutor may repeat "doInit"

Owner: alibaba

Repo: Sentinel

Labels: 

## xuyue531 (24 Jul 2018)

InitExecutor#doInit() may be executed repeatedly.

we can do this to avoid repeat doInit.
```java
private static AtomicBoolean initialized = new AtomicBoolean(false);
...
public static void doInit() {
  if (!initialized.compareAndSet(false, true)) {
       return;
  }
  ...
}
```


## sczyh30 (24 Jul 2018)

Thanks for reporting!

## CodeIngL (30 Jul 2018)

it only happen in `staic { }`.   is it a NotThreadSafe ? It is ThreadSafe

