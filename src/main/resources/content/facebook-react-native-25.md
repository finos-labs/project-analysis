#[Orientation] Re-Layout on orientation change

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## nick (01 Feb 2015)

How should I re-layout the page when the orientation of the device changes? I noticed none of the demo apps seem to support this yet. If I refresh the app in a different orientation it lays out fine though.


## nihgwu (08 Nov 2016)

@uc-hus You can just disable the landscape orientation via configuration, and if you don't like RN you can just drop it


## uc-hus (08 Nov 2016)

Why? It's required.

Thanks & Regards
_Mohammed HUSAIN_
Senior Apps Developer, uCertify
+91 9919475253

On Tue, Nov 8, 2016 at 9:49 AM, Neo notifications@github.com wrote:

> @uc-hus https://github.com/uc-hus You can just disable the landscape
> orientation via configuration, and if you don't like RN you can just drop it
> 
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> https://github.com/facebook/react-native/issues/25#issuecomment-259041642,
> or mute the thread
> https://github.com/notifications/unsubscribe-auth/ALoZ1HAFoyHADU42xbtnwUqSgB7GtMRhks5q7_hQgaJpZM4DaIiJ
> .


## mmmulani (08 Nov 2016)

@uc-hus are you handling onLayout events to setState or otherwise cause a re-render()? Compare your app to the UIExplorer which isn't having any of these issues


## linonetwo (08 Nov 2016)

@uc-hus AFAIK  react-native-menu-button solve its orientation problem by some workaround, you may take a look.

And the workaround I tried before is use Dimention.weight as height and use height as weight.


## uc-hus (08 Nov 2016)

No, I'm not agree with you because I have resolved this issue without setState
and re-render().

[image: Inline image 1]
[image: Inline image 2]

Thanks & Regards
_Mohammed HUSAIN_
Senior Apps Developer, uCertify
+91 9919475253

On Tue, Nov 8, 2016 at 12:03 PM, lin onetwo notifications@github.com
wrote:

> @uc-hus https://github.com/uc-hus AFAIK react-native-menu-button solve
> its orientation problem by some workaround, you may take a look.
> 
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> https://github.com/facebook/react-native/issues/25#issuecomment-259057144,
> or mute the thread
> https://github.com/notifications/unsubscribe-auth/ALoZ1LjzjJAFFyPIbAWRqPFIVrgBt7mfks5q8Be1gaJpZM4DaIiJ
> .


## jayshah123 (02 Dec 2016)

If you have fixed height and/or fixed width for your view,
chances are onlayout won't be called for that particular view after rotation, 
but onLayout for views with flex:1 does get called when checking as of 0.35 (on android atleast)

## ThaJay (11 Jan 2017)

> uc-hus commented on 8 Nov 2016
> 
> No, I'm not agree with you because I have resolved this issue without setState
> and re-render().

What was your fix? (the images you posted do not work)

I will try and listen for `onLayout` or `namedOrientationDidChange` now and change state. My components seem to be good apart from my navbar then reverts to its initial state instead of remaining in the state I gave it (with custom text and / or custom button icon + handler) so it needs to re-render.

edit: I just had to remove some workarounds, static sizes in my case, no need to do obscure stuff.

## uc-hus (28 Jan 2017)

Can you pease provide a example


Thanks & Regards
*Mohammed HUSAIN*
Senior Apps Developer, uCertify
+91 9919475253




On Sat, Jan 28, 2017 at 2:41 AM, Martin Koo <notifications@github.com>
wrote:

> We had this issue on iOS because we were setting height/width using
> Dimensions.get in a StyleSheet. Switching to inline styles fixed the
> problem.
>
> —
> You are receiving this because you were mentioned.
> Reply to this email directly, view it on GitHub
> <https://github.com/facebook/react-native/issues/25#issuecomment-275775472>,
> or mute the thread
> <https://github.com/notifications/unsubscribe-auth/ALoZ1Jizto9CH1UWN33l2euEUN0KTX8nks5rWl2PgaJpZM4DaIiJ>
> .
>


## mkoo21 (28 Jan 2017)

