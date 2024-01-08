#Incorrect pull requests getting merged

Owner: TheAlgorithms

Repo: Java

Labels: 

## varunu28 (04 Oct 2017)

I was looking through the commits for last few days and found that a few PRs were merged without proper review.
Ex: [Towers Of Hanoi in Misc section](https://github.com/manmeet-22/Java/blob/1e99c48d4a3346476d7d48b8b03ae8ddea27569c/Misc/TowerOfHanoiUsingRecursion)

Issues:
1. The  code is committed from a personal package and the package name has now become a part of the codebase 
2. **The code file is not even a Java file**
3. There is no structure to the code. No description about the function or the parameters passed.

I have therefore opened a new PR for the algorithm #143 

@dynamitechetan @AnupKumarPanwar Please make sure that the PRs are reviewed properly. I totally understand that you guys would be getting multiple PRs a day due to hacktoberfest but at the same time lets make sure that the codebase in the repo does not degrades due to this. Thanks 

## TheFern2 (05 Oct 2017)

@varunu28 I just submitted a PR yesterday fixing multiple typos, and issues as you've described. Anyways I had intended to fix class file names. Some file names are different that the class. That has been fixed on my PR. While poking around the code, there was several erroneous main methods. Some main methods taking input and not using it at all. I'll raise an new issue for one 
[OctalToBinary.java](https://github.com/manmeet-22/Java/blob/1e99c48d4a3346476d7d48b8b03ae8ddea27569c/Conversions/OctalToBinary.java) There are methods declared within a method. 

I suggest to have an example class of what is proper for the repo, and add that to the readme.
I know that the idea of the repo is to use individual classes on your project or what not, even for learning. For people contributing like myself, it would be a lot easier if I can just open the latest repo on my IDE with no errors without package declarations on the classes it is very hard to do.

