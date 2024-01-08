#BinaryTree method find() confusing return value

Owner: TheAlgorithms

Repo: Java

Labels: 

## mpokryva (28 Sept 2017)

The `find(int key)` method in the BinaryTree class returns the node with the value `int`, if it's found, and root if there is no such node. This is confusing behavior, first of all, as one would expect it to return null, and is also problematic, since the root itself can match the query. 

I suggest simply changing this function to return null on an unsuccessful query.

