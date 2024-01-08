#Misidentifies sender's identity

Owner: signalapp

Repo: Signal-Android

Labels: 

## d1b (04 Feb 2012)

TextSecure does not use the sender's 'provided identifier' as it should. 

What happens:
1. I use an sms gateway to send an sms from 'B'
2. I see an sms on my phone from 'B'
3. I use the same sms gateway to send an sms from 'C'
4. I see an sms on my phone under 'B' 's conversation from 'unknown' 

What should happen:
1. I use an sms gateway to send an sms from 'B'
2. I see an sms on my phone from 'B'
3. I use the same sms gateway to send an sms from 'C'
4. I see an sms on my phone from 'C'


## d1b (04 Feb 2012)

I can confirm that the built in android message application does not have this problem (on my handset).  


## d1b (04 Feb 2012)

Here is the log (and stacktrace) from when I send a message from something like 'ZOMG' to my phone --> 

/SesureSMS( 1030): Got contact update, clearing cache...
W/SesureSMS( 1030): Got contact update, clearing cache...
 W/SMSListener( 1030): Got SMS broadcast...
W/Prefix  ( 1030): Calculating on message: 2**
W/Prefix  ( 1030): Received prefix: test
W/Prefix  ( 1030): Calculated prefix: IDyT
W/Prefix  ( 1030): Calculating on message: 2**
W/Prefix  ( 1030): Received prefix: test
W/Prefix  ( 1030): Calculated prefix: 2CgE
W/MmsSmsDatabase( 1030): Executing query: SELECT _id, body, type, address, subject, normalized_date AS date, m_type, msg_box, transport_type FROM (SELECT DISTINCT date \* 1 AS normalized_date, _id, body, read, thread_id, type, address, subject, date, NULL AS m_type, NULL AS msg_box, 'sms' AS transport_type FROM sms WHERE (thread_id = 48) UNION ALL SELECT DISTINCT date \* 1000 AS normalized_date, _id, NULL AS body, read, thread_id, NULL AS type, NULL AS address, NULL AS subject, date, m_type, msg_box, 'mms' AS transport_type FROM mms WHERE (thread_id = 48) ORDER BY normalized_date DESC) LIMIT 1
W/MmsSmsDatabase( 1030): Executing query: SELECT _id, body, read, type, address, subject, thread_id, normalized_date AS date, m_type, msg_box, transport_type FROM (SELECT DISTINCT date \* 1 AS normalized_date, _id, body, read, thread_id, type, address, subject, date, NULL AS m_type, NULL AS msg_box, 'sms' AS transport_type FROM sms WHERE (read = 0) UNION ALL SELECT DISTINCT date \* 1000 AS normalized_date, _id, NULL AS body, read, thread_id, NULL AS type, NULL AS address, NULL AS subject, date, m_type, msg_box, 'mms' AS transport_type FROM mms WHERE (read = 0) ORDER BY normalized_date ASC)
W/SMSNotifier( 1030): Building pending intent...
W/SMSNotifier( 1030): Adding extras...
W/SmsNotifier( 1030): Adding thread_id to pending intent: 48
W/KeyCharacterMap( 1030): No keyboard for id 131073
W/KeyCharacterMap( 1030): Using default keymap: /system/usr/keychars/qwerty.kcm.bin
W/securesms( 1030): restart called...
W/securesms( 1030): Registering receiver...
W/securesms( 1030): Checking caching service...
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/RecipientFactory( 1030): Hitting recipient provider [ID].
W/KeyCachingService( 1030): Incrementing activity count...
W/DecryptingQueue( 1030): Processing pending decrypts...
W/ComposeMessageActivity( 1030): onStop called...
W/KeyCachingService( 1030): Decrementing activity count...
I/ActivityManager(  135): Starting: Intent { cmp=org.thoughtcrime.securesms/.ComposeMessageActivity (has extras) } from pid 1030
W/securesms( 1030): Unregistering receiver...
W/ComposeMessageActivity( 1030): onCreate called...
W/KeyUtil ( 1030): Checking session...
W/LocalKeyRecord( 1030): Checking: 17-local
W/ComposeMessageActivity( 1030): onStart called...
W/MmsSmsDatabase( 1030): Executing query: SELECT _id, body, type, address, subject, normalized_date AS date, m_type, msg_box, transport_type FROM (SELECT DISTINCT date \* 1 AS normalized_date, _id, body, read, thread_id, type, address, subject, date, NULL AS m_type, NULL AS msg_box, 'sms' AS transport_type FROM sms WHERE (thread_id = 48) UNION ALL SELECT DISTINCT date \* 1000 AS normalized_date, _id, NULL AS body, read, thread_id, NULL AS type, NULL AS address, NULL AS subject, date, m_type, msg_box, 'mms' AS transport_type FROM mms WHERE (thread_id = 48) ORDER BY normalized_date ASC)
W/SmsDatabase( 1030): setMessagesRead time: 98
W/MmsSmsDatabase( 1030): Executing query: SELECT _id, body, read, type, address, subject, thread_id, normalized_date AS date, m_type, msg_box, transport_type FROM (SELECT DISTINCT date \* 1 AS normalized_date, _id, body, read, thread_id, type, address, subject, date, NULL AS m_type, NULL AS msg_box, 'sms' AS transport_type FROM sms WHERE (read = 0) UNION ALL SELECT DISTINCT date \* 1000 AS normalized_date, _id, NULL AS body, read, thread_id, NULL AS type, NULL AS address, NULL AS subject, date, m_type, msg_box, 'mms' AS transport_type FROM mms WHERE (read = 0) ORDER BY normalized_date ASC)
W/ComposeMessageActivity( 1030): onResume called...
W/KeyUtil ( 1030): Checking session...
W/LocalKeyRecord( 1030): Checking: 17-local
W/KeyUtil ( 1030): Checking session...
W/LocalKeyRecord( 1030): Checking: 17-local
W/KeyCachingService( 1030): Incrementing activity count...
W/ConversationAdapter( 1030): org.thoughtcrime.securesms.recipients.RecipientFormattingException: Recipient: ZOMG is badly formatted.
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.recipients.RecipientFactory.parseRecipient(RecipientFactory.java:186)
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.recipients.RecipientFactory.getRecipientsFromString(RecipientFactory.java:114)
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.ConversationAdapter.buildRecipient(ConversationAdapter.java:88)
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.ConversationAdapter.
(ConversationAdapter.java:132)
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.ConversationAdapter.getMessageRecord(ConversationAdapter.java:148)
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.ConversationAdapter.bindView(ConversationAdapter.java:101)
W/ConversationAdapter( 1030):   at org.thoughtcrime.securesms.ConversationAdapter.newView(ConversationAdapter.java:110)
W/ConversationAdapter( 1030):   at android.widget.CursorAdapter.getView(CursorAdapter.java:182)
W/ConversationAdapter( 1030):   at android.widget.AbsListView.obtainView(AbsListView.java:1430)
W/ConversationAdapter( 1030):   at android.widget.ListView.makeAndAddView(ListView.java:1745)
W/ConversationAdapter( 1030):   at android.widget.ListView.fillUp(ListView.java:700)
W/ConversationAdapter( 1030):   at android.widget.ListView.layoutChildren(ListView.java:1602)
W/ConversationAdapter( 1030):   at android.widget.AbsListView.onLayout(AbsListView.java:1260)
W/ConversationAdapter( 1030):   at android.view.View.layout(View.java:7175)
W/ConversationAdapter( 1030):   at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1254)
W/ConversationAdapter( 1030):   at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1130)
W/ConversationAdapter( 1030):   at android.widget.LinearLayout.onLayout(LinearLayout.java:1047)
W/ConversationAdapter( 1030):   at android.view.View.layout(View.java:7175)
W/ConversationAdapter( 1030):   at android.widget.LinearLayout.setChildFrame(LinearLayout.java:1254)
W/ConversationAdapter( 1030):   at android.widget.LinearLayout.layoutVertical(LinearLayout.java:1130)
W/ConversationAdapter( 1030):   at android.widget.LinearLayout.onLayout(LinearLayout.java:1047)
W/ConversationAdapter( 1030):   at android.view.View.layout(View.java:7175)
W/ConversationAdapter( 1030):   at android.widget.FrameLayout.onLayout(FrameLayout.java:338)
W/ConversationAdapter( 1030):   at android.view.View.layout(View.java:7175)
W/ConversationAdapter( 1030):   at android.widget.FrameLayout.onLayout(FrameLayout.java:338)
W/ConversationAdapter( 1030):   at android.view.View.layout(View.java:7175)
W/ConversationAdapter( 1030):   at android.view.ViewRoot.performTraversals(ViewRoot.java:1140)
W/ConversationAdapter( 1030):   at android.view.ViewRoot.handleMessage(ViewRoot.java:1859)
W/ConversationAdapter( 1030):   at android.os.Handler.dispatchMessage(Handler.java:99)
W/ConversationAdapter( 1030):   at android.os.Looper.loop(Looper.java:130)
W/ConversationAdapter( 1030):   at android.app.ActivityThread.main(ActivityThread.java:3683)
W/ConversationAdapter( 1030):   at java.lang.reflect.Method.invokeNative(Native Method)
W/ConversationAdapter( 1030):   at java.lang.reflect.Method.invoke(Method.java:507)
W/ConversationAdapter( 1030):   at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:839)
W/ConversationAdapter( 1030):   at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:597)
W/ConversationAdapter( 1030):   at dalvik.system.NativeStart.main(Native Method)
W/KeyCachingService( 1030): Decrementing activity count...


