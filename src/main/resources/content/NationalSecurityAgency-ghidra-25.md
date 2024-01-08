#Highlighting similar symbols (names, registers, etc.)

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Enhancement 

## Barakat (06 Mar 2019)

It would be nice if Ghidra highlights similar symbols (names, registers, etc.) in the listing and decompilation views so it is easier to track down where these symbols are being used.

Current behavior:

![capture2](https://user-images.githubusercontent.com/11032985/53866718-43516c80-4003-11e9-8cce-dc52f78bf31d.PNG)

Desired behavior:

![capture](https://user-images.githubusercontent.com/11032985/53866442-9ecf2a80-4002-11e9-9dc1-a06cbeceba07.PNG)


## marcan (06 Mar 2019)

Use the middle mouse button. The feature is there, it's just not terribly discoverable.

## computerline1z (06 Mar 2019)

And better, you could change it function to use left mouse :D

![image](https://user-images.githubusercontent.com/2786420/53881589-a6182780-4046-11e9-84a8-eb5c255e4415.png)


## theguy147 (06 Mar 2019)

Also read up in the help section about "Higlight Forward/Backward (Inst) Slice"...

Ghidra then higlights all occurences of the variables plus additional tainted variables and instructions. Pretty neat...

