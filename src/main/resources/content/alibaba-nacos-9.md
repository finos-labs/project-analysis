#这代码质量感觉一般啊

Owner: alibaba

Repo: nacos

Labels: 

## zhuam (24 Jul 2018)

rt
healthcheck 里面的 ExecutorService 到处都是， UtilsAndCommons 里面的 ScheduledExecutorService 也是， 这种系统 thread 必须要做到可知、可控、可调啊

另外重点看了一下 Raft，  感觉质量一般，有点乱啊

## pguo65535 (24 Jul 2018)

感谢您的中肯意见，Nacos 0.1 代码还正在经历从阿里内部复杂的体系中逐渐梳理、剥离及重构的过程中，所以确实有不少瑕疵，你可以看到包括core等代码目录目前还是空的，也是这个原因。Nacos正在持续重构中，计划在0.8版本完成重构，从而达到Production Ready的状态。

而Raft这一块我们基于阿里大规模生产需求的实际考虑，适当放低一致性要求提高可用性上做了很大幅度的定制，所以不了解整体设计意图的情况下阅读起来确实会有点费劲，未来我们会将这块的设计和实现原理考虑文档化公开出来。

Thank you for your pertinent comments. The Nacos 0.1 code is also undergoing a process of gradual combing, stripping and refactoring from Alibaba's internal complex system, so there are indeed a lot of flaws, including you can see that the core and other code directories are still empty. This is also the reason. Nacos is continuing to refactor and plans to complete the refactoring in version 0.8 to achieve a production ready state.

And Raft, which is based on the practical considerations of Alibaba's large-scale production requirements, has made a very large customization in terms of appropriately lowering the consistency requirements and improving the usability. Therefore, reading it is really a little difficult without understanding the overall design intent. In the future, we will The design and implementation principles of this piece will be documented and published.

$$$$$$$   NOTICE  $$$$$$$  
                 
为了未来更方便的将Nacos推进到CNCF或者Apache社区，我们恳求大家尽量
用英文发issue或者评论##（当然，仍然是非强制的）

In order to promote Nacos to the CNCF or Apache community more conveniently 
 in the future, we beg you to publish the issue in English  
 (Of course, it is still non-mandatory).

$$$$$$$$$$$$$$    




## xuechaos (29 Jul 2018)

感谢提出，不过Nacos遵循阿里巴巴JAVA代码规约，你可以在 https://github.com/alibaba/p3c 这里看到，整体有问题，可以具体提出。代码我们还在持续完善。

Thanks for the issues, but Nacos follows the Alibaba JAVA code specification. You can see it here at https://github.com/alibaba/p3c. There are problems in the whole and can be specified. The code is still improving.

