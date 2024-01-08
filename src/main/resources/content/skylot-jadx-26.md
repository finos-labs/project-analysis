#Can't decompile one method, always the same

Owner: skylot

Repo: jadx

Labels: 

## andrewpros (10 Nov 2014)

Hi.
 I tried every known java decompiler and the result is always the same on one method, it just does not decompile., but the method is simple.

In jadx it is always inconsistent code error

```
/* JADX WARNING: inconsistent code. */
/* Code decompiled incorrectly, please refer to instructions dump. */
```

it is in utils.Api class, apiRequest method, jar file http://www59.zippyshare.com/v/27225685/file.html

Is it really that hard to fix it?


## skylot (11 Nov 2014)

I don't think this method is simple, there are a lot of try/catch with finally blocks.
Using jadx with `--show-bad-code` command line option you can see this method code and it looks fine but without 'finally' blocks (code duplicated in 'try' and every 'catch' blocks), because jadx don't fully support 'finally' blocks yet.

Also for better understand what some method do, you can make methods control flow graphs using `--cfg` or `--raw-cfg` command line options. You will find generated '.dot' files which can be opened using [xdot](https://github.com/jrfonseca/xdot.py) or [ZGRViewer](http://zvtm.sourceforge.net/zgrviewer.html).


## andrewpros (12 Nov 2014)

Thank you very much for this answer, it helps a lot.

As i wrote, it is not only jadx problem, i was trying many java decompilers and it is always the same method, the same problem, don't know why the try/catch/finally is such a problem, but it looks like, every software has the same problem.

As of now with --show-bad-code jadx is the only software that shows me at least something.


