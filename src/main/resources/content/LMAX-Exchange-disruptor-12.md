#passing en empty Array into Disruptor.then() causes elements in the ring buffer to be overwritten before passed to an handler [moved]

Owner: LMAX-Exchange

Repo: disruptor

Labels: 

## mikeb01 (26 Sept 2012)

This is [Issue 12](http://code.google.com/p/disruptor/issues/detail?id=12) moved from a Google Code project.
Added by 2011-09-27T07:47:51.000Z by [oliver.d...@gmail.com](http://code.google.com/u/106552207964548886986/).
Please review that bug for more context and additional comments, but update this bug.
 Closed (Fixed).

Original labels: Type-Defect, Priority-Medium
### Original description

```
<b>What steps will reproduce the problem?</b>
1. Instantiate a Disruptor, using the dsl 
2. invoke Disruptor.handleEventsWith(), passing a proper event handler
3. invoke Disruptor.then() passing an empty array

<b>What is the expected output? What do you see instead?</b>

The expected would be, that the created disrupter behaves normally, i.e. calls the event handler callback for each element, published into the ring buffer. Instead, we see that elements seem to be either overwritten (current assumption) or dispatched in the wrong order..

<b>What version of the product are you using? On what operating system?</b>

Latest Java 2.6 release on Windows XP.

<b>Please provide any additional information below.</b>

Attached, please find a little test driver able to  reproduce the problem. The code simply instantiates a new ring buffer with 128 elements. A producer then pumps 10'000'000 events (simple integer wrappers) into the buffer, and the registered EventHandler just verifies that the elements are delivered to the callback in order.

If the disrupto was created using 

disruptor.handleEventsWith(myHandler);

everything is fine. If, however, it is created with 

disruptor.handleEventsWith(myHandler).then(new EventHandler[0]);

it does not behave properly anymore.

I know, passing an empty array is strange. However, I think the API should be able to handle that situation. Either by throwing an IllegalArgumentException or - IMHO the preferred solution - by simply not doing anything. 

I would prefer the latter approach, since I think it is nicer in the context of the DSL like API of the Disruptor class. In my eyes the example above would then read something like &quot;Instantiate the Disruptor, handle events with my handler and then don't do anything&quot;
```


