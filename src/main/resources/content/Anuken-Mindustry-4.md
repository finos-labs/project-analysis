#[Implemented][Suggestion] A few suggestions that I and many other players would really appreciate having in the game

Owner: Anuken

Repo: Mindustry

Labels: 

## skybldev (07 Dec 2017)

# First, let me get this straight. This. Game. Is. ✨AWESOME!!✨
This is my type of game, the ones where you manage an assembly line and at the same time have a real good challenge. This just completes all of that and that's why I think it's awesome. ~~also the sound effects are fantastic~~

So while I currently enjoy the game a whole lot both on Windows and Android/ChromeOS, I have a few suggestions that would make the game much more satisfying and easy to use.

**IMPLEMENTED**~~## 1. [Desktop/Mobile] A more user-freindly conveyor/conduit path creator. ⤴
Currently, you can only place conveyors and conduits one-by-one, and it usually takes a very long time to make a path, for example, from one part of *Maze* to the other. It would be really nice to have a drawing style, or a place-and-extend style for creating converyor or conduit paths, and that would save loads of time for lots of players that like to make complex networks of conveyors.~~

**IMPLEMENTED**## ~~2. [Desktop] Make it possible to use the scrollwheel to rotate converyors and conduits around. 🔄
Honestly, using the R key (or whatever key is bound to 'rotate') is not too bad and honestly I'm pretty comfortable with it. But sometimes, I think it would be more convenient to use the scroll wheel to rotate conveyors, for example, when you're making a long path and have to move around to be able to continue laying it down.~~

**IMPLEMENTED**~~## 3. [Desktop/Mobile] Put a button or toggle for one-click-deletion of towers/machinery. 💥
This comes REALLY useful when moving defenses out of a certain area or erasing a big mistake. The click-and-hold method is way too slow for deleting many things in a short time, and having a togglable hotkey/button on desktop or mobile would be great. (especially for mobile!)~~

**IMPLEMENTED**~~## 4. [Desktop] In addition to that, please put an option to rebind a key to almost all actions. 🔡
There are lots of examples of this, the most major being the weapons hotbar (for the ship) and exiting tower placement mode. Binding keys to almost all actions could save a ton of time.~~

## 5. [Desktop/Mobile} Increase zoom out view. 🔎
Currently, the only way I can oversee the whole map and what's going on is on my Chromebook, where the internal display resolution could be set really high (up to 4k on a rather small screen) and I could zoom out far enough to see what's going on in the whole map. Adding a larger view when zooming out would be really useful for a lot of players.

**IMPLEMENTED**~~## 6. [Mobile] Fix the boxes around buttons. ▶
It's really annoying when I accidentally waste 30 precious steel by accidentally placing a Heal Turret II where I don't want it to go, and the same goes for practically every tower in the game. A tap registered off 3 points could waste a lot of recources, especially for people on devices with smaller screens. I would love it if you made the boxes around buttons solid, and unable to let a tap go under it.~~

### And that's all I have to say, for now. 
I will update this a few times in the future by adding example gifs of game mechanics I suggested. But for now, Anuken, keep up the good work with your awesome game. I love it and it makes me really confident that this game will contiue to be really good seeing that you're actively working on it when you can! 😄 

## Anuken (08 Dec 2017)

**1)** I'm planning to solve this by allowing the player to hold the mouse while placing in a line (Desktop) and adding special multi-place mode (Mobile). 3.0 is a bit late already, so I'd expect this to be done in time for.. 3.1?

**2)** Just implemented this. Since it conflicts with scrollwheel zooming, that's now done with _ctrl+scrollwheel_.

**3)** Planned! I was thinking on making it an area-selection delete tool; most likely for 3.1 as well. I'll need to add some sort of tool menu for these.

**4)** Those already have (hidden) keybindings: 1-9 for the weapons, right-click to deselect a tower. But, you're right, I should make them rebindable.

**5)** This one is tricky. Due to the pixel filter, I have to zoom out in whole numbers (2x scale, 3x scale), etc, where the default 4x. If I increase the zoom-out constraints, it'll be available up to 1x scale, which is relatively _huge_ and breaks smaller maps like Spiral and Grassland, since the camera can see out of bounds. There's also the problem of lag: most mobile devices I've tested can't draw tiles very well, and start dropping frames very fast once you get to lower zoom levels; I can definitely see users accidentally zooming out too far, and getting annoyed as their game slows to a crawl. Maybe a minimap could help instead?

