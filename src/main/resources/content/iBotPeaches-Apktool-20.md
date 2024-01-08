#Error when compiling framework-miui-res.apk with apktool 1.5.0

Owner: iBotPeaches

Repo: Apktool

Labels: 

## Acid-miuipolskapl (03 Sept 2012)

Got this, which I've never seen before:
http://pastebin.com/Fwzw4EL8


## iBotPeaches (03 Sept 2012)

Can you post the apktool.yml associated with that file?


## Acid-miuipolskapl (03 Sept 2012)

I will post you the file later as i am at work. But Its standard apktool.yml for MIUI with 1 to 5 frameworks ids added. You can view this file on my github in miui_src_fix directory. 


## iBotPeaches (03 Sept 2012)

I updated the structure of apktool.yml, and that error makes it seem like some automated scripting inserted some lines where they shouldn't be.

Either way, still post it after work :p


## Acid-miuipolskapl (03 Sept 2012)

Ok this is my apktool.yml after I decompile framework-miui-res.apk: 

version: 1.4.10.f53a296
apkFileName: framework-miui-res.apk
isFrameworkApk: true
usesFramework:
  ids:
- 1

And before recompilation my script add IDs from 2 to 5 with this bash script:
        if [ ${framework_copy} == "framework-miui-res.apk" ]
            then
            echo "  - 2" >> $FOLDER_BUILD/${framework_copy}/apktool.yml
            echo "  - 3" >> $FOLDER_BUILD/${framework_copy}/apktool.yml
            echo "  - 4" >> $FOLDER_BUILD/${framework_copy}/apktool.yml
            echo "  - 5" >> $FOLDER_BUILD/${framework_copy}/apktool.yml
            fi

```
    done
```

So the file when recompile looks like this:

version: 1.4.10.f53a296
apkFileName: framework-miui-res.apk
isFrameworkApk: true
usesFramework:
  ids:
- 1
- 2
- 3
- 4
- 5


## iBotPeaches (03 Sept 2012)

Just post the raw contents of the apktool.yml :p

I know it's wrong somewhere 


## Acid-miuipolskapl (03 Sept 2012)

i don't know what you're expecting but ok, heres apktool.yml when I decompile previously builded framework-miui-res from my polish pack from latest MIUI:
http://pastebin.com/u69hd4za

And this is the file before recompilation (after decompile when my bash script puts IDs):
http://pastebin.com/spYNg3pT


## iBotPeaches (03 Sept 2012)

Then there isn't a problem. I don't think your giving me the right file. I know what happened and this isn't the problem.

Framework IDs are no longer the last part of the file. The SDK Info is, so if you just echo -2 it'll fail as that first error said.

Your still not giving me the apktool.yml thats used in the build, otherwise it would be working.


## Acid-miuipolskapl (03 Sept 2012)

Your're right. I didn't know that 1.5.0 adds SDK info in the end of apktool.yml:
http://pastebin.com/ubTzLMbJ

So what now should I do to compile MIUI framework properly? Is it still necessary to add dummy frameworks IDs into that file?
If yes, then I should look for some other linux commands to insert IDs before certain lines of code.


## iBotPeaches (03 Sept 2012)

```
                  if [ $(basename $i) == "framework-miui-res.apk" ]; then
                  sed -i '7i \
\  - 2\
  - 3\
  - 4\
  - 5' $path/d_apks/$(basename $i)/apktool.yml
```

sed -i '7i
- Edit in place
- 7th line insert contents below
- new line
  / - 2\ 
  / = start contents
  \ = end contents, to next line.

bash


## Acid-miuipolskapl (03 Sept 2012)

Can you post the code via pastebin? Github comments messes up this :)
I understand the code (using sed command) but whould like to make sure..


## iBotPeaches (03 Sept 2012)

I'll look at delaying the write, so I can control the order of contents.

As this might break existing tools (as just proved).

http://pastebin.com/BtaTqA5c


## Acid-miuipolskapl (03 Sept 2012)

Yup, this works :) Framework-miui-res has been compiled properly and rom boots without issue. Thanks.


