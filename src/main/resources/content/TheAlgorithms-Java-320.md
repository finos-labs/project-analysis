#[ciphers/AES] Rijndael Galois Field Operations - Design Question

Owner: TheAlgorithms

Repo: Java

Labels: 

## Phil-Schmidt (19 Nov 2017)

The AES algorithm uses a couple of math operations on 8-bit integers that are not calculated in the regular integer space, but in Rijndael's Finite Field.
There are different ways to approach this issue: 
You could implement different sub algorithms that will do the calculation for you. This would pose a more 'complete' solution to this problem, however it will make the algorithm harder to read.
Alternatively you can simply use precalculated lookup tables for these operations. This is the most common way to build AES.
Personally I think lookup tables is the way to go, however I would like to hear some other opinions about how The Algorithms' implementation of AES is supposed to handle these functions.

