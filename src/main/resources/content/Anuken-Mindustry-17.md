#Crash after being alt tabbed

Owner: Anuken

Repo: Mindustry

Labels: 

## Raotor123 (12 Dec 2017)

The game crashed with this error after I alt tabbed and did something on my second screen.
![image](https://user-images.githubusercontent.com/25741824/33914390-3be7ec92-df9e-11e7-9613-20ba66baa546.png)
I could reproduce the error when resizing the window and moving the mouse around wildly, although this is not what I was doing when it first occurred.

## Anuken (13 Dec 2017)

I _think_ this is caused by fetching the screen size of the wrong monitor when constructing a framebuffer. Not sure how to test it, as I only have one monitor.

## Raotor123 (13 Dec 2017)

I guess this was a 1 in a million occurrence, because it didn't happen again when i switchted focus to the other monitor. At least you know it's out there:).

## Anuken (14 Dec 2017)

_Might_ have been fixed by a Framebuffer creation change in 1acc47190c827932c03fd827a98152f45509fd4e. Will keep this issue open for a while longer just in case.

