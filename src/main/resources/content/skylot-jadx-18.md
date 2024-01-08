#Nested 'if'

Owner: skylot

Repo: jadx

Labels: 

## NeoSpb (02 Sept 2014)

I think you can find a solution faster than me. Below is a test that does not decompile.

```
package jadx.samples;

public class TestConditions2 extends AbstractTest {

    private boolean a0 = false;
    private int a1 = 1;
    private int a2 = 2;
    private int a3 = 1;
    private int a4 = 2;

    public void test1a() {

    }

    public void test1b() {

    }

    public void test1c() {

    }

    public boolean test1() {
        if (a0) {
            if ((a1 == 0) || (a2 == 0)) {
                return false;
            }
        } else {
            if ((a3 == 0) || (a4 == 0)) {
                return false;
            }
        }

        test1a();
        test1b();
        test1c();

        return true;
    }

    @Override
    public boolean testRun() throws Exception {
        assertTrue(test1());
        return true;
    }

    public static void main(String[] args) throws Exception {
        new TestConditions2().testRun();
    }
}
```


## skylot (02 Sept 2014)

You found a really bad regression, thanks!
Short issue description:
At some point I try to merge nested if's (for detect ternary condition) and after additional checks decide that this is impossible, but for some blocks 'SKIP' flag was already set and this inconsistence broke whole region structure.
I fix that by split merge info calculation and additional actions after confirmation (confirmMerge method). This is not good solution but I think it is ok unless we have some transactional model with rollback support )


## NeoSpb (03 Sept 2014)

Thanks a lot.


