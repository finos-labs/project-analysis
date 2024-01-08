#Listener Injection Proposal

Owner: JakeWharton

Repo: butterknife

Labels: 

## JakeWharton (09 Mar 2013)

``` java
@InjectView(R.id.call) TextView call;
@InjectView(R.id.call) OnClickListener callClickListener = new OnClickListener() { };
@InjectView(R.id.thing) OnDragListener thingDragListener = new OnDragListener() { };
```

which would amount to:

``` java
call = (TextView) activity.findViewById(R.id.call);
call.setOnClickListener(callClickListener);
activity.findViewById(R.id.thing).setOnDragListener(thingDragListener);
```


## hameno (10 Mar 2013)

I think this would be a great addition though I think it would be better if you would separate `@InjectView` from the click listener annotation. I like the style of AndroidAnnotations (though I think AA has a very weird overall handling of generated code, e.g. the weird underscore Activities you have to use everywhere) where they have @Click-method annotations.

So, following AAs style I think it could look like this: 

``` java
@InjectView(R.id.button1) Button buttonOne;
@InjectView(R.id.button2) Button buttonTwo;

@Click(R.id.button1)
public void onButtonOneClick(View v) {}

@Click(R.id.button2)
public void onButtonTwoClick(View v) {}
```

which would generate somethink like this:

```
final View.OnClickListener onClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                activity.onButtonOneClick(v);
                break;
            case R.id.button2:
                activity.onButtonTwoClick(v);
                break;
        }
    }
};
buttonOne = (Button) activity.findViewById(R.id.button1);
buttonOne.setOnClickListener(onClickListener);
buttonTwo = (Button) activity.findViewById(R.id.button2);
buttonTwo.setOnClickListener(onClickListener);
```

What do you think?


## JakeWharton (10 Mar 2013)

Hmmm. `@Click` is very limiting though. I'd have to create annotations for every listener action.

I'll think about it. I do like having callbacks bound directly to methods.


## imminent (15 Mar 2013)

Best of both: `@InjectListener`? It would stay a field annotation, but have a separate annotation. How would you want to handle injecting a listener without injecting the view as well? It seems like sometimes you only want the `View` so that you can attach an event callback, particularly with buttons. So the generated code some be fine making a local variable for the `View` to attach the listener.

I just thought of another variation while writing this: `@InjectListener(OnClickListener.class, R.id.button)`. This would be a method annotation, but just like the field annotation, it would be able to use the type (this time given as a parameter) to work for more than just clicking. That could even be expanded to things like `OnNavigationItemSelected` or `OnOptionItemSelected` if desired.

One nice feature of keeping "Inject" in the name of the annotation is that you can still say call `Views.inject(this)`, otherwise it could get a little weird keeping track of what is being injected. That actually is a quality of the field injection approach - all the Butterknife injections can be placed together sequentially. 


## imminent (25 Mar 2013)

I've started a draft for my proposed solution to listener injection: https://github.com/imminent/butterknife/commit/4958c471efabd5bffad8b1beb23fcb13149b5d2e

