#Enemy AI stuck in wall AND going wrong way

Owner: Anuken

Repo: Mindustry

Labels: 

## skybldev (08 Dec 2017)

![image](https://user-images.githubusercontent.com/30189017/33750732-f410187a-dba3-11e7-8162-15a45e51abd3.png)

'why is the enemy count still at 6???'

EDIT:
one of them got free after i killed a couple
![image](https://user-images.githubusercontent.com/30189017/33750797-64190122-dba4-11e7-9e15-1d4aaf2ced4c.png)

EDIT: 
re-loaded save, still same problem. 6 enemies left and the indicator pointing right at em
![image](https://user-images.githubusercontent.com/30189017/33750823-8cb38666-dba4-11e7-9cfd-507fc1129bab.png)

EDIT: 
what.
![image](https://user-images.githubusercontent.com/30189017/33787908-5f698bbc-dc3d-11e7-9419-c9dcaac2f2e5.png)


## Anuken (09 Dec 2017)

I'm honestly not sure how you managed to get them stuck in the blocks like that. Did there happen to be a moment of extremely low FPS, or something else abnormal? I've tried spawning 5000+ enemies before, reducing the FPS to 10 and that's never happened to me.
As for them _moving backwards_, that's an old bug where the enemies improperly select a path waypoint to follow after a save load. That _should_ be fixed now, though there might be an edge case where they still bug out. 

## skybldev (09 Dec 2017)

I simply loaded the save file and found that out. No big fps drops or anything, i just noticed that there were still 6 enemies not showing up and found out that they were the ones. I have a gif that i would like to upload to giphy but that might take a while as it is 80 something MB...

## Anuken (12 Dec 2017)

Small update: I still haven't found why it happens, but enemies now self-destruct after being still for 15 seconds. This should prevent some frustration when enemies are stuck somewhere on the far side of the map.

## skybldev (12 Dec 2017)

Awesome. 

~~although I loved how the enemies getting stuck would make the wave last indefinitely and essentially amke the wave sandbox lmao~~

Oh, and another thing is that sometimes, when i load a save, some enemies so the opposite direction of where they were going before i saved the game.

## Anuken (12 Dec 2017)

Does that still happen with the new beta/current version? _I thought I fixed that!_

## skybldev (12 Dec 2017)

Happens less now, I see. Only 1 out of like 10 or 20 enemies go backwards in some waves. Testing again soon, and attaching gifs too.

## Raotor123 (12 Dec 2017)

Can confirm, this enemy is "stuck" now for a couple of minutes.
![image](https://user-images.githubusercontent.com/25741824/33913583-b542c048-df9a-11e7-962a-ab99562d3b32.png)
(I am on commit c7c70216990a50541891a9a8fd38e3ffbd27c968) 
Edit: seems to be moving backwards (to the right, to get unstuck?). When i approach it turns around to shoot at me. 
Edit2: A couple minutes later.
![image](https://user-images.githubusercontent.com/25741824/33914195-68523bda-df9d-11e7-917d-a8245743f2df.png)


## Anuken (18 Jan 2018)

Enemies will now always explode quickly and consistently when they're stuck. I don't think there's a way to fix this issue properly without making each enemy pathfind on an individual basis, so I'm closing this issue. Will open another (more specific) issue concerning enemy AI later.

