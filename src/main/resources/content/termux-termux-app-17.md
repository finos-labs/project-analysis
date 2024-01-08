#Option to show bold text in bright color

Owner: termux

Repo: termux-app

Labels: 

## 0xallie (13 Nov 2015)

Some programs like htop show bold black text on black background, and it's impossible to see, so it would be nice to have an option to display bold text in bright color.


## fornwall (19 Nov 2015)

Thanks for the feedback!

Do you think bold-as-bright needs to be an option (as opposed to always doing it)?


## fornwall (19 Nov 2015)

More information: https://github.com/altercation/solarized/issues/90


## 0xallie (22 Nov 2015)

It's good to have an option, I'm still not sure what's the "correct" way, but I usually use bold as bright because of htop, for one.


## fornwall (24 Nov 2015)

Displaying bold text in bright colours was fixed in 36cc010a87c1cbc96f504540f1418c3c5fde96e5, which will be available in an updated version 0.20 in Google Play in a couple of hours max.

It was made non-optional for now, let's see if anything motivates making this optional later. Thanks for reporting the issue!


## joshyrobot (14 Jul 2017)

As somebody who uses a base16 theme, I would greatly appreciate it if this was made optional. Using "bright" colors when displaying bold text only makes sense if the theme being used has "bright" colors to begin with. I completely understand how it could be useful to somebody, so I think the best solution is a simple option. Maybe this could go in the styling addon?