Some take aways:
- Separating the inject annotations means you lose the `View`'s type (unless you interact with `Views`)
- Seems to feasibly be extensible to any listener that can be associated with a `View` given the setOn*Listener(...)` pattern that seems to be consistent
- Version gate things like `OnDragListener`?
- I think my non-`View` listeners would require different API to work
- Support for custom listeners (as in, for custom `View`s)? Drop listener validation or let developer register a listener? 
- Testing listener injection
- `Listeners.inject(activity);` `Listeners.inject(fragment, activity);`


## JakeWharton (25 Mar 2013)

I have a local branch as well with a version. I opted to go the one-annotation-per-listener model on methods because it allows for the most code removal. It also allows for binding multiple IDs to a single method:

``` java
@BindClick({R.id.title, R.id.subtitle, R.id.other_thing})
void doStuff(View view) {
}
```

Additionally you can add support for concrete types as the argument, something you cannot do with `onClick` attributes:

``` java
@BindClick(R.id.my_view)
void onClick(MyCustomView view) {
}
```

Internally the code is structured similar to what you have above. It also will allow for custom listener types to be declared but I'm still torn on whether to support it or not.


## imminent (25 Mar 2013)

Ah, cool. So you would have `@BindClick`, `@BindDrag`, and `@BindCustomListener`? Or something. Do you have a good list of the available listeners that this API would be compatible with? I'm thinking (depending on how you're generating the listeners) the API could support `OnNavigationListener` or `FragmentManager.OnBackStackChangedListener` (ignoring Android version considerations), since you're already getting the `Activity`. 

Would you still use `Views.inject(...)` to bind the listeners, or a separate method like what I was doing (`Listeners.bind(...)`)? 

Are you torn because of complication of the API, usefulness, or just staying very picky on what is supported? I'm fine with which ever, just curious so I can think about the issue as well. I'd say one nice thing about the `View` related listeners (goes against my comment here) is that it's still related to `View`s. That way the focus of butterknife can be around rarefying `View` related code.


## JakeWharton (25 Mar 2013)

All listeners on `View` will probably be supported. Maybe some of the specialized ones on specific view/layout types. No listeners on anything that's not a `View` subclass. Still all fulfilled by `Views.inject`.

Not sure about naming either. Keep jumping around between `@OnClick`, `@BindClick`, and `@Click`.


## imminent (25 Mar 2013)

I like the descriptive `@ObserveClick` or `@ObserveClickOn`, even
`@ObservesClickOn`. Should make it pretty obvious that the method observes
click events on the `View`s. `View`s only, sounds like a good way to keep the library focused.


## SimonVT (31 Mar 2013)

You could just strip set and listener from the method name. @&#8203;OnClick, @&#8203;OnTouch, @&#8203;OnDrag, etc.


## JakeWharton (19 Jun 2013)

Turns out there's a lot of listeners in the view class hierarchies.

```
View.OnAttachStateChangeListener
- onViewAttachedToWindow(View v)
- onViewDetachedFromWindow(View v)

View.OnClickListener
- onClick(View v)

View.OnCreateContextMenuListener
- onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)

View.OnDragListener
- onDrag(View v, DragEvent event)

View.OnFocusChangeListener
- onFocusChange(View v, boolean hasFocus)

View.OnGenericMotionListener
- onGenericMotion(View v, MotionEvent event)

View.OnHoverListener
- onHover(View v, MotionEvent event)

View.OnKeyListener
- onKey(View v, int keyCode, KeyEvent event)

View.OnLayoutChangeListener
- onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)

View.OnLongClickListener
- onLongClick(View v)

View.OnSystemUiVisibilityChangeListener
- onSystemUiVisibilityChange(int visibility)

View.OnTouchListener
- onTouch(View v, MotionEvent event)

ViewGroup.OnHierarchyChangeListener
- onChildViewAdded(View parent, View child)
- onChildViewRemoved(View parent, View child)

TextView.OnEditorActionListener
- onEditorAction(TextView v, int actionId, KeyEvent event)

TextureView.SurfaceTextureListener
- onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height)
- onSurfaceTextureDestroyed(SurfaceTexture surface)
- onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
- onSurfaceTextureUpdated(SurfaceTexture surface)

ViewStub.OnInflateListener
- onInflate(ViewStub stub, View inflated)

AbsListView.MultiChoiceModeListener
- onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)

AbsListView.OnScrollListener
- onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
- onScrollStateChanged(AbsListView view, int scrollState)

AbsListView.RecyclerListener
- onMovedToScrapHeap(View view)

AdapterView.OnItemClickListener
- onItemClick(AdapterView<?> parent, View view, int position, long id)

AdapterView.OnItemLongClickListener
- onItemLongClick(AdapterView<?> parent, View view, int position, long id)

