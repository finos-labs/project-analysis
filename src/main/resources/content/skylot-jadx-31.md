#Wrong with complex if/else condition and for loop (while loop)

Owner: skylot

Repo: jadx

Labels: 

## dungelin (05 Dec 2014)

I found this bug long ago but today i find a simple test case to report.
This occur with latest code base.

```
public class Main {
    static boolean[][] occupied = new boolean[30][70];
    static boolean placingStone = true;

    public static void main(String[] args) {

        if (findPoint(14, 20)) {
            System.out.printf("Found!!");
        } else {
            System.out.printf("Not Found!!");
        }

    }

    private static boolean findPoint(int xx, int yy) {
        int[] extraArray = new int[] { 10, 45, 300, 600, 800 };

        if (extraArray != null && placingStone) {
            for (int i = 0; i < extraArray.length; i += 2) {
                int tX;
                int tY;
                if (yy % 2 == 0) {
                    if (extraArray[i + 1] % 2 == 0) {
                        tX = xx + extraArray[i];
                    } else {
                        tX = extraArray[i] + xx - 1;
                    }

                    tY = yy + extraArray[i + 1];
                } else {
                    tX = xx + extraArray[i];
                    tY = yy + extraArray[i + 1];
                }

                if (tX < 0 || tY < 0 || tY % 2 != 0 && tX > 28 || tY > 118
                        || occupied[tX][tY]) {
                    return false;
                }
            }
        }

        return true;
    }

}
```


