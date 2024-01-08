#Packages imports?

Owner: TheAlgorithms

Repo: Java

Labels: 

## TheFern2 (02 Oct 2017)

Sorry if this a dumb question. This is my first time attempting to contribute to get onboard with hacktoberfest. If someone tries to run this it will be errors due to no package import lines. Just looking at Sorts package and only shell sort has the import. Other packages have the same issues and some files are wrongly named for example BinaryTreeSort file and the class name is TreeSort.

By the way I can fix this issues if needed to... Before I start working on my cipher classes.

## varunu28 (04 Oct 2017)

@fredz0003  As we are not keeping a Java project structure for this repo, there would not be any need for package import. You can work on naming the file correctly where the file name is not matching with the class name

Thanks for pointing this out. You can go ahead and do the changes and open a PR. Just make sure you keep a check that there are no conflicts with the master repo

## TheFern2 (04 Oct 2017)

@varunu28 Got it. It's what I was thinking. I'll fix any file naming issues on the latest master. 

Forgive me for asking but what is the reason for not doing the package declaration? I mean it is a java repo after all. As of now there are tons of syntax errors in multiple classes, semicolons, just basic stuff. It would be a lot easier to manage as the repo grows if there is some type of guidelines.

So far I've found semicolons missing, not instantiating classes properly, main methods not even running even with the package import. And well the list goes on, I'll finish the simple typos and formatting and submit the PR. And I'll raise another issue for classes that need more work with another branch to keep things separated.

![Image](https://imgur.com/UX7gWN2.png)