AdapterView.OnItemSelectedListener
- onItemSelected(AdapterView<?> parent, View view, int position, long id)
- onNothingSelected(AdapterView<?> parent)

AutoCompleteTextView.OnDismissListener
- onDismiss()

CalendarView.OnDateChangeListener
- onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)

Chronometer.OnChronometerTickListener
- onChronometerTick(Chronometer chronometer)

CompoundButton.OnCheckedChangeListener
- onCheckedChanged(CompoundButton buttonView, boolean isChecked)

DatePicker.OnDateChangedListener
- onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)

DrawerLayout.DrawerListener
- onDrawerClosed(View drawerView)
- onDrawerOpened(View drawerView)
- onDrawerSlide(View drawerView, float slideOffset)
- onDrawerStateChanged(int newState)

ExpandableListView.OnChildClickListener
- onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)

ExpandableListView.OnGroupClickListener
- onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)

ExpandableListView.OnGroupCollapseListener
- onGroupCollapse(int groupPosition)

ExpandableListView.OnGroupExpandListener
- onGroupExpand(int groupPosition)

FragmentBreadCrumbs.OnBreadCrumbClickListener
- onBreadCrumbClick(FragmentManager.BackStackEntry backStack, int flags)

GestureOverlayView.OnGestureListener
- onGesture(GestureOverlayView overlay, MotionEvent event)
- onGestureCancelled(GestureOverlayView overlay, MotionEvent event)
- onGestureEnded(GestureOverlayView overlay, MotionEvent event)
- onGestureStarted(GestureOverlayView overlay, MotionEvent event)

GestureOverlayView.OnGesturePerformedListener
- onGesturePerformed(GestureOverlayView overlay, Gesture gesture)

GestureOverlayView.OnGesturingListener
- onGesturingEnded(GestureOverlayView overlay)
- onGesturingStarted(GestureOverlayView overlay)

NumberPicker.OnScrollListener
- onScrollStateChange(NumberPicker view, int scrollState)

NumberPicker.OnValueChangeListener
- onValueChange(NumberPicker picker, int oldVal, int newVal)

RadioGroup.OnCheckedChangeListener
- onCheckedChanged(RadioGroup group, int checkedId)

RatingBar.OnRatingBarChangeListener
- onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)

SearchView.OnCloseListener
- onClose()

SearchView.OnQueryTextListener
- onQueryTextChange(String newText)
- onQueryTextSubmit(String query)

SearchView.OnSuggestionListener
- onSuggestionClick(int position)
- onSuggestionSelect(int position)

SeekBar.OnSeekBarChangeListener
- onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
- onStartTrackingTouch(SeekBar seekBar)
- onStopTrackingTouch(SeekBar seekBar)

SlidingDrawer.OnDrawerCloseListener
- onDrawerClosed()

SlidingDrawer.OnDrawerOpenListener
- onDrawerOpened()

SlidingDrawer.OnDrawerScrollListener
- onScrollEnded()
- onScrollStarted()

SlidingPaneLayout.PanelSlideListener
- onPanelClosed(View panel)
- onPanelOpened(View panel)
- onPanelSlide(View panel, float slideOffset)

TabHost.OnTabChangeListene
- onTabChanged(String tabId)

TimePicker.OnTimeChangedListener
- onTimeChanged(TimePicker view, int hourOfDay, int minute)

