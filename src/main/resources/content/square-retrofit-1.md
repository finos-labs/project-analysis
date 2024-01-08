#cannot build the jars

Owner: square

Repo: retrofit

Labels: 

## sragu (17 Oct 2010)

Since git probably ignored the empty test directories - core/src-tests, io/src-tests, the ant build fails with error message indicating that the directory were not found.

You could either add some tests or dummy files to the test directory.


## jwill (23 Oct 2010)

Just creating the directories allows the build to complete.


## JakeWharton (01 May 2012)

Build has been mavenized with instructions in the readme for compilation.


