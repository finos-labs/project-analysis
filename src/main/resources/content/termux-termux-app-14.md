#Option to run commands at boot

Owner: termux

Repo: termux-app

Labels: enhancement 

## iexos (09 Nov 2015)

Would it be possible to run commands (like sshd) automatically after the device boots?


## gayprogrammer (11 Nov 2015)

Creating a .profile script works, or .bash_profile which takes precedence. 
You can use these bash scripts in conjunction with an app that will start Termux on boot. A quick search turned up many generic autostart apps.


## iexos (12 Nov 2015)

That is like launching your X server in your .profile, not really ideal. I could check each time if the service is running and if not, start it and exit the terminal, but it seems rather fragile.


## jarrettgilliam (19 Nov 2015)

I vote for adding cron to the repo. Not sure how feasible that is.


## fornwall (19 Nov 2015)

@iexos It's a nice idea, will think about it! Perhaps something like executing the file `$HOME/.termux/boot` if it exists at boot (so you can do anything you want there, like starting sshd)?

@jarrettgilliam There is `crontab` and `crond` available from busybox, which is pre-installed in Termux. Though you have to start crond for it to work (which you could then do with the above start-at-boot solution).


## iexos (20 Nov 2015)

@fornwall Yes that sounds like a good solution. Maybe not just a file, but a folder where you could drop scripts in, though that depends on taste as it could easily be implemented in the boot file.


## benjaminoakes (02 Jul 2016)

I really like the idea of running cronjobs in Termux, but the issue with `crond` is that it could be terminated in the background unless a wake lock is held. However, always holding a wake lock means that the CPU is always awake, even if there's nothing to do. Perhaps we can have something _like_ `cron` using the Android system [AlarmManager](https://developer.android.com/reference/android/app/AlarmManager.html)?


## vit1-irk (04 Jul 2016)

@benjaminoakes we can use external AlarmManager apps for that. But it's required to disable Termux feature `[Process completed - press Enter to close]` because that prevents job scripts to exit normally.


## benjaminoakes (14 Jul 2016)

> But it's required to disable Termux feature [Process completed - press Enter to close] because that prevents job scripts to exit normally.

That would be really nice to have sometimes. Perhaps available by setting an environment variable (for example `TERMUX_AUTO_CLOSE`) if it doesn't make sense in all situations. 

On July 4, 2016 4:05:47 AM CDT, "Viktor (vit01)" notifications@github.com wrote:

> @benjaminoakes we can use external AlarmManager apps for that. But it's
> required to disable Termux feature `[Process completed - press Enter to
> close]` because that prevents job scripts to exit normally.
> 
> ---
> 
> You are receiving this because you were mentioned.
> Reply to this email directly or view it on GitHub:
> https://github.com/termux/termux-app/issues/14#issuecomment-230242150


## Neo-Oli (14 Jul 2016)

@benjaminoakes Just as an FYI, there is a lot of discussion about this in Issue https://github.com/termux/termux-app/issues/56


## RolandHughes (06 Jun 2017)

Is service too heavy?
<pre>
roland@roland-HP-Compaq-8100-Elite-SFF-PC:~/BBB$ service
Usage: service < option > | --status-all | [ service_name [ command | --full-restart ] ]
</pre>

I'm thinking more along the lines of launching postgresql or any other needed service AT BOOT, not at login. In particular something like postgresql would start when the device powers up and any app could then write to the PostgreSQL port.

The more I poke at Android the more I can't believe anybody signed off on shipping this platform, especially on 2 in 1 and tablet computers.

## pranavg189 (15 Jun 2017)

The Termux:Boot add-on (which is currently unreleased) will be a very good solution to this problem.

## kennwhite (26 Jul 2017)

I struggled with this during my own setup on Chromebook, and while maybe a little inelegant, this works for my use case. The execution flow prevents inception-like deeply nested calls to proot, but performs a one-time hop on initial app startup so that sshd starts inside a chroot'd environment (if you don't need chroot to auto-invoke, just remove that line). I've tested 3 different (concurrent) open ssh sessions (via FireSSH and Google's NaSSH) without issues. `sshd` will only be invoked once, and subsequent calls will only invoke the normal `... sshd -D` process. Rebooting or force restarting Termux behaves as expected, with sshd and chroot autostarted. This is obviously no substitute for first-class init daemon support, and potentially risky if fat-fingered, but hopefully might help others looking for a short-term fix.

    # Create/append to:  ~/.bash_profile
    if ! pgrep -f "termux-chroot" >/dev/null ; then echo "[Starting chroot...]" && termux-chroot; else echo "[chroot is running]"; fi
    if ! pgrep "sshd" >/dev/null ; then echo "[Starting sshd...]" && sshd && echo "[OK]"; else echo "[ssh is running]"; fi
    
Note the -f flag is required because of the way the `termux-chroot` shell script works, but is not needed with sshd. If interested in the whole Termux writeup, the post is here: https://blog.lessonslearned.org/building-a-more-secure-development-chromebook/

## nathanbw (30 Aug 2017)

@kennwhite I got nested proots when I used your stanza in my .bash_profile. I changed the first conditional to `pgrep -f proot` to fix that.

A little OT: is the chroot solely for ergonomic benefit? Thanks for your article; it inspired me to get a chromebook as my primary mobile environment. Very happy with it so far thanks to Termux!

## gayprogrammer (08 Sept 2017)

Termux:Boot add-on is now out.

## rogerxxxx (22 Aug 2018)

Absolutely no need to install/start chroot/proot!  The above commentor did this as an added feature for his/her needs.  (Now I've got added libraries I don't need!)

Just use an incanatation of a if/then pgrep to first check for an already running process of the program you want to run, and if the program/process isn't already running, then execute it.  Insert/append the pgrep if/then incanatation to $HOME/.bashrc

If you break the bashrc startup causing termux to hang, swipe for the left menu, click new session and modify the new session as fail safe.  Move the bashrc (or bash_profile) to a new name and restart termux.

## rogerxxxx (22 Aug 2018)

To be more explicit, kennwhite's script snippet is designated to run two services (or programs), with the second service (or program) dependent upon the first program.  (eg. Similar to making sshd dependent upon establishing an ethernet connection.)

So the following is all you really need within bashrc, or preferably bash_profile if bash_profile works on your termux install:

if ! pgrep -f "crond" >/dev/null; then
     echo "[Starting crond...]" && crond && echo "[OK]"
else
     echo "[crond is running]"
fi

The reason why bash_profile is preferred, bash_profile is called once during initial login, versus bashrc upon each shell opening.  (Will save a few CPU cycles to use bash_profile.)

By the way, this forum's code quoting do not preserve white space well at all!  As such, just posted the above snippet within quoting to preserve end line characters.

## digimystigi (05 Sept 2018)

For anyone attempting to run Node-Red as a daemon using Termux:Boot, it would be a good idea to run the following:
termux-fix-shebang $(which node-red)

I was running into problems where node-red wasn't actually starting properly and doing some debugging found that the environment that Termux:Boot provided wasn't what node-red needed.  Node-Red references /usr/bin/env by default which gives a bad environment for Node.js.  The above command fixes that to /data/data/com.termux/files/usr/bin/env which allows it to run properly.

