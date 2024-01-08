#Supports internal and external network separation Settings

Owner: apache

Repo: rocketmq

Labels: 

## aluomaidi (26 Mar 2018)

Let's start with the background. Now we're doing a message queuing service, and the underlying platform is based on k8s or openstack. I looked at the source code, slaver used brokerIP1:10911 and brokerIP1:10909 and brokerIP1:10909, slaver used brokerIP2:10912 from master synchronization data, while producer, consumer and console used brokerIP1:10911 or brokerIP1:10909. Our master and slaver and console deployment in an internal network, but the business side of the consumer and producer may is not in the network, if I set the broker brikerIP1 to a internal network address, so the external access apparently no, if I set the brokerIP1 to a external network address, need to do some extra work and slaver also console interact with the the master network increased by at least one forward, we plan to do network isolation, so don't want to do so. We want to have a solution that satisfies the business side's production and consumption components using an external network address, while slaver, console USES the Intranet network address, and currently they are not satisfied with brokerIP1. My idea is that the internal network and external network information configuration, and register to namesrv, with your role when you request each component namesrv information, namesrv according to the character select corresponding address back to the requesting party, also can put the judgment logic to request component side, according to their own role to choose the appropriate address, anyway, there are a lot of code changes.what's your plan？aliyun should also have this problem? I sincerely hope to receive your Suggestions!



## vongosling (27 Apr 2018)

Could you set your broker VIP easily for your use case? Another, this is not a correct place to ask for help. Please read carefully for our issue template. If you have any question about RocketMQ, please consult our user email list. How to reach our email list, please see here, http://rocketmq.apache.org/about/contact/