**6)** Should be fixed now! (on GitHub, anyway)

~~I'm glad you appreciate my professional sound effects that definitely did not take 5 minutes to make~~


## skybldev (08 Dec 2017)

1) Got it
2) didn't know that lol.
3) Okay
4) ...nor that
5) ahh okay. Maybe you could have a process that loads all the tiles in the map, reduces the graphics load, displays and updates all the tiles in a separate square container, and use that as a frontend for the background process. Also, i've experienced that map-breaking thing on my chromebook. The stylus dropped on the screen and simulated a finger moving from one point to another point on the screen, thus making the camera go out of bounds and making me unable to play.
6) I see, lol. 

okay now to more questions ~~sorry~~
1. does Mindustry use multithreading?
2. how do i pack the game into an executable and run the builds that are fresh from the vine?

And as you may have noticed recently, I've made a fork of the game, and am currently revising the README lol. In there, I have images as links to downloads, and would like to know if I could host the game files on Gdrive, or upload the files to GitHub, to make direct download links.

And one more thing, I saw your reply to my post on the itch.io page, and I'm gonna be starting on the Wiki tonight, maybe add like 10 pages b4 i go to bed. 

Many thanks, Anuken 😄 

## Anuken (09 Dec 2017)

**1.** Nope. It's really hard to implement multi-threading in a 2D game like this, not to mention the fact that HTML5 doesn't really support threads.

**2.** I assume you have the Java Development Kit and Java installed, and are using Windows. Open the command line in the root Mindustry folder, and do `gradlew.bat desktop:run` to run the game. To build, do `gradlew.bat desktop:dist`; the output JAR file should be located in `Mindustry/desktop/build/libs/desktop-1.0.jar`.

However, if you want to distribute it as an .exe in a zip (like it is on itch.io), that's a much more complex process-- that's probably not necessary right now.

**3?)** Yeah, improving the readme is a good idea-- but why host the downloads when you can link to itch.io? I'll have to maintain downloads for two different locations-- and wouldn't most people on Github prefer to build the latest version themselves, too?

## skybldev (09 Dec 2017)

I do have it but it tries to download a gradle file which takes forever... 
![image](https://user-images.githubusercontent.com/30189017/33792015-2b7cf998-dc64-11e7-86ed-9e96e5cbe0af.png)
Also for itch.io, I can't link to direct download links like the ones specific for Windows, Mac etc.
If i hosted the files on GDrive or Mega I could get the latest files from the Itch.io page then up it to MEGA then update the links.

But you do have a point there, people on GitHub would rather build the game themselves, and in addition to that, more people prefer to just use itch.io to dl the game. Just linking to itch.io is a good idea that'll save me time.

EDIT: okay, gradle finished downloading, along with all of the dependencies. It errored because i found out that i in fact did not have JDK installed, but only JRE. 🤦‍♂️  Downloading it now. Thanks for the help! 😄 

EDIT:
okay i installed JDK, but it still is spitting out errors...
![image](https://user-images.githubusercontent.com/30189017/33792673-4c9b4b6a-dc74-11e7-860c-49c29da71fa8.png)


## Anuken (09 Dec 2017)

Looks like you installed JDK 9 instead of JDK 8-- sorry, I forgot about that. See if it works if you install JDK 8 and JRE 8.

## skybldev (09 Dec 2017)

Gotcha. I'll try to install it on my desktop later today. Meanwhile, I'll keep working on the Wiki.

## skybldev (10 Dec 2017)

I keep getting this error: `ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.`

I've installed JRE and JDK 8, and have the Mindustry root in my Github Desktop folder. (C:\Users\Owner\Documents\GitHub\Mindustry) What's wrong? :s

## Anuken (10 Dec 2017)

Looks like you need to set the `JAVA_HOME` system variable. [This](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/index.html) should explain how to do it.

## SaphireLattice (11 Dec 2017)

Few ideas that I would love to see added as well:
- Possibility (on desktop) to choose if player is going to control a player character, like it is right now, or control a camera, like on mobile. 
- Minimap, especially on mobile. It gets rather awkward to move around when you got a lot of defenses all over the map in small spots.

Also, some kind of sorter would be awesome to have, but that is either adding some kind of GUI, or tons of special blocks, which doesn't seems to fit the game. :c

## Anuken (30 Dec 2017)

Closing, as all of this is now on the TODO.

