#Support message statistical log on DLQ for security audit

Owner: apache

Repo: rocketmq

Labels: 

## lindzh (27 Mar 2018)

The issue tracker is **ONLY** used for bug report and feature request. Keep in mind, please check whether there is an existing same report before your raise a new one.

Alternately (especially if your communication is not a bug report), you can send mail to our [mailing lists](http://rocketmq.apache.org/about/contact/). We welcome any friendly suggestions, bug fixes, collaboration and other improvements.

Please ensure that your bug report is clear and that it is complete. Otherwise, we may be unable to understand it or to reproduce it, either of which would prevent us from fixing the bug. We strongly recommend the report(bug report or feature request) could include some hints as the following:

**FEATURE REQUEST**

1. Please describe the feature you are requesting.
Add dlq message stat log for security audit which contains topic,consumerGroup and msgId.
2. Provide any additional detail on your proposed use case for this feature.
When consumeMessage failed, we need to confirm the accurate scope of influence which depend on the  log rather than printmsg.
2. Indicate the importance of this issue to you (blocker, must-have, should-have, nice-to-have). Are you currently using any workarounds to address this issue?
should-have
4. If there are some sub-tasks using -[] for each subtask and create a corresponding issue to map to the sub task:

- [sub-task1-issue-number](example_sub_issue1_link_here): sub-task1 description here, 
- no sub task


