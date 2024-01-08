#Compute the expected result by using an arithmetic series instead of looping and adding [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 7](http://code.google.com/p/disruptor/issues/detail?id=7) moved from a Google Code project.
Added by 2011-08-11T22:38:51.000Z by [cleh...@gmail.com](http://code.google.com/u/116165689957650735628/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Fixed).

Original labels: Priority-Medium, Type-Enhancement
### Original description

```
Not an issue per se. I just noticed it when looking over the tests and it seemed strange.

The expected result for UniCast unit tests is computed by a loop and an accumulator.
An arithmetic series could be used instead. 
Also the code is duplicated. 

Added getArithmeticSeries to Utils and removed duplication.

Perhaps a less general formula like n(n-1)/2 and single parameter method could be used instead for simplicity. 
```


