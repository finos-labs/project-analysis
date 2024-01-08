#broken native type support (boolean, int)

Owner: skylot

Repo: jadx

Labels: 

## dtr2 (17 Dec 2014)

version 0.54 is a regression to 0.53 in that matter.
e.g.: 
with 0.54:

```
            booleanValue = (z && threadStorage.getReducer(str).andReduction.booleanValue()) ? 1 : null;
```

while with 0.53:

```
           booleanValue = (z && threadStorage.getReducer(str).andReduction.booleanValue()) ? 1 : 0;
```


## skylot (17 Dec 2014)

Thank you! 
I hope I fix this issue in latest commit, and if you can please test jadx (from [unstable](https://drone.io/github.com/skylot/jadx/files)) for your case.


