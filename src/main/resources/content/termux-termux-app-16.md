#Closing virtual keyboard with back button

Owner: termux

Repo: termux-app

Labels: 

## 0xallie (13 Nov 2015)

Termux does not seem to allow you to close the virtual keyboard with the back button, even though the icon turns into a downwards-pointing triangle. This is annoying in apps that take control of the mouse such as htop.


## 0xallie (13 Nov 2015)

I figured out I can close it from the menu if I swipe in from the left side of the screen, but this would be still nice.


## fornwall (13 Nov 2015)

The back button is now mapped to sending the Escape key.

To close the keyboard for apps such as htop/vim, you may pull out the menu from the left and press the `KEYBOARD` button (see attached screenshot).
![hide](https://cloud.githubusercontent.com/assets/277251/11144429/d9064dc4-89fc-11e5-9a00-29dd124bc0d5.png)

It's a bit clunky, but the reasoning for the current behaviour is that the cases you really want to hide the keyboard are rare, especially compared to how often you want to press escape.


## 0xallie (13 Nov 2015)

I see.


## rafaeln (28 Jan 2016)

Now termux by default behaves standardly, with the triangle retracting the keyboard instead of sending the escape key. I like the old behavior better, with the triangle sending the escape key and a touch on the screen hiding the keyboard. I've managed to get the triangle=esc behavior back by setting a termux.properties file, and I am wondering if I can also set the single touch on the screen to hide the keyboard. Is there a way?


