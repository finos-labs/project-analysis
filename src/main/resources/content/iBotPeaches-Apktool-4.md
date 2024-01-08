#formatted=false

Owner: iBotPeaches

Repo: Apktool

Labels: Bug 

## iBotPeaches (02 May 2012)

```
    if (ResXmlEncoders.hasMultipleNonPositionalSubstitutions(mRawValue)) {
        serializer.attribute(null, "formatted", "false");
    }
```

was being run by not working. Also, plurals/arrays never run this. Even though theres true xlif data in there. We need to add counter to fix the $1 $2 issue.

Small counter per node. Reset once done.


## iBotPeaches (02 May 2012)

Fixed the formatted false.

/home/peaches/build/rom_wip/apks/framework-res/res/values-zh-rCN/plurals.xml:79: error: Multiple substitutions specified in non-positional format; did you mean to add the formatted="false" attribute?
/home/peaches/build/rom_wip/apks/framework-res/res/values-zh-rCN/plurals.xml:79: error: Found tag </item> where </plurals> is expected

Its because the 

```
<plurals name="matches_found">
    <item quantity="other" formatted="false">第 %d 项，共 %d 项</item>
    <item quantity="one">1 个匹配项</item>
</plurals>
```

should be

```
<plurals name="matches_found">
    <item quantity="other" formatted="false">第 %1$d 项，共 %2$d 项</item>
    <item quantity="one">1 个匹配项</item>
</plurals>
```

If the attribute comes back true, when inserting text into XML. Check for the value. If so, replace all % with incremental x$ so

% becomes %1$ next,
% becomes %2$


## iBotPeaches (03 May 2012)

```
<anim name="android_slide_in_right">@android:anim/shrink_fade_out_from_bottom</anim>
<anim name="android_slide_out_left">@android:anim/slide_in_up</anim>
```

Need to look at references. Adding to remember. 


## iBotPeaches (03 May 2012)

```
<item type="array" name="android_config_virtualKeyVibePattern">@android:array/config_virtualKeyVibePattern</item>
<item type="array" name="android_config_mobile_hotspot_provision_app">@android:array/config_mobile_hotspot_provision_app</item>
```

Fixed. Testing time.


## iBotPeaches (07 May 2012)

For some reason, @null isn't passing this.

```
    <string name="config_wifi_p2p_device_type">10-0050F204-5</string>
    <item type="string" name="default_wallpaper_component">@null</item>
    <string name="config_networkLocationProvider">com.google.android.location.NetworkLocationProvider</string>
    <item type="string" name="config_geocodeProvider">@null</item>
```


## yyjdelete (06 Jul 2012)

Maybe you can use the below code to format % to %n$
(not sure if encodeAsResXmlValueExt work well for all other types as encodeAsResXmlValue)

brut.androlib.res.data.value.ResScalarValue

```
public String encodeAsResXmlValueExt() throws AndrolibException {
    String rawValue = mRawValue;
    if (rawValue != null) {
        if (ResXmlEncoders.hasMultipleNonPositionalSubstitutions(rawValue)) {
            int count = 1;
            StringBuffer result = new StringBuffer();
            String tmp1[] = rawValue.split("%%", -1);//skip "%%"
            int tmp1_sz = tmp1.length;
            for(int i=0;i<tmp1_sz;i++) {
                String cur1 = tmp1[i];
                String tmp2[] = cur1.split("%", -1);
                int tmp2_sz = tmp2.length;
                for(int j=0;j<tmp2_sz;j++) {
                    String cur2 = tmp2[j];
                    result.append(cur2);
                    if(j != (tmp2_sz-1)) {
                        result.append('%').append(count).append('$');
                        count++;
                    }
                }
                if(i != (tmp1_sz-1)) {
                    result.append("%%");
                }
            }
            rawValue = result.toString();
        }
        return rawValue;
    }
    return encodeAsResXml();
}
```


## iBotPeaches (06 Jul 2012)

Thanks. I will test with it.


## yyjdelete (06 Jul 2012)

Sorry, I miss a function, there is already a function to do that
(can be found called by ResStringValue.encodeAsResXmlItemValue())

import brut.androlib.res.xml.ResXmlEncoders;

public String encodeAsResXmlValue() throws AndrolibException {
    String rawValue = mRawValue;
    if (rawValue != null) {
        rawValue = ResXmlEncoders.enumerateNonPositionalSubstitutions(rawValue);
        return rawValue;
    }
    return encodeAsResXml();
}


## iBotPeaches (08 Jul 2012)

Merged your fix in, still some issues with <strings> with positional formatting. Will investigate more.


