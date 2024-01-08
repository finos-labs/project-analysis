#[Discussion] Code structure and convention

Owner: ReactiveX

Repo: RxAndroid

Labels: 

## Yarikx (15 Oct 2014)

As I said in https://github.com/ReactiveX/RxAndroid/pull/25 I think we should discuss naming convention and code organization question.

Currently most of library functions is some kind of wrappers of native android components to lift them to use with Rx.
Some of them are using `from` prefix and name of wrapped component (`fromBroadcast`, `fromSharedPreferences`, etc). I think this is more verbose but it clear to the user what component is using under the hood.
Other wrappers just use simple form (like `clicks`, `input`). This is more pretty, but can confuse user for the first time.

Should we rename some of them to one kind?


## mttkay (17 Oct 2014)

For what it's worth, at SoundCloud we use the latter. We would have e.g. a `tracks` Observable. I prefer this for two reasons:
- I think of Observables as collections or streams. Giving them the name of what is being streamed seems natural.
- You can streamline creation of observables via operators/adaptors or those created by `create`. At the end of the day, all those methods return observables, so consistency in naming makes sense to me


## Gi-lo (07 Nov 2014)

I guess I can use this issue for further code structure and code convention questions.

I am relatively new to the RX and MVVM topic but have managed to build my first application using these technics (soon available). While doing so we stumbled across a lot of common issues when doing Android apps. I want to share the gathered experience with you as I would love to hear if I do something terribly wrong or if you solved it in a better a way.
- **Adapters**:

``` java
public class XXXItemViewAdapter extends ArrayAdapter<XXXItem, XXXItemView> {

    // Constructor

    public XXXItemViewAdapter(Context context, List<XXXItem> array) {
        super(context, array);
    }

    // ArrayAdapter

    @Override
    protected void onPrepareViewForReuse(XXXItemView view) {
        view.getViewModel().prepareForReuse();
    }

    @Override
    protected XXXItemView onCreateViewAtPosition(int position) {
        return new XXXItemView(getContext());
    }

    @Override
    protected void onBindValues(XXXItemView view, XXXItem item) {
        view.getViewModel().itemCommand.onNext(item);
    }
}
```

`ArrayAdapter` is a simple base adapter which abstracts reusing handling and getting data from an array. The main thing here is that I only need to implement 4 methods.
- 1. The constructor, which is super simple as we are just calling super.
- 2. `onPrepareViewForReuse` which is called by the super class adapter when a view is about to be reused.
- 3. `onCreateViewAtPosition`which is called by the super class adapter when a view needs to be created. Same as on "getView" if the input convertView is null.
- 4. `onBindValues` called when the view is created or reused. I simply send the new item to the item command. The view model then decides what needs to be displayed in the item view.

**Pros:**
- I don't need to test that code... (I need to test the "ArrayAdapter" once and the view model)
- It's pretty easy to create new adapters

**Cons:**
- Not optimal if you need different view types
- I think one could reduce the boilerplate code...

**TBC**
- ListItemView
- AbstractContextViewModel
- Navigation
- more more more

**PS:**
I really think we should update the wiki and create more examples, best practices etc. We could also create a markdown file on the main repo, and contribute to it by using pull requests. Just an idea :)


## mttkay (19 Nov 2014)

This has come up a few times before: classes implemented `OnSubscribe` used to be called "operators" in pre-release versions of RxJava, but with the arrival of `Operator` they got renamed bit by bit and are now all called `OnSubscribe*`. cf. for instance https://github.com/ReactiveX/RxJava/blob/1.x/src/main/java/rx/internal/operators/OnSubscribeCache.java

Unless anyone has objections, I think we should follow suit and rename our classes accordingly and adopt this convention going forward.


## JakeWharton (30 Jun 2015)

As part of #172 all of the inconsistent naming conventions have been removed. We will be absolutely mindful of this in the future as things get re-added. Consistency is a great thing!


