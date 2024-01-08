#Doesn't load

Owner: Anuken

Repo: Mindustry

Labels: 

## erikbsap (07 Oct 2017)

Hi,

nice idea. Seems like Factorio + Tower Defense, right?

However, besides audio the game doesn't load in Chrome on Win10 playing on [itch.io](https://anuke.itch.io/mindustry), with this error:

```
Uncaught Error: java.lang.RuntimeException: com.badlogic.gdx.utils.GdxRuntimeException: Couldn't flush preferences
    at V$b.J$b [as OR] (html-0.js:10770)
    at V$b.M$b [as QR] (html-0.js:10770)
    at V$b.R$b (html-0.js:7774)
    at new V$b (html-0.js:1910)
    at Gd (html-0.js:10087)
    at Ld (html-0.js:9116)
    at Jh (html-0.js:9601)
    at Vg.Yg [as Xc] (html-0.js:10761)
    at Tg.Ug [as Uc] (html-0.js:10761)
    at XMLHttpRequest.eval (html-0.js:7237)
    at xpe (html-0.js:4676)
    at Ape (html-0.js:7213)
    at XMLHttpRequest.eval (html-0.js:7530)
J$b @ html-0.js:10770
M$b @ html-0.js:10770
R$b @ html-0.js:7774
V$b @ html-0.js:1910
Gd @ html-0.js:10087
Ld @ html-0.js:9116
Jh @ html-0.js:9601
Yg @ html-0.js:10761
Ug @ html-0.js:10761
(anonymous) @ html-0.js:7237
xpe @ html-0.js:4676
Ape @ html-0.js:7213
(anonymous) @ html-0.js:7530
XMLHttpRequest.send (async)
Lg @ html-0.js:8378
Kg @ html-0.js:9610
Ch @ html-0.js:10080
Gh @ html-0.js:10761
Sg @ html-0.js:10761
(anonymous) @ html-0.js:7237
xpe @ html-0.js:4676
Ape @ html-0.js:7213
(anonymous) @ html-0.js:7530
XMLHttpRequest.send (async)
Ng @ html-0.js:8551
zh @ html-0.js:6710
Jd @ html-0.js:10760
onready @ html-0.js:9729
P @ soundmanager2-jsmin.js:91
(anonymous) @ soundmanager2-jsmin.js:92
setTimeout (async)
S @ soundmanager2-jsmin.js:92
Z @ soundmanager2-jsmin.js:108
za @ soundmanager2-jsmin.js:110
na @ soundmanager2-jsmin.js:99
la @ soundmanager2-jsmin.js:104
Q @ soundmanager2-jsmin.js:111
K.beginDelayedInit @ soundmanager2-jsmin.js:36
Wh @ html-0.js:9729
Cd @ html-0.js:10368
ZXe @ html-0.js:5551
g @ html-0.js:9288
xpe @ html-0.js:4676
Ape @ html-0.js:7213
(anonymous) @ html-0.js:7530
QXe @ html-0.js:9289
(anonymous) @ html-0.js:10779
(anonymous) @ VM185:1
m @ html.nocache.js:9
(anonymous) @ html.nocache.js:10
d @ html.nocache.js:6
(anonymous) @ html.nocache.js:7
setInterval (async)
l @ html.nocache.js:7
A.html.onScriptDownloaded @ html.nocache.js:10
(anonymous) @ 2B932F8521E9F78E8A19BEEE95AF744D.cache.js:1
```

Also with uBlock Origin activated a second error occurs:

```
hexagon-analytics.com/images/293692.gif?bk=82f227d0c5&tm=87&r=183443513&v=101&cs=UTF-8&h=anuke.itch.io&l=de&S=5a38969c7d937009fc522d5eb9431b1e&uu=dabce58a-c419-457a-8962-2ca798bcb8e1&t=Mindustry%20by%20Anuke&u=https%3A%2F%2Fanuke.itch.io%2Fmindustry&rf=https%3A%2F%2Fitch.io%2Fgames%2Fhtml5&ua=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F61.0.3163.100%20Safari%2F537.36&nm=5&mh=46a0658d23b064a106c0b0dbeb81ecf0&np=4&ph=3b66f798cfdf798e8b4cc42cd710eaaa&sh=864&sw=1536&cd=24&p=Win32&to=-120&d=60&z=z:1 GET https://hexagon-analytics.com/images/293692.gif?bk=82f227d0c5&tm=87&r=183443513&v=101&cs=UTF-8&h=anuke.itch.io&l=de&S=5a38969c7d937009fc522d5eb9431b1e&uu=dabce58a-c419-457a-8962-2ca798bcb8e1&t=Mindustry%20by%20Anuke&u=https%3A%2F%2Fanuke.itch.io%2Fmindustry&rf=https%3A%2F%2Fitch.io%2Fgames%2Fhtml5&ua=Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F61.0.3163.100%20Safari%2F537.36&nm=5&mh=46a0658d23b064a106c0b0dbeb81ecf0&np=4&ph=3b66f798cfdf798e8b4cc42cd710eaaa&sh=864&sw=1536&cd=24&p=Win32&to=-120&d=60&z=z net::ERR_BLOCKED_BY_CLIENT
Image (async)
K @ s.js:17
(anonymous) @ s.js:23
l @ s.js:17
setTimeout (async)
L @ s.js:18
M @ s.js:18
_trackPageview @ s.js:123
I @ s.js:24
(anonymous) @ s.js:124
(anonymous) @ s.js:124
```

and FB fails to load, but that is more like a feature.


I wouldn't be surprised if Java is simply deactivated, which is quite normal in 2017.

## Anuken (07 Oct 2017)

From the first log, it seems to be failing to save the preferences to your browsers' local storage. Maybe you have an extension that's blocking it somehow? 
Seeing as this error is being thrown from the back-end of the library I'm using, there isn't much I can do besides catch the error and disable preferences altogether.

...as for the second error, I have no idea what the problem is. Seems to be an issue with itch.io's site rather than my game's code, although it's very hard to tell, since the problematic source files are obfuscated.

It's also worth mentioning that the HTML5 version of Mindustry _does not use Java_; it uses a tool (GWT) to transpile Java code to JavaScript-- no Java applets or plugins are used.

For now, I would recommend using the PC/Linux/Mac versions instead.


## erikbsap (08 Oct 2017)

I'd say all library errors should be caught and handled and the user should only face an exception if there's really no way to handle it in code. In this case giving the user a warning that his data is lost with closing the tab should be enough, right? The game itself should not be blocked by having no persistent storage.

## Anuken (08 Oct 2017)

Alright, a warning should be displayed now, although there's no way I can test to be absolutely sure. I'll upload the fix to itch.io soon.

## erikbsap (09 Oct 2017)

Okay, I see the commit 0decc18d76b45f1e0519f22771a5e196b1c9fca4. Thanks. I can try again after it's uploaded.

Btw, in your commit message you can also write #3 and then github will automatically add links between commit and github issue number three for you.

## Anuken (14 Oct 2017)

Should be live now. This took a while to finish, since I wanted to implement a tutorial in the same release.

## erikbsap (16 Oct 2017)

I could confirm it works, even with uBlock Origin activated. The notification is also there and understandable. Thanks!

