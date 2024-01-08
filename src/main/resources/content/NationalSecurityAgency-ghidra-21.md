#"Convert" EquatePlugin not present in "Decompile"

Owner: NationalSecurityAgency

Repo: ghidra

Labels: Type: Bug Feature: Decompiler 

## wsxarcher (06 Mar 2019)

**Describe the bug**

In the "Listing" is possible to change the representation of bytes but this change is not reflected on the values shown in the "Decompile", and this menu is not present if right clicking on a value on the latter.

**Expected**

The "Convert" menu should be also available in the "Decompile" values' context menu, if not, be mirrored from the "Listing".

**Screenshots**

Listing context menu:
![image](https://user-images.githubusercontent.com/519077/53858946-bbf50080-3fe4-11e9-80cd-ab25fc106501.png)

Listing:
![image](https://user-images.githubusercontent.com/519077/53859090-3e7dc000-3fe5-11e9-98c6-ca87f8059891.png)

Decompile:
![image](https://user-images.githubusercontent.com/519077/53859068-29089600-3fe5-11e9-9785-ca0cd40a9ed0.png)


## saruman9 (15 Mar 2019)

Also Ghidra have some problems with converting negative numbers:

![2019-03-15_105042_093338494](https://user-images.githubusercontent.com/4244396/54416446-733df580-4710-11e9-83af-965889d8bfbe.png)
> Negative number should be char

![2019-03-15_105059_085971621](https://user-images.githubusercontent.com/4244396/54416459-80f37b00-4710-11e9-95fd-7c8a30f228ed.png)
> Negative number interpreted as negative char

![2019-03-15_105541_366642896](https://user-images.githubusercontent.com/4244396/54416594-e7789900-4710-11e9-9c33-4bf13337596e.png)
> Also in IDA

![2019-03-15_105327_689444093](https://user-images.githubusercontent.com/4244396/54416549-c617ad00-4710-11e9-81e2-4096d637a838.png)
> IDA can convert negative char




## SaifRushdHadad (12 Mar 2021)

Has anyone made any progress on this?

## biggestsonicfan (03 Jun 2021)

I also am interested in the progress on this...

## MrSapps (07 Jul 2021)

Still seems to be a problem in v10.0

## ryanmkurtz (07 Jul 2021)

Fixed by 2143c4961db49d3f59c708f395e4397ccf1d3e78

## Wall-AF (08 Jul 2021)

Could also do with `Unsigned:` too.