## d1b (04 Feb 2012)

I wasn't sure if this would be useful or not so I am providing some logs from when I sent a message from 6141414141

First sms:

/KeyCachingService( 1030): Decrementing activity count...
W/SesureSMS( 1030): Got contact update, clearing cache...
 W/SesureSMS( 1030): Got contact update, clearing cache...
W/SMSListener( 1030): Got SMS broadcast...
W/Prefix  ( 1030): Calculating on message: 4141**
W/Prefix  ( 1030): Received prefix: 0x41
W/Prefix  ( 1030): Calculated prefix: /wJz
W/Prefix  ( 1030): Calculating on message: 4141**
W/Prefix  ( 1030): Received prefix: 0x41
W/Prefix  ( 1030): Calculated prefix: IRqy

Second sms:

LocalKeyRecord( 1030): Checking: 60-local
W/KeyCachingService( 1030): Incrementing activity count...
W/KeyCachingService( 1030): Decrementing activity count...
W/securesms( 1030): restart called...
W/securesms( 1030): Registering receiver...
W/securesms( 1030): Checking caching service...
W/KeyCachingService( 1030): Incrementing activity count...
W/DecryptingQueue( 1030): Processing pending decrypts...
W/ComposeMessageActivity( 1030): onStop called...
W/KeyCachingService( 1030): Decrementing activity count...
 W/SMSListener( 1030): Got SMS broadcast...
