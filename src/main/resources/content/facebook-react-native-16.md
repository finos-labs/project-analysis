#Improve error when forgetting to npm install

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## vjeux (31 Jan 2015)

We need to have a better error message than this non descriptive red box and terminal error

![screen shot 2015-01-30 at 5 05 02 pm](https://cloud.githubusercontent.com/assets/197597/5986064/32b67980-a8a2-11e4-9c34-79b6f0072a24.png)


## amccloud (31 Jan 2015)

I've attempted to improve the error after running the packager without running `npm install` first by detecting if the node_modules directory exist.


## vjeux (31 Jan 2015)

Haha you beat me to it, I have done the same thing and would setup a pr when I get a chance :)


## vjeux (07 Feb 2015)

Closing as there's a pull request