ViewPager.OnPageChangeListener
- onPageScrollStateChanged(int state)
- onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
- onPageSelected(int position)
```


## imminent (19 Jun 2013)

Wow! I might come back here next time I'm looking for a View listener.

On Tuesday, June 18, 2013, Jake Wharton wrote:

> Turns out there's a lot of listeners in the view class hierarchies.
> 
> View.OnAttachStateChangeListener
> - onViewAttachedToWindow(View v)
> - onViewDetachedFromWindow(View v)
> 
> View.OnClickListener
> - onClick(View v)
> 
> View.OnCreateContextMenuListener
> - onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
> 
> View.OnDragListener
> - onDrag(View v, DragEvent event)
> 
> View.OnFocusChangeListener
> - onFocusChange(View v, boolean hasFocus)
> 
> View.OnGenericMotionListener
> - onGenericMotion(View v, MotionEvent event)
> 
> View.OnHoverListener
> - onHover(View v, MotionEvent event)
> 
> View.OnKeyListener
> - onKey(View v, int keyCode, KeyEvent event)
> 
> View.OnLayoutChangeListener
> - onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
> 
> View.OnLongClickListener
> - onLongClick(View v)
> 
> View.OnSystemUiVisibilityChangeListener
> - onSystemUiVisibilityChange(int visibility)
> 
> View.OnTouchListener
> - onTouch(View v, MotionEvent event)
> 
> ViewGroup.OnHierarchyChangeListener
> - onChildViewAdded(View parent, View child)
> - onChildViewRemoved(View parent, View child)
> 
> TextView.OnEditorActionListener
> - onEditorAction(TextView v, int actionId, KeyEvent event)
> 
> TextureView.SurfaceTextureListener
> - onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height)
> - onSurfaceTextureDestroyed(SurfaceTexture surface)
> - onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
> - onSurfaceTextureUpdated(SurfaceTexture surface)
> 
> ViewStub.OnInflateListener
> - onInflate(ViewStub stub, View inflated)
> 
> AbsListView.MultiChoiceModeListener
> - onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked)
> 
> AbsListView.OnScrollListener
> - onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
> - onScrollStateChanged(AbsListView view, int scrollState)
> 
> AbsListView.RecyclerListener
> - onMovedToScrapHeap(View view)
> 
> AdapterView.OnItemClickListener
> - onItemClick(AdapterView<?> parent, View view, int position, long id)
> 
> AdapterView.OnItemLongClickListener
> - onItemLongClick(AdapterView<?> parent, View view, int position, long id)
> 
> AdapterView.OnItemSelectedListener
> - onItemSelected(AdapterView<?> parent, View view, int position, long id)
> - onNothingSelected(AdapterView<?> parent)
> 
> AutoCompleteTextView.OnDismissListener
> - onDismiss()
> 
> CalendarView.OnDateChangeListener
> - onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
> 
> Chronometer.OnChronometerTickListener
> - onChronometerTick(Chronometer chronometer)
> 
> CompoundButton.OnCheckedChangeListener
> - onCheckedChanged(CompoundButton buttonView, boolean isChecked)
> 
> DatePicker.OnDateChangedListener
> - onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
> 
> DrawerLayout.DrawerListener
> - onDrawerClosed(View drawerView)
> - onDrawerOpened(View drawerView)
> - onDrawerSlide(View drawerView, float slideOffset)
> - onDrawerStateChanged(int newState)
> 
> ExpandableListView.OnChildClickListener
> - onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
> 
> ExpandableListView.OnGroupClickListener
> - onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
> 
> ExpandableListView.OnGroupCollapseListener
> - onGroupCollapse(int groupPosition)
> 
> ExpandableListView.OnGroupExpandListener
> - onGroupExpand(int groupPosition)
> 
> FragmentBreadCrumbs.OnBreadCrumbClickListener
> - onBreadCrumbClick(FragmentManager.BackStackEntry backStack, int flags)
> 
> GestureOverlayView.OnGestureListener
> - onGesture(GestureOverlayView overlay, MotionEvent event)
> - onGestureCancelled(GestureOverlayView overlay, MotionEvent event)
> - onGestureEnded(GestureOverlayView overlay, MotionEvent event)
> - onGestureStarted(GestureOverlayView overlay, MotionEvent event)
> 
> GestureOverlayView.OnGesturePerformedListener
> - onGesturePerformed(GestureOverlayView overlay, Gesture gesture)
> 
> GestureOverlayView.OnGesturingListener
> - onGesturingEnded(GestureOverlayView overlay)
> - onGesturingStarted(GestureOverlayView overlay)
> 
> NumberPicker.OnScrollListener
> - onScrollStateChange(NumberPicker view, int scrollState)
> 
> NumberPicker.OnValueChangeListener
> - onValueChange(NumberPicker picker, int oldVal, int newVal)
> 
> RadioGroup.OnCheckedChangeListener
> - onCheckedChanged(RadioGroup group, int checkedId)
> 
> RatingBar.OnRatingBarChangeListener
> - onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
> 
> SearchView.OnCloseListener
> - onClose()
> 
> SearchView.OnQueryTextListener
> - onQueryTextChange(String newText)
> - onQueryTextSubmit(String query)
> 
> SearchView.OnSuggestionListener
> - onSuggestionClick(int position)
> - onSuggestionSelect(int position)
> 
> SeekBar.OnSeekBarChangeListener
> - onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
> - onStartTrackingTouch(SeekBar seekBar)
> - onStopTrackingTouch(SeekBar seekBar)
> 
> SlidingDrawer.OnDrawerCloseListener
> - onDrawerClosed()
> 
> SlidingDrawer.OnDrawerOpenListener
> - onDrawerOpened()
> 
> SlidingDrawer.OnDrawerScrollListener
> - onScrollEnded()
> - onScrollStarted()
> 
> SlidingPaneLayout.PanelSlideListener
> - onPanelClosed(View panel)
> - onPanelOpened(View panel)
> - onPanelSlide(View panel, float slideOffset)
> 
> TabHost.OnTabChangeListene
> - onTabChanged(String tabId)
> 
> TimePicker.OnTimeChangedListener
> - onTimeChanged(TimePicker view, int hourOfDay, int minute)
> 
> ViewPager.OnPageChangeListener
> - onPageScrollStateChanged(int state)
> - onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
> - onPageSelected(int position)
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/7#issuecomment-19663058
> .

## 

## 

Dandré Allison                                (323) 823-4456
KeepandShare.com http://www.keepandshare.com

```
          Schedule & Share. Simply. Securely.
