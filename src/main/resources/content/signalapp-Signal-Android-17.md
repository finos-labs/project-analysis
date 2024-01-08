#short username in email address do not return as valid

Owner: signalapp

Repo: Signal-Android

Labels: 

## cem- (08 Jan 2012)

(email addresses changed to prevent spam and whatnot)

Steps to recreate:
long click a message
click 'Forward message'
type in email address in the 'To' text box : a@aaa.aa
Click 'Send'

When I type in aaa@aaa.aa it works great. When I type in a@aaa.aa, I receive the toast error 'Recipient is not a valid SMS or email address!' When I forward to the 'a@aaa.aa' address using the android built in text message program, I can send the message fine, and when I send it from TextSecure, it goes to aaa@aaa.aa perfectly. So, I don't believe it to be an issue with the OS or the email function of TextSecure.

The problem seems to be in line 8 of src\org\thoughtcrime\securesms\util\NumberUtil.java
The regex, ^[\w-]([.w])+[\w]+@([\w-]+.)+[A-Z]{2,4}$, seems to be requiring three letters for the left side of the email. I think this is in contradiction of RFC5322 (page 17) and real email addresses in use today.

Logs (partially redacted):
mm-dd hh:mm:ss.mmm W/compose (#####): org.thoughtcrime.securesms.recipients.RecipientFormattingException: Recipient:a@aaa.aa is badly formatted.
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.recipients.RecipientFactory.parseRecipient(RecipientFactory.java:186)
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.recipients.RecipientFactory.getRecipientsFromString(RecipientFactory.java:114)
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.components.RecipientsPanel.getRecipients(RecipientsPanel.java:84)
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.ComposeMessageActivity.getRecipients(ComposeMessageActivity.java:618)
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.ComposeMessageActivity.sendMessage(ComposeMessageActivity.java:651)
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.ComposeMessageActivity.access$13(ComposeMessageActivity.java:649)
mm-dd hh:mm:ss.mmm W/compose (#####): at org.thoughtcrime.securesms.ComposeMessageActivity$SendButtonListener.onClick(ComposeMessageActivity.java:817)


## cem- (18 Jul 2012)

fixed with https://github.com/WhisperSystems/TextSecure/pull/34

Closing issue.