W/Prefix  ( 1030): Calculating on message: ?**
W/Prefix  ( 1030): Received prefix: fail
W/Prefix  ( 1030): Calculated prefix: fFvC
W/Prefix  ( 1030): Calculating on message: ?**
W/Prefix  ( 1030): Received prefix: fail
W/Prefix  ( 1030): Calculated prefix: qqg8


## d1b (04 Feb 2012)

I think this will fix my issue slightly, but I haven't been able to test it just yet! 

```
diff --git a/src/org/thoughtcrime/securesms/recipients/RecipientFactory.java b/src/org/thoughtcrime/securesms/recipients/RecipientFactory.java
index d8eaf10..59df509 100644
--- a/src/org/thoughtcrime/securesms/recipients/RecipientFactory.java
+++ b/src/org/thoughtcrime/securesms/recipients/RecipientFactory.java
@@ -183,7 +183,8 @@ public class RecipientFactory {
     if (NumberUtil.isValidSmsOrEmail(recipient))
       return getRecipientForNumber(context, recipient);

-    throw new RecipientFormattingException("Recipient: " + recipient + " is badly formatted.");
+    Log.e("RecipientFactory", "Recipient: " + recipient + " is badly formatted.");
+    return getRecipientForNumber(context, recipient);
   }

   public static void clearCache() {
```


## moxie0 (17 Oct 2012)

This should be fixed in 0.7.4, please let me know if you continue to see problems.


## d1b (09 Mar 2013)

@moxie0 this issue still exists in the latest update as of today's date.


## moxie0 (10 Mar 2013)

How are you reproducing this?  Just tried it and wasn't able to reproduce.


## d1b (12 Mar 2013)

@moxie0 I am using an sms gateway to send a message to my phone from different 'senders' (all who are not valid email addresses) and noticing when they conflict / have conflicted with other existing 'senders' (perhaps I have a stale database or something now ? )


## d1b (04 Jun 2014)

This was fixed by addea8d3400a352ce7bb152f99527e72a8e716ee .


