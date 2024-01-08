#Theme / Style Configuration Support? (Dark Mode)

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Enhancement Feature: GUI Feature: Accessibility 

## SocraticBliss (06 Mar 2019)

**Is your feature request related to a problem? Please describe.**

I understand that it's possible to Invert Colors to have a sort of semi-Dark Mode....

Main Window ->Edit->Tool Options->Tools->Use inverted colors

But it would be nice to have a few sets of pre-configured color schemes that we can switch back and forth between.

**Describe the solution you'd like**

Something along the lines of Notepad++'s Style Configurator might be nice, along with a preview of how it will look?



## jonpalmisc (10 Mar 2019)

Adding support for the Darcula LAF would be awesome - open source and available [here](https://github.com/bulenkov/Darcula).  Already used in JetBrains IDEs and Netbeans, so it will be familiar to users and it has been proven to work in large applications.

## elliiot (16 Mar 2019)

I've made a dark theme, there is a script to configure your code_browser.tcd : https://github.com/elliiot/ghidra_darknight

## agnosticlines (27 Mar 2019)

It'd be awesome to see the next release allow the graph background color to be changed, that's the one thing that's breaking the dark theme experience currently.

## agnosticlines (27 Mar 2019)

Also this is a pretty stellar piece of work: https://digmi.org/2019/03/26/ghidracula/

## osardar-zz (29 Mar 2019)

> lso this is a pretty stellar piece of work: https://digmi.org/2019/03/26/ghidracula/

Agreed, unfortunately `GHIDRACULA` doesn't work with `9.0.1` due to the namespace typo [Issue #7] having been resolved. Hoping for a quick fix or GitHub repo from digmi soon...

## agnosticlines (07 Apr 2019)

@osardar Might be worth making a repo for it until they decide to post it? Don't want this to become another ida-toolbag scenario :P

## osardar-zz (10 Apr 2019)

I briefly played around with rebuilding `Docking.jar` recently but wasn't successful- I need to get the gradle build env setup and take another stab at it. I'll link back here if I succeed in the near future.

## ryanmkurtz (25 Apr 2019)

Ghidra's out-of-the-box "dark theme" will continue to be the inverted color scheme for the foreseeable future, but it looks like the community is providing great solutions...keep up the good work!

## agnosticlines (11 May 2019)

Hey, sorry to reopen this @ryanmkurtz, but I've got the Darcula patch to work on 9.2. Not sure how to proceed, is this something that can even be added/would be accepted into the program? It looks much nicer than the default theme, although I'd recommend leaving the inverted scheme in still.

I've had to patch out some of the IP checks since I don't understand the manifest format, providing I can fix those, would you like me to make a pull request? Or should I keep these in a seperate repo

Here's a screenshot (on Linux) to gague interest from the developers 
![2019-05-11-23:16:58](https://user-images.githubusercontent.com/23334509/57575492-f646a800-7442-11e9-8ab7-3a8244aa1f33.png)

The biggest issue I'm having atm is the fact Darcula isn't supported anymore really, if anyone has any recomendations for a lookandfeel plugin that looks.. less terrible, let me know 

## pabx06 (02 Oct 2020)

take a look :
https://github.com/pabx06/buildghidra
https://github.com/pabx06/buildghidra/tags

![image](https://user-images.githubusercontent.com/4013370/94881045-05c5f700-0465-11eb-9261-1da8faa98956.png)


## pedrib (02 Oct 2020)

Looks good, but why not make it a plug-in instead? Much easier to manage.


## pabx06 (31 Oct 2020)

I dont know how to it with a plugin... many variable are Colors field with private scope... 
Somme modification involves just commenting set background and setforground methods to use the dark look and feel also i like to run latest master branch master

## pedrib (01 Nov 2020)

BTW guys it seems quosego deleted his account and repo. I had a copy of his plugin, so I put it in my repositories:
https://github.com/pedrib/ghidra.hues

All credit goes to him, I haven't changed a thing!

## agnosticlines (26 Jan 2022)

Sorry to bring this thread back but is there any plans for a native theming engine or support @ryanmkurtz.

The solutions in this repo are out of date now and require significant amounts of work to bring up to date with the new builds, and maintaining them.

For example: https://github.com/roblabla/GhiDracula-Ext by @roblabla gets a good part of the way there, but it requires using bytedude to hot patch JVM emitted code, and it only affects the “LookAndFeel” rather than the entire UI experience.

I understand it was developed in an environment where people are used to those sorts of Java GUIs and where a nice theme/UX wasn’t a primary goal, but if you look at IDEs, not many people use the default themes on IDEs or text editors and I think Ghidra is the same.

Ghidra is an excellent tool and as much as we may not want to admit it, one of the reasons people choose other programs is in part the UI/UX, I know of about 4 people who want to use Ghidra but can’t because there’s no dark theme (and I don’t count inverted mode as a dark theme)

I understand developer time is limited but imo this is an important part to get Ghidra to mass adoption, is there any way the community could sponsor this feature? I would be happy to donate to developers who would implement this, not an insignificant amount of money either, that’s how important I think this is, it’s also an accessibility issue sadly.

I totally understand a response might be “why don’t you implement it yourself” which my response is, I have, but it’s not nearly as easy to update, edit, etc than a native theme engine would be, there’s a dozen places to change various theme settings even per toolbox item, then there’s the global Java LaF which affects some components but not others, and plugins will immediately break.

I asked a few years ago and the [response](https://github.com/NationalSecurityAgency/ghidra/issues/632#issuecomment-496667748)  from @dragonmacher was “we’re looking at doing this ourselves” when I offered my patch set/plug-in, but nothing seems to have come from that, is that still the plan?

Also just to be clear I don’t mean to come off as criticising, I’m not, I’m just concerned maybe this request has gotten lost internally or maybe the value of it wasn’t understood outside of the NSA, you’ve made an amazing tool and open sourcing it is incredible, it just needs a few comfort features to be able to eclipse the bigger players such as IDA, your decompiler is already far better for most things, especially embedded.

## pedrib (26 Jan 2022)

@agnosticlines my version of ghidra.hues works out of the box with the latest Ghidra:
https://github.com/pedrib/ghidra.hues

## agnosticlines (26 Jan 2022)

> @agnosticlines my version of ghidra.hues works out of the box with the latest Ghidra:
> https://github.com/pedrib/ghidra.hues

Oh that’s super cool! I only tried the original, it still doesn’t change the toolbox themes etc, which a more cohesive first party theme manager might provide, I’m gonna check this out though, thank you!

hues is limited to just RGB colours though right? It can’t change the actual lookandfeel of specific elements?

## roblabla (26 Jan 2022)

> and it only affects the “LookAndFeel” rather than the entire UI experience.

I know there's a few things ghidracula-ext doesn't stylize, but none of them are inherently impossible to do. The main one, the decompiler/disassembler view, is intentionally not styled because ghidra already has a theme system for it. I could override the defaults if the user didn't provide a theme (and it's something I've been meaning to do, but haven't found the time for yet). The biggest drawback of this approach is that it requires a bit of maintenance time to keep it working across ghidra updates, and I haven't been very good at putting that work in X). 

## pedrib (26 Jan 2022)

@agnosticlines my recommendation is to change the look and feel to "Metal", turn on inverted colors, then use the ghidra.hues plugin to pick the darker color. It works very well for me for years, and while IDA's dark themes are more polished, there's little practical difference for day to day usage.

The fonts on the other hand... Java font rendering is terrible, at least on Linux. That definitely needs an improvement, and it can be improved, since JetBrains IDEs render fonts very nicely in Linux.

## TheEtwolf (30 Apr 2022)

Would be nice if this wasn't Windows centric.......bummer. I'll have to try and sort it on Mac OS. Still thank you for your work.

## dragonmacher (17 Nov 2022)

Ghidra now includes a Theme feature.   

Fixed by  e657a701416877f7a77b635662d30cfc25f1b5f0

