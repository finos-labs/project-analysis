#Issues in decompilation complicate if

Owner: skylot

Repo: jadx

Labels: 

## dungelin (06 Nov 2014)

Has been long time since i reported same bug, but it still exist (latest unstable version), below test code will wrong when decompile:

```
static int executedCount = 0;
static boolean finished =false;
static int repeatCount=2;

public static void main(String[] args) throws Exception {
    float delta = 5;
    Object object = null;
    check(delta, object);
}

static boolean check(float delta, Object object) {
    if (executedCount != repeatCount) {
        if (isRun(delta, object)) {
            if (finished) {
                return true;
            }

            if (repeatCount == -1) {
                ++executedCount;
                action();
                return false;
            }

            ++executedCount;
            if (executedCount >= repeatCount) {
                return true;
            }

            action();
        }

    }

    return false;
}

public static void action() {
}

public static boolean isRun(float delta, Object object) {
    return true;
};
```


