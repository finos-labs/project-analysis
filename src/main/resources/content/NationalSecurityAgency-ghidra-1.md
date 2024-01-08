#Ghidra does not render correctly with GDK_SCALE set (HiDPI display)

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Bug 

## dw (05 Mar 2019)

100% perfect workaround: https://github.com/NationalSecurityAgency/ghidra/issues/1#issuecomment-480923545

-----

**Describe the bug**

On HiDPI displays on Linux, it is necessary to set GDK_SCALE= for a reasonable experience with OpenJDK apps, but setting this variable causes clipping of UI elements in every window. 

**To Reproduce**

- Acquire HiDPI machine
- Set GDK_SCALE=2 or higher
- Launch Ghidra

**Expected behavior**

Neatly scaled UI

![screenshot from 2019-03-05 23-51-13](https://user-images.githubusercontent.com/2315/53845602-98f52d00-3fa1-11e9-81b5-fc18c1e2c787.png)


**Environment (please complete the following information):**
 - OS:  Ubuntu 18.10
 - Version ghidra_9.0_PUBLIC_20190228.zip
- Dell XPS 9550 (~285 dpi)

## dw (06 Mar 2019)

I tried to find somewhere to jerry-rig a fix, but no luck yet. The clipping is clearly relative to the size of the container window, so I bet this is a single line of code somewhere that somehow is fetching unscaled dialog widths and copying the result to a scaled width

(but finding that line escapes me! looks like it's under docking/ somewhere. ./ghidra/app/plugin/core/totd/TipOfTheDayDialog.java shows the classes involved for the tip of the day dialog


## ahertz (06 Mar 2019)

Forcing the Swing look and feel to GTK+ (under Edit > Tool Options > Tool in the main window) and unsetting GDK_SCALE does slightly better, though it's still not usable.  Many of the text controls are scaled properly, but the custom controls and images are not.

![image](https://user-images.githubusercontent.com/28460149/53849575-4ce8bf00-3f86-11e9-9ba0-8400b600337b.png)


## backerman (06 Mar 2019)

Another option is to use [`run_scaled`][rs]. It works iff you change `bg` to `fg` in the last line of `ghidraRun`.

[rs]: https://github.com/kaueraal/run_scaled

## bkerler (06 Mar 2019)

See https://github.com/bkerler/ghidra_installer

Tested and verified to work with Ubuntu / Kubuntu 18.04 and 18.10.

## anticomputer (10 Mar 2019)

Having spent some time debugging this I'm starting to suspect it is an Xlib scaling issue in openjdk itself. All the window->jframe->jpanel hierarchies seem sized properly (at a quotient of the scaling factor) and _should_ be scaled up consistently, but clearly some root view port's scaled size is being used as a literal resolution (thus the clipping). 

I'm no java swing expert, but all the layout manager logic seems kosher to me as well. I dumped the actual window components, and they all seem correct and as expected throughout the hierarchy.

e.g.  on a 3200x1800 screen, and a scale factor of 2.0:
...
javax.swing.JPanel[null.contentPane,0,33,1600x833,invalid,layout=java.awt.BorderLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=]
 javax.swing.JPanel[,0,24,1600x809,invalid,layout=java.awt.BorderLayout,alignmentX=0.0,alignmentY=0.0,border=javax.swing.border.EmptyBorder@402e0506,flags=9,maximumSize=,minimumSize=,preferredSize=]
  Project Window
   javax.swing.JPanel[,2,2,1596x802,invalid,layout=java.awt.BorderLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=]
    javax.swing.JPanel[,0,0,1596x802,invalid,layout=java.awt.BorderLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=]
     javax.swing.JPanel[,0,0,1596x675,invalid,layout=java.awt.BorderLayout,alignmentX=0.0,alignmentY=0.0,border=,flags=9,maximumSize=,minimumSize=,preferredSize=]
....

Which is what what you would expect to see, as openjdk itself is responsible for scaling up the scaled down resolutions for final display, but it seems at some root component this is not happening, and thus the displayed scaled output is clipped.

I suspect it's a corner case in openjdk's Linux specific XLib scaling since Ghidra scales fine on Windows HIDPI and OSX HIDPI. Unless I missed something I did not immediately see a simple fix in Ghidra itself.


## rd0shak (28 Mar 2019)

On my laptop, adding GDK_SCALE=2 to "launch.properties" will enlarge about half of the UI text, and does nothing for resizing icons. I don't experience any clipping, everything seems to come up, but the inconsistency in text size makes the program unusable. I'm running Centos 7, Gnome 3.28.2.

## ryanmkurtz (28 Mar 2019)

Would someone with this problem mind testing Ghidra against [Amazon Corretto 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) to see if they've done anything to address it?  I don't have a setup to reproduce the problem.  Thanks!

## anticomputer (28 Mar 2019)

I'll test against Coretto in a bit and report back.

## anticomputer (28 Mar 2019)

Tested with java-11-amazon-corretto-devel-11.0.2.9-3.x86_64.rpm and the issue persists. I believe this will probably require openjdk team support once the UI test case is isolated since it's almost certainly an issue with the Linux specific xlib scaling (there's a history of this kind of thing happening with the Linux specific scaling code). This is further supported with the native OSX version scaling fine on Retina screens (not driven by the xlib scaling code). 

## ryanmkurtz (28 Mar 2019)

Ok thanks for following up.  Issue #161 reported some success switching from OpenJDK to Oracle JDK with another linux/graphics problem, so I figured it was worth a shot. 

## anticomputer (28 Mar 2019)

I did try with both oracle java 8 and oracle java 11 as well just in case, but no dice.

## talex5 (08 Apr 2019)

I found this in `support/launch.properties`. Changing it to `true` fixed the problem for me.

```
# Resolves issue with Ubuntu when running within VM
VMARGS=-Dsun.java2d.xrender=false
```

## anticomputer (08 Apr 2019)

D'oh! I can confirm that fixes the issue for me as well.

## pedrib (08 Apr 2019)

same here, it's fixed! 

## FMorsbach (08 Apr 2019)

can confirm aswell, in combination with 
`VMARGS=-Dsun.java2d.uiScale=2`
on a 3000x2000 screen running cinnamon 

## ryanmkurtz (29 Apr 2019)

I updated launch.properties to indicate that `sun.java2d.xrender` set to true may address HiDPI monitor issues on Linux.  We've had to set that property to false in the past to get Ghidra to render correctly in VM's so I wasn't comfortable with using true as a default just yet.  We might change that in the future though. 

## X-Ryl669 (03 Nov 2020)

The proposed solution does not work for me. With both `...xrender=true` and `...uiscale=2`, I'm getting the same broken interface as the OP. I've tried all 4 combinations (xrender true/false, uiscale 1/2) and none give a usable GUI (uiscale = 1 is correct but too small to be usable).

## ryanmkurtz (04 Nov 2020)

What distro are you using?

## X-Ryl669 (04 Nov 2020)

Manjaro (based on Archlinux)

## elig0n (10 Mar 2021)

`xrender` was already `true` for me when this error happened, setting `VMARGS=-Dsun.java2d.opengl=false` to `true` fixed it

