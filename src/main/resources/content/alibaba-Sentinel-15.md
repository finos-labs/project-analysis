#minRt 4900

Owner: alibaba

Repo: Sentinel

Labels: kind/question 

## jarvisxiong (31 Jul 2018)

https://github.com/alibaba/Sentinel/blob/6bb4ff21dadde00c9453a5f57d030d55f87b71a8/sentinel-core/src/main/java/com/alibaba/csp/sentinel/slots/statistic/base/Window.java#L34

minRt init set to 4900,is it experience point,why?


## sczyh30 (31 Jul 2018)

That's the value in experience at Alibaba. You can change according to your own demand (e.g. max timeout, RT outlier)