```


## MikolajKakol (06 Jul 2013)

I'm working on similar project code generation project (not yet ready to release). My thinking was that it would be great if my project could be expanded for everyone needs for custom classes.
I was thinking about creating additional annotation for factory classes that could generate code for custom classes. Maybe you could think about something similar so that you might start just with View.OnClickListener and add more possibilities later? That solution could also serve any custom views or external libs.


## imminent (06 Jul 2013)

@novemberox Do you mean a meta annotation (like https://code.google.com/p/jsr-305/source/browse/trunk/ri/src/main/java/javax/annotation/meta/TypeQualifierNickname.java for example) that allows you to define an annotation that annotates other annotations?


## MikolajKakol (06 Jul 2013)

I was thinking about annotating code generation classes and those classes would have some method which would generate string (with generated code to attach listener to class). That method probably should have VaraibleElements  passed and some additional information about code they should generate. But if you think that meta annotation is the way to do it I'd love to help if I could.
In my project I'm far away from implementing that possibility.


## JakeWharton (10 Jul 2013)

`@OnClick` is on `master`.


## JakeWharton (31 Jul 2013)

Really liking `@OnClick`. I think `@OnItemSelected` for `AdapterView` is the next-best candidate for inclusion.


## imminent (31 Jul 2013)

Will that work with ListFragment/ListActivity?
On Jul 31, 2013 3:13 PM, "Jake Wharton" notifications@github.com wrote:

> Really liking @OnClick. I think @OnItemSelected for AdapterView is the
> next-best candidate for inclusion.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/7#issuecomment-21900058
> .


## JakeWharton (31 Jul 2013)

It works everywhere. You give it the view root when injecting so the object makes no difference!


## JakeWharton (26 Nov 2013)

A bunch more added. Closing.


