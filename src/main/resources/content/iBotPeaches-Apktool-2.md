#Switching the XML up

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## iBotPeaches (01 May 2012)

`<anim name="htc_ime_decelerate_interpolator">@anim/zzz_htc_ime_decelerate_interpolator</anim>
<item type="anim" name="htc_ime_decelerate_interpolator">@anim/zzz_htc_ime_decelerate_interpolator</item>`

It seems to be wrapping the type into the type of XML. It should store that into the type attribute and keep the item node.


## iBotPeaches (03 May 2012)

Fixed.


