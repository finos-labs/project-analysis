#[FR] option to read password with termux-api?

Owner: termux

Repo: termux-app

Labels: 

## dezifit (26 Oct 2015)

I'm new to termux (very nice package and Android interaction concept!) and tried to use termux-api to query a password from a script, but termux-dialog seems to be rather limited so far.

Do you think it makes sense and is possible to add such an option, for example to perform encfs or sshfs mounts controlled by scripts?


## fornwall (26 Oct 2015)

Thanks, I think that makes sense!

So would a `-p,--password` option which would set the input type to password work?


## dezifit (26 Oct 2015)

Yes, that would be excellent!

Maybe another option "-t" to specify a title (usefull for all kinds of dialogs)? Intended usage:
pw=`termux-dialog -t "Please enter your password" -p`


## sm4rk0 (26 Oct 2015)

Sorry for being slightly off-topic.
@dezifit I suppose you wrote this:

```
pw=`termux-dialog -t "Please enter your password" -p`
```

But markdown formatting messed it.

See [What is the benefit of using $() instead of backticks in shell scripts.](http://stackoverflow.com/questions/9449778/what-is-the-benefit-of-using-instead-of-backticks-in-shell-scripts)


## fornwall (27 Oct 2015)

@dezifit I've added the following options:
- -m, --multiple-lines → Use input with multiple lines (single line being the new default)
- -p, --password → Enter the input as a password
- -t, --title &lt;title&gt; → The title to show for the input prompt

Let me know if there are any problems!

You have to `apt update && apt upgrade` to get the updated `termux-api` package (version 0.9), as well as wait for the corresponding 0.5 update of the [Termux:API](https://play.google.com/store/apps/details?id=com.termux.api) app to be installed from the Play Store as an update (it should be available in a couple of hours max).


## dezifit (27 Oct 2015)

Perfect, problem solved in amazing speed. Works like a charm, thanks a lot!

@sm4rk0
Yep, you're right (altough I stick  with the backticks, as they are working almost everywhere)


