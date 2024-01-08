#真实的qps有时和FlowRule中设置的qps的不一致

Owner: alibaba

Repo: Sentinel

Labels: kind/question 

## xflonga (01 Aug 2018)


### Describe what happened (or what feature you want)

我将wiki中的案例代码拷贝到我的IDEA中，然后稍作修改，具体如下：

```java
public static void main(String[] args) {
        String resourceName = "helloWorld";
        initFlowRules(resourceName);

        long begin = System.currentTimeMillis();
        long end = begin + 1000;
        int success = 0;
        int fail = 0;

        while (System.currentTimeMillis() < end) {
            Entry entry = null;
            try {
                entry = SphU.entry(resourceName);
                success++;
            } catch (BlockException e1) {
                fail++;
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }

        System.out.println("Begin time: " + begin);
        System.out.println("End time: " + end);
        System.out.println("Success: " + success);
        System.out.println("Fail: " + fail);
        System.exit(0);
    }

    private static void initFlowRules(String name){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(name);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

```

### Describe what you expected to happen
初始化FlowRule时，设置的qps为20，我期待程序运行结束后，success输出的值也应该为20，
然而实际运行过程中，日志显示success的值 有时为20，有时为40.

>Begin time: 1533132761258
>End time: 1533132762258
>Success: 40
>Fail: 140633





## sczyh30 (02 Aug 2018)

Sentinel 指标统计的滑动窗口中每个小窗口的初始时间都是经过对齐的（间隔默认 500 ms），而不是当前时间，因此会存在时间偏差导致多通过的情况。

比如流量的时间范围（取后四位）：1258 - 2258

理想的情况是：
```
   瞬间20个请求            这段时间无法通过
       ↓                       ↓
   |    o               |                    |
 1258                  1758                 2258    (windowStart)
```

但实际的窗口是：

```
   瞬间20个请求            这段时间无法通过  第一个窗口过期，因此又可以进入 20 个
        ↓                       ↓              ↓
   |    o               |                    |  o                  |
 1000                  1500                 2000                 2500  (windowStart)
```





## xflonga (02 Aug 2018)

感谢回答。

我理解了，然后今天看了下这一块的代码，sentinel是以500ms为一个时间监控窗口，所以我问题中所描述的2倍其实也不准确，只是因为我测试的时间少，使得增加的起始时间片段和结束时间片段的qps刚好和qps一样。。

