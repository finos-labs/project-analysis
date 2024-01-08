#Ubuntu Vivid Vervet - missing zip (very low priority)

Owner: bazelbuild

Repo: bazel

Labels: 

## malkia (24 Mar 2015)

I'm using Ubuntu Vivid Vervet under croutonized chromebook, and found that one dependency (zip) is missing, which this command below fixes:

sudo apt-get install zip

(It might be that under normal ubuntu install it's always present)


## kchodorow (24 Mar 2015)

Thanks, adding it to the install reqs.


