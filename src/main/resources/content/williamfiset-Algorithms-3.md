#GaussianElimination explanation

Owner: williamfiset

Repo: Algorithms

Labels: 

## FinnLidbetter (22 Jul 2017)

In the GaussianElimination.java file it is written that you should check for consistency and/or multiple  solutions before trying to solve, however I don't think that the methods correctly do this for an arbitrary matrix. I think that the solve method should be run first in order to put the matrix into reduced row echelon form and from there the isInconsistent and hasMultipleSolutions can be used correctly. Does this look/sound right?

## williamfiset (23 Jul 2017)

Yes, what you're saying makes sense. The example I gave in the file doesn't seem correct. I'll update the example tomorrow thanks for the shout out! 

