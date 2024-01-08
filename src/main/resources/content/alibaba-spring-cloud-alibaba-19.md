#Get wrong instance list at first time

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: bug nacos 

## HaojunRen (18 Sept 2018)

How to reproduce this issue, refer to followings:

    I have registered 2 instances (ex service name are all 'a') to nacos server
    Then I stop them
    After a while, I do step 1 again
    Then I get 2 instances from interface, for first time, it returns 1 instance or 0 instance. But I invoke again, it is fine to return 2 instances. Guess the cache issue?

## HaojunRen (10 Oct 2018)

Fixed

