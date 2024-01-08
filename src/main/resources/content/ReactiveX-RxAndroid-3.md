#Address the problem of asynchronously unsubscribing in life-cycle callbacks

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## mttkay (23 Aug 2014)

This was first reported here: https://github.com/ReactiveX/RxJava/issues/1590

Opening a new issue here since RxAndroid is now separated out of core.

Potential solutions so far:
1. Always use `bindFragment` when subscribing Subscribers in fragments that access views in `onNext` (defensive programming style)
2. Provides a safe UI subscriber class that only propagates notifications if not `isUnsubscribed`
3. ... ?


## dpsm (13 Sept 2014)

Reposting a possible change to HandlerThreadScheduler to address the issue of a sync unsubscribe.

Regarding recursive scheduling resulting in stack overflows, the provided Action should be responsible to break the recursion and avoid the stack overflow just like any recursive call should it not? I am not sure I understand the issue so please apologize if I am not following you :)

Another alternative would be something like this:

```
private ThreadLocal<Queue<Action0>> pendingActions = new ThreadLocal<Queue<Action0>>() {
        @Override
        protected Queue<Action0> initialValue() {
            return new LinkedList<Action0>();
        }
    };

    private ThreadLocal<Boolean> isCalling = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Subscription schedule(final Action0 action) {
        final Looper handlerLooper = handler.getLooper();
        if (Looper.myLooper() == handlerLooper) {
            if (isCalling.get()) {
                Queue<Action0> actions = pendingActions.get();
                actions.add(action);
            } else {
                isCalling.set(true);
                action.call();

                Queue<Action0> actions = pendingActions.get();
                while(!actions.isEmpty()) {
                    actions.poll().call();
                }
                isCalling.set(false);
            }
            return Subscriptions.empty();
        } else {
            return schedule(action, 0, TimeUnit.MILLISECONDS);
        }
    }
```


