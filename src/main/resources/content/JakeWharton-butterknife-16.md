#Proguard configuration

Owner: JakeWharton

Repo: butterknife

Labels: 

## niqo01 (27 Mar 2013)

I had to add the proguard conf to make it work:
-dontwarn butterknife.Views$InjectViewProcessor

Do you think it would be better to move the InjectViewProcessor class in a separate project "butterknife-compiler" like dagger-compiler?


## JakeWharton (27 Mar 2013)

Pro guard is for the generated code, not the annotation processor. Separating it would have no effect on the behavior you are seeing.

Dagger is separate for other reasons. This library is a single class, 200-line file. If pro guard strips the annotation processor itself then it is doing its job correctly since that is only used at compile-time.


## JakeWharton (27 Mar 2013)

Ah I just reread what you wrote and understand what you're saying. I'll update the pro guard config with the warning suppression. I don't want to split because there's no benefit.


## niqo01 (27 Mar 2013)

Make sense.
Thanks for this lib.


## JakeWharton (28 Mar 2013)

Updated.


