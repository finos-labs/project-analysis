#No verification of the same object references in different registers

Owner: skylot

Repo: jadx

Labels: 

## NeoSpb (12 Sept 2014)

The code below shows the meaning of the problem

```
.method private calc(DDLSomeObject;)LSomeObject;
    .locals 22
    .param p1, "arg1"    # D
    .param p3, "arg2"    # D
    .param p5, "arg3"    # LSomeObject;

    .prologue
    .line 54
    new-instance v17, LSomeObject;

    move-object/from16 v0, v17

    move-object/from16 v1, p5

    invoke-direct {v0, v1}, LSomeObject;-><init>(LSomeObject;)V

    .line 59
    .local v17, "localSomeObject":LSomeObject;

```

The Jadx produces:

```
    private SomeObject calc(double arg1, double arg2, SomeObject arg3) {

        SomeObject someObject = localSomeObject;
        SomeObject someObject2 = arg3;

```

I think there must be some mechanism to control where the reference to the object is located. (or make wrap instruction?)


## skylot (13 Sept 2014)

Some time ago I fixed similar issue for constructor call for "this" arg (RegisterArg.isThis() method). Here we have same problem, I will make more common analysis.


