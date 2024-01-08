#Support for views with OnItemClickListeners

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## matthewmichihara (04 Oct 2014)

I am not sure if this is in the scope of the project, but the `ViewObservable` `clicks`, `text`, and `input` methods are pretty nice convenience methods. Today I found myself wanting an `Observable` for item clicks on a view with an adapter -- ie an `AutoCompleteTextView` with some autocomplete suggestions.

It is pretty straightforward to add make a method to do this in my app, but would this be something worth adding to the project?


## mttkay (05 Oct 2014)

Could you post some sample code? It sounds like it would be useful to have.


## matthewmichihara (05 Oct 2014)

Wow @naghtarr nice! I was thinking something along the lines of your commit, but in my case I need it for an `AutoCompleteTextView` which surprisingly is not an `AdapterView`. 

Usage would be almost identical though --

``` java
ViewObservable
    .itemClicks(autoCompleteTextView)
    .subscribe((parent, view, position, id) -> { Log.d(TAG, "Item " + position + " clicked"); });
```


## kboyarshinov (05 Oct 2014)

@matthewmichihara It would be very similar for `AutoCompleteTextView`:

```
public class OperatorAutoCompleteTextViewSuggestionsClick implements Observable.OnSubscribe<OnItemClickEvent> {

    private final AutoCompleteTextView autoCompleteTextView;

    public OperatorAutoCompleteTextViewSuggestionsClick(final AutoCompleteTextView autoCompleteTextView) {
        this.autoCompleteTextView = autoCompleteTextView;
    }

    @Override
    public void call(final Subscriber<? super OnItemClickEvent> observer) {
        Assertions.assertUiThread();
        final CompositeOnClickListener composite = CachedListeners.getFromViewOrCreate(autoCompleteTextView);

        final AbsListView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                observer.onNext(new OnItemClickEvent(parent, view, position, id));
            }
        };

        final Subscription subscription = AndroidSubscriptions.unsubscribeInUiThread(new Action0() {
            @Override
            public void call() {
                composite.removeOnClickListener(listener);
            }
        });

        composite.addOnClickListener(listener);
        observer.add(subscription);
    }

    private static class CompositeOnClickListener implements AbsListView.OnItemClickListener {
        private final List<AbsListView.OnItemClickListener> listeners = new ArrayList<AbsListView.OnItemClickListener>();

        public boolean addOnClickListener(final AbsListView.OnItemClickListener listener) {
            return listeners.add(listener);
        }

        public boolean removeOnClickListener(final AbsListView.OnItemClickListener listener) {
            return listeners.remove(listener);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            for (AdapterView.OnItemClickListener listener : listeners) {
                listener.onItemClick(parent, view, position, id);
            }
        }
    }

    private static class CachedListeners {
        private static final Map<AutoCompleteTextView, CompositeOnClickListener> sCachedListeners = new WeakHashMap<AutoCompleteTextView, CompositeOnClickListener>();

        public static CompositeOnClickListener getFromViewOrCreate(final AutoCompleteTextView view) {
            final CompositeOnClickListener cached = sCachedListeners.get(view);

            if (cached != null) {
                return cached;
            }

            final CompositeOnClickListener listener = new CompositeOnClickListener();

            sCachedListeners.put(view, listener);
            view.setOnItemClickListener(listener);

            return listener;
        }
    }
}
```

And at `ViewObservable`

```
   public static <T extends AutoCompleteTextView> Observable<OnItemClickEvent> itemClicks(final T autoCompleteTextView) {
        return Observable.create(new OperatorAutoCompleteTextViewSuggestionsClick(autoCompleteTextView));
   }
```

Unfortunately, `AutoCompleteTextView` is untestable. It contains `ListPopupWindow` which not always exists. It creates inner DropDownListView every time `show()` called. Therefore, fails in unit tests. For example after performing click:

```
    java.lang.IllegalArgumentException: View not attached to window manager
        at android.view.WindowManagerImpl.findViewLocked(WindowManagerImpl.java:653)
        at android.view.WindowManagerImpl.removeView(WindowManagerImpl.java:349)
        at org.fest.reflect.method.Invoker.invoke(Invoker.java:112)
        at org.robolectric.shadows.ShadowWindowManagerImpl.removeView(ShadowWindowManagerImpl.java:33)
        at android.view.WindowManagerImpl.removeView(WindowManagerImpl.java)
        at android.view.WindowManagerImpl$CompatModeWrapper.removeView(WindowManagerImpl.java:160)
        at org.robolectric.shadows.ShadowPopupWindow.dismiss(ShadowPopupWindow.java:118)
        at android.widget.PopupWindow.dismiss(PopupWindow.java)
        at android.widget.ListPopupWindow.dismiss(ListPopupWindow.java:612)
        at android.widget.AutoCompleteTextView.dismissDropDown(AutoCompleteTextView.java:1018)
        at android.widget.AutoCompleteTextView.performCompletion(AutoCompleteTextView.java:890)
        at android.widget.AutoCompleteTextView.access$500(AutoCompleteTextView.java:91)
        at android.widget.AutoCompleteTextView$DropDownItemClickListener.onItemClick(AutoCompleteTextView.java:1177)
        at android.widget.AdapterView.performItemClick(AdapterView.java:298)
        at android.widget.AbsListView.performItemClick(AbsListView.java:1086)
        at rx.android.operators.OperatorAutoCompleteTextViewSuggestionsClickTest.testAutoCompleteSuggestions(OperatorAutoCompleteTextViewSuggestionsClickTest.java:95)
```

Even more. `ListPopupWindow` is private field. I had to use reflection to get it. So this test is not good, I guess.


## matthewmichihara (15 Oct 2014)

:+1: thanks guys


