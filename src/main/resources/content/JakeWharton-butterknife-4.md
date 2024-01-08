#Check that the annotated type extends View

Owner: JakeWharton

Repo: butterknife

Labels: 

## pyricau (05 Mar 2013)

Butt erk nife should check that the annotated type extends View. Which is quite straightforward:

``` java
    public boolean isSubtype(TypeMirror potentialSubtype, TypeMirror potentialSupertype) {
        return processingEnv.getTypeUtils().isSubtype(potentialSubtype, potentialSupertype);
    }
```

Guess where that code is coming from? A project from the world of Magic ;)


## pyricau (05 Mar 2013)

To use it you need a `TypeElement` and call `asType()` on it.


