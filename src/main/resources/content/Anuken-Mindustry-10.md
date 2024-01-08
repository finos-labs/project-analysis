#Array out of bounds exception, Block.java

Owner: Anuken

Repo: Mindustry

Labels: 

## Raotor123 (11 Dec 2017)

Occurs when I place a router/tunnel on a full conveyer belt.
![image](https://user-images.githubusercontent.com/25741824/33847783-012f9e94-deac-11e7-8ee5-2d29fc6aea3f.png)


## Anuken (11 Dec 2017)

Moving the conversation here, it looks like an overflow. Not sure what's causing it, but I added a few small safeguards in there to make sure it doesn't happen again. As for the tunnels, that _was_ intentional initially, but not particularly good design on my part. I've changed it, and fixed the router tunnel bug as well.

## Raotor123 (11 Dec 2017)

Good work! Nice to see you so active.

