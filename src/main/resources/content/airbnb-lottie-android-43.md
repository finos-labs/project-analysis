#Lottie android Renders differently 

Owner: airbnb

Repo: lottie-android

Labels: 

## Nabeel-khalid (02 Feb 2017)

Hi Guys, 

I'm getting two different out comes when i export. The web one works fine but once i copy the json code into a android project the top corner becomes rounded. Before it was completely disconnected.  

[See here for the web link ](http://codepen.io/nabeelkhalid/full/egMwbJ/)

Also here is a gif of the android render

![](http://i.imgur.com/8044V2z.gif)

Thanks

## gpeal (02 Feb 2017)

@Nabeel-khalid can you attach the AE file?

## Nabeel-khalid (03 Feb 2017)

[loader_2.aep.zip](https://github.com/airbnb/lottie-android/files/750111/loader_2.aep.zip)

Here is the AE file. I'm using After effects CC 2017 on a mid 2014 macbook

## therealsalih (04 Feb 2017)

Hi Nabeel!
I think we found the issue. its something with the way Lottie is handling the first vertex on a path. It's not properly closing it up. We're looking into fixing it. In the mean time if you want a temporary work around you can just use a rectangle shape instead of a path. I tried that out and it works. Here's he ae file for that

 attached it here
[loader_3.aep.zip](https://github.com/airbnb/lottie-android/files/752427/loader_3.aep.zip)


## Nabeel-khalid (06 Feb 2017)

@therealsalih Thank you! I did try redrawing the shape. I think it maybe because the file was created in AE '16 and then i updated to AE 2017. 

