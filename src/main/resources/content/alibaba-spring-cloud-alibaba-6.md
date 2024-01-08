#Can Sentinel support feign ?

Owner: alibaba

Repo: spring-cloud-alibaba

Labels: enhancement sentinel 

## xiejiashuai (06 Sept 2018)

RestTemplate 使用起来太不友好了，不如Feign方便。可否考虑支持下Feign。

Spring Framework RestTemplate is not convenient when used , can consider supproting Feign.

## flystar32 (19 Sept 2018)

Sentinel 目前对于有线程切换的行为处理还不大完善，待 Sentinel 完善支持后会评估支持方案。

Sentinel currently does not have a good deal of behavior for thread switching, we will evaluate it after Sentinel supports this scenario.

## fangjian0423 (06 Dec 2018)

A simple FeignClient like this:

```
@FeignClient(name = "service-provider", fallback = EchoServiceFallback.class)
public interface EchoService {
	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	String echo(@PathVariable("str") String str);
}
```

The resource name of method `echo` is: `GET:http://service-provider/echo/{str}` and we use `SphU` to handle it:

```
Entry entry = SphU.entry(resourceName, EntryType.OUT, 1, args);
```

So we can use any rules, including `ParamFlowRule`.

## zjun2 (30 Jan 2019)

心累。。。一群中国人用英文沟通。我们这种菜鸟英文不好，看起来真心心累🤨

