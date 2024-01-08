#leetcode/array/LongestConsecutiveSequence.java

Owner: kdn251

Repo: interviews

Labels: 

## chr1shung (04 Jun 2019)

[LongestConsecutiveSequence](https://github.com/kdn251/interviews/blob/master/leetcode/array/LongestConsecutiveSequence.java)

Will it be better if Line 26 while loop be like ? :
```C++
while(set.contains(current + 1)) {
    currentMax++;
    current++;
}
```
in C++ if I use `n` instead of `current` the output would be 7 with sample input.


