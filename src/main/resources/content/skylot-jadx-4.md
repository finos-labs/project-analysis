#Broken complex If statements

Owner: skylot

Repo: jadx

Labels: 

## 13-beta2 (08 Dec 2013)

Input:

``` java
    int c;
    String d;
    public void testComplexIf(String a, int b) {
        if (d == null
        || (c == 0 && b != -1 && d.length() == 0)) {
            c = a.codePointAt(c);
        } else {
            if (a.hashCode() != 0xCDE) {
                c = f.compareTo(a);
            }
        }
    }
```

Output:

``` java
    int c;
    String d;
    public void testComplexIf(String a, int b) {
        if (d != null) {
            if (c != 0 || b == -1 || d.length() != 0) {
                if (a.hashCode() != 3294) {
                    c = f.compareTo(a);
                }
                // Fix option#1
                // return;
            }
        }
        // Fix option#2
        // else
        c = a.codePointAt(c);
    }
```


## skylot (09 Dec 2013)

The problem is incorrectly removed 'return' instructions in `jadx.core.dex.visitors.regions.FinishRegions` without this code result will be:

``` java
    public void testComplexIf(String a, int b) {
        if (this.d != null) {
            if (this.c != 0 || b == -1 || this.d.length() != 0) {
                if (a.hashCode() != 3294) {
                    this.c = this.f.compareTo(a);
                    return;
                } else {
                    return;
                }
            }
        }
        this.c = a.codePointAt(this.c);
    }
```

This code is correct. Duplicated 'return' can be moved outside 'if' by simple analysis.


