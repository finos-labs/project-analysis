#Horizontal scrolling on LineChart (not drag), Popup on click

Owner: PhilJay

Repo: MPAndroidChart

Labels: enhancement 

## tieubao (10 Jul 2014)

Hi PhilJay, your library is too great. But i think it will perfect I think it still lack of some features to be perfect. I suggest that you implement 2 features:
- We can scroll the chart in horizontal orientation, dragging is too slow.
- When we click on a point, it can popup a mini balloon to show the value.

What do you think about them?


## PhilJay (10 Jul 2014)

Thank you for your input. 

As a matter of fact, the most recent commit on the **experimental branch** already supports showing a custom popup View when selecting a value. The class that needs to be used for that is called `MarkerView`. Simply extend that class and you can provide any layout you want to be displayed when selecting values.

Concerning the scrolling feature: This will very likely be part of future improvements made to the library, once all other known issues are fixed.

Regards,
Philipp


## tieubao (10 Jul 2014)

I see, I'm the one waiting for it :100: 


