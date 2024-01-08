#Found issues in decompilation

Owner: skylot

Repo: jadx

Labels: bug 

## goldbug23 (23 Jun 2014)

Thanks for your brilliant work.  I found two cases which provides wrong results.  Hope you could fix it.

//Src 1

```
public class JavaTest {
    public static void main(String[] args) throws Exception {
        boolean a = true;
        int b = 3;
        if (a || b > 2)
          b++;

        if (!a || ( b >= 0 && b <=11))  
            System.out.println ("OK");
        else
            System.out.println ("Not Reach");   
        System.out.println ("End");
    }
}
```

//Src 2

```
public class JavaTest {
    public static void main(String[] args) throws Exception {
        int a = 3 * 5;
        String s = null;

        switch (a % 4) {
            case 1:
                s = "1";
                break;
            case 2:
                s = "2";
                break;
            case 3:
                s = "3";
                break;
            case 4:
                s = "4";
                break;
            default:
                System.out.println ("Not Reach");   
        }
        System.out.println (s);
    }
}
```


## goldbug23 (13 Jul 2014)

Your latest fix is near perfect.  Please don't mind I write something weird.  Hope you could fix it.

//Src 3

```
public class JavaTest {
    public static void main(String[] args) throws Exception {
        int a = 0;
        int b = 4;
        int c = 0;
        while (a < 12) {
            if (b + a < 9) {
                if (b < 8) {
                    if (b >= 2 && a > -1) {
                        if (b < 6) {
                            System.out.println ("OK");
                            c = b + 1;
                        } 
                    }
                    c = b;
                }
            }
            c = b;
            b++;
            b = c;
            a++;
        }
    }
}
```

//Src 4

```
public class JavaTest {
    public static void main(String[] args) throws Exception {
        int n = -1;
        int n2;
        for (n2 = n; n2 < 0; n2+=12) {
        }
        for (; n2 > 11; n2-=12) {
        }
        System.out.println (n2); 
    }
}
```


## goldbug23 (29 Jul 2014)

"core: fix condition in loops (issue #9)" is perfect in the for loop.  I can't find any issue.

Src3 is still not fixed yet.  Looking forward to seeing your next version.


## skylot (29 Jul 2014)

In last commits I fixed Src 3.
Interesting fact:
In Src 3 `b` variable always equals `4` and after converting to dex it is inlined in `if (b + a < 9) {` and it is became `if (a + 4 < 9) {` and now I can't undo it (decompile to original expression). 
I did't know that `dx` can make such optimizations.

Thank you for your bug reports :+1: 


## goldbug23 (31 Jul 2014)

Very good.  I have no other issues.


