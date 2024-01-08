#Use Android ContactsContract API to properly support birthdays, anniversaries, websites, etc.

Owner: zxing

Repo: zxing

Labels: enhancement android 

## srowen (18 Jan 2014)

Using the ContactsContract API (API level 5) additional VCARD data like  birthdays, anniversaries, websites, etc. can be added.

Here a working example for birthdays:

```
ArrayList<ContentValues> data = new ArrayList<ContentValues>();
ContentValues row1 = new ContentValues();
row1.put(ContactsContract.CommonDataKinds.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE);
row1.put(ContactsContract.CommonDataKinds.Event.TYPE, ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY);
row1.put(ContactsContract.CommonDataKinds.Event.START_DATE, bdayString);
data.add(row1);
intent.putParcelableArrayListExtra(ContactsContract.Intent.Insert.DATA, data);
```

Ported from https://code.google.com/p/zxing/issues/detail?id=1689


## tomkraw1 (12 May 2014)

+1
It would be nice if Barcode Scanner could add separated name and addresses fields to contacts.

I'm not an android developer but I googled for this and I found this:
http://stackoverflow.com/questions/4459138/insert-contact-in-android-with-contactscontract
and this
http://developer.android.com/reference/android/provider/ContactsContract.CommonDataKinds.StructuredName.html


## srowen (12 May 2014)

To be clear, I am not yet clear Android supports it _by Intent_. You are showing how to write straight into the contacts DB, which is not the same. To do means we would have to write a mini contacts app, and could not let people use any of the contacts app functions, and would even now need "write contacts" permission. I don't want that.


## t-8ch (14 Aug 2014)

Can this be closed based on #153?


## srowen (14 Aug 2014)

I think this covers some of the request, yes. It looks like you can use these APIs with Intent too, so I expect actually more is possible.


## SyedalrezaTafrshyan (09 Jun 2018)

نظری ندارم