`flex:1` makes a component resize to take up all available space within its container, so if you set it on your root component it will resize to take up the whole screen on rotation. If you need to resize child views according to the new dimensions you can set `onLayout` for that view to capture that event and trigger a rerendering. 
If you use something like redux you can easily write those dimensions to your app state and use them as props for any components that need to be responsive (I personally prefer this over Dimensions.get).
Here is an example that draws a red rectangle that fills the top-left quarter of the screen:
```
import React, { Component } from 'react';
import {
  AppRegistry,
  Dimensions,
  StyleSheet,
  View
} from 'react-native';

var app = React.createClass({
  getInitialState() {
    return {
      pageHeight: Dimensions.get('window').height,
      pageWidth: Dimensions.get('window').width
    }
  },
  getNewDimensions(event){
    this.setState({
      pageHeight: event.nativeEvent.layout.height,
      pageWidth: event.nativeEvent.layout.width
    })
  },
  render() {
    return (
      <View
        style={styles.page}
        onLayout={this.getNewDimensions}>
        <View style={[styles.rectangle, {
          height: this.state.pageHeight / 2,
          width: this.state.pageWidth / 2
        }]}/>
      </View>
    );
  }
});

const styles = StyleSheet.create({
  page:{
    backgroundColor: 'blue',
    flex:1
  },
  rectangle:{
    backgroundColor: 'red'
  }
});

AppRegistry.registerComponent('app', () => app);

```
Of course, if you try to set `onLayout` on a view with fixed dimensions nothing interesting will happen. Dimensions.get('window') seems to return fixed numbers and set them, so if you use that you will need to force the component to rerender on rotation. 

## vspedr (02 Feb 2017)

@uc-hus I'm having the same issue that you reported [here](https://github.com/facebook/react-native/issues/25#issuecomment-258066776), but it's  not clear what you did to solve it. The images in your other comment don't show up. Could you please share your solution?
Edit: on RN 0.32.0

## qrobin (10 Apr 2017)

@mkoo21 we can also define width and height outside of class and change them `onLayout` to have access to that values in styles.

## tiendq (22 May 2017)

@mkoo21 I got screen width changed in the onLayout event but view isn't re-rendered with new `this.state.pageWidth` :(

## mkoo21 (22 May 2017)

@ababba15 If you mean access them from `StyleSheet.create()`, you could access them but unless it has changed recently, the point of a stylesheet is that it's calculated once and reused, so you wouldn't be able to change the values dynamically. I think it's just easier to use state since changing state automatically triggers a rerender.

@Tiendq Is this an issue you had with my example? Are you setting style through `this.state`, or are you using a stylesheet?

## qrobin (22 May 2017)

@mkoo21 maybe it not too cool, but I can define width and height outside the class like this:

``` javascript

let {width, height} = Dimensions.get('screen');

class Peace extends Component {
    constructor() {
        super();
        this.state = {
            width,
            height
        };
    }

    _handleLayout = event => {
        this.setState({
            width: event.nativeEvent.layout.width,
            height: event.nativeEvent.layout.height
        });
    }

    render() {
        width = this.state.width;
        height = this.state.height;
        /* this should re-render our component and assign to outer scope vars */

        return (
            <View style={styles.container} onLayout={this._handleLayout}>
                ...
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        width,
        height
    }
});

```

It should work (if here is no any typo). Why like this? Because I prefer to avoid mixing inline and StyleSheet style. As for me, `style={[styles.container, { width, height }]}` looks more ugly..

Or even better(?) -- to write few functions instead of class.

But I can be wrong, I'm not too experienced, so let me know.. 
Peace, guys : )

## mkoo21 (22 May 2017)

@ababba15  I added `backgroundColor = 'red'` in styles to test your code. It does not respond to rotations for me. StyleSheets are initialized once and cannot be changed dynamically.

![screenshot at 2017-05-22 18 25 55](https://cloud.githubusercontent.com/assets/12832801/26330881/26df647e-3f1c-11e7-92c7-6d4ac9921ef4.png)
![screenshot at 2017-05-22 18 26 03](https://cloud.githubusercontent.com/assets/12832801/26330887/28fbccb6-3f1c-11e7-8b53-1d7f3e7ccd94.png)



## tiendq (23 May 2017)

@mkoo21 I used inline style e.g. {{ width: this.state.width }} but it doesn't work.

## qrobin (23 May 2017)

@mkoo21 ok, I will check it today. I made it working somehow, just need to try again.

## mkoo21 (23 May 2017)

@Tiendq can you upload code or reproduce the issue with a minimal example so we can see what's wrong?

## tiendq (24 May 2017)

@mkoo21 Never mind, sample code works now, I think it's my app's issue with nested views and FlatList view. Thanks.

## rsp8055 (16 Mar 2018)

UI is perfect when it is in PORTRAIT mode no need to scroll even. But as you get to change it to the LANDSCAPE mode UI is ok here but need to use <ScrollView /> now!? So, if I use scroll view here then UI is in this mode but now UI gets affected in another mode. What should do for making the UI working properly in both the mode?

I as worked so far and got to know that when I use any background image and flex it then only orientation problem arrives.

Here is the code for this production :

` <View     style={{ flex: 1, justifyContent: 'space-between' }}
                  onLayout={this.getNewDimensions}>

                <HeadImage />

                <View style={inputContainerStyle} >
                    <TextInput />
                     <View>
                            <Image source={*****} style={searchStyle} />
                     </View>
                </View>

                <View style={buttonContainer}>
                    <ButtonContainer />
                </View>

                <FootImage />

</View>`

Thanks in ADV.

