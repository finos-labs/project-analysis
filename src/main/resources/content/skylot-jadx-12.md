#Issues in decompilation (complicate if and else)

Owner: skylot

Repo: jadx

Labels: bug 

## dungelin (07 Jul 2014)

Thank for your great work ,
But this code will not decompile correctly and hope you can fix it:

``` java
    static boolean autoStop = true;
    static boolean qualityReading = false;
    static int lastValidRaw = -1;

    public static void main(String[] args) throws Exception {
        int a = 5;
        int b = 30;
        dataProcess(a,b);
    }

    public static void dataProcess(int raw, int quality) {
        if(quality >= 10 && raw != 0) {
            System.out.println ("OK"+ raw); 
           qualityReading = false;
        } else if(raw != 0 && quality >= 6 && qualityReading) {
            System.out.println ("Quit OK"+ raw); 
        } else {
            System.out.println ("Not OK"+ raw); 
        }

        if(quality < 30) {
           int timeLeft = 30 - quality;
           if(quality >= 10) {
               System.out.println ("Processing"+ timeLeft); 
           }
        } else {
            System.out.println ("Finish Processing");
           if(raw > 0) {
              lastValidRaw = raw;
           }
        }

        if(quality >= 30 && autoStop) {
            System.out.println ("Finished");
        }

        if(!autoStop && lastValidRaw > -1 && quality < 10) {
            System.out.println ("Finished");
        }

     }
```


## skylot (07 Jul 2014)

Thanks, I already working on issue with complex if (also pointed in issue #9 case 1),
and there are so many possible combinations so I stuck to resolve some of them.
I hope I will finish this soon :)


## dungelin (08 Jul 2014)

I have never read your code carefully , but there are some java decompiler tool from other developer face this problem and they seem fixed. I don't know if it can apply to dex code.
One i know is Procyon https://bitbucket.org/mstrobel/procyon/wiki/Java%20Decompiler


## dungelin (31 Jul 2014)

Thank you, It nearly perfect now still have some bug with very complex conditional in try/catch block but i still not find a way to write test code. I will report when i can re-produce it.


