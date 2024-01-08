#LG G4: Cannot see typed input until space keypress with stock keyboard

Owner: termux

Repo: termux-app

Labels: bug report Keyboard 

## 4Z4T4R (24 Nov 2015)

I installed your app and immediately realized i could not see what I am typing in the command prompt until I press space. No other key prints the buffer to screen. Android 5.1 on LG G4. 


## fornwall (24 Nov 2015)

Which keyboard do you use? The stock keyboard on the LG G4?


## fornwall (27 Nov 2015)

I wil debug it as soon as I have access to an LG device.

In the meantime, you could try using an alternate keyboard such as SwiftKey or Swype!


## alextz (21 Apr 2016)

On a Nexus5X the Google English board puts out keys, as is. No word-guessing, etc.

Except if the "Swype" logic is used. ("gesture keyboard" - trace between the letters of a word you want to type). In that case the word is output when a space is hit.

I would not spend a lot of time on this sort of thing. When keyboards put out words, the keyboard's logic of when to put out the word becomes very gnarly. So, it can be frustrating to second guess the keyboard when it's trying to second guess you.

Anyway, this sort of word-at-a-time output logic may be what's going on with the reported LG G4 board.


## EleRam (26 Nov 2019)

I've faced the same problem and there are duplicate in each type.
I tested GBoard without any configuration and it was OK.
this strange bug (Swiftkey bug maybe) is relay painful!


## Luk164 (04 Sept 2020)

Same issue on galaxy s5e, is there any fix?

## Grimler91 (04 Sept 2020)

@Luk164 see https://github.com/termux/termux-app/issues/686. Workaround for affected samsung devices is to add `enforce-char-based-input = true` to the [terminal settings](https://wiki.termux.com/wiki/Terminal_Settings) in `~/.termux/termux.properties`. This is possible in termux-app v0.99 (and newer).

## Luk164 (04 Sept 2020)

> @Luk164 see https://github.com/termux/termux-app/issues/686. Workaround for affected samsung devices is to add `enforce-char-based-input = true` to the [terminal settings](https://wiki.termux.com/wiki/Terminal_Settings) in `~/.termux/termux.properties`. This is possible in termux-app v0.99 (and newer).

Thx, but it looks like 
 version 0.99 is not on f-droid yet. Do you know when f droid will add that release? I would rather not reinstall the app.

## Grimler91 (04 Sept 2020)

> Do you know when f droid will add that release?

They have had build problems with the last couple of releases, see https://github.com/termux/termux-app/issues/1736.
No idea when that will be resolved unfortunately

