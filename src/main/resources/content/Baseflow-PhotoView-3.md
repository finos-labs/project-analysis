#The app is slow with PhotoView

Owner: Baseflow

Repo: PhotoView

Labels: 

## tasomaniac (08 Oct 2012)

The library is great, I want to use it but it seems it has a problem. Let me explain.

I am using viewpager with a cursor. Cursor has like 500 items. Since I am using viewpager it should load 2 pages at once. 

I have used PagerAdapter and FragmentStatePagerAdapter both and the result is the same with your PhotoView. It is too darn slow.

When I use normal ImageView it is fast. When I use PhotoView with only 3 pages in viewpager, it is fast again. 

What could be the problem??


## chrisbanes (08 Oct 2012)

How are you populating the the PagerAdapter from the Cursor? (code preferably) 

This class is used in my [photup](https://play.google.com/store/apps/details?id=uk.co.senab.photup) app and works fine in a ViewPager (with hundreds of items).


## tasomaniac (08 Oct 2012)

Yes I know photup is also great BTW.

I have used PagerAdapter with instantiateItem() function below.

@Override

 public Object instantiateItem(View container, int position) {

 this.container = (ViewPager) container;

```
 String url=" ";

    if(c.moveToPosition(position))

    url =  c.getString(c.getColumnIndex(TutListDatabase.COL_URL));

    PhotoView v = (PhotoView) new PhotoView(context);

    ImageLoader imageLoader = new ImageLoader(context, false);

    imageLoader.DisplayImage(url, v);

this.container.addView(v);
```

 return v;

 }

On Mon, Oct 8, 2012 at 1:51 PM, Chris Banes notifications@github.comwrote:

> How are you populating the the PagerAdapter from the Cursor? (code
> preferably)
> 
> This class is used in my photuphttps://play.google.com/store/apps/details?id=uk.co.senab.photupapp and works fine in a ViewPager (with hundreds of items).
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/chrisbanes/PhotoView/issues/3#issuecomment-9222135.


## httpdispatch (04 Dec 2012)

I can confirm that PhotoView + ViewPager eats memory extreamely fast and doesn't release it when page changes. This causes slow performance and outofmemory issue. The issue is not present if i use anothe imageview


## tasomaniac (04 Dec 2012)

But it does not slown down my phone in Photup application of the author of
this lib.

## 

std

http://tasomaniac.com http://card.ly/taso
http://home.ku.edu.tr/~sdane

On Tue, Dec 4, 2012 at 12:29 PM, Eugene Popovich
notifications@github.comwrote:

> I can confirm that PhotoView + ViewPager eats memory extreamely fast and
> doesn't release it when page changes. This causes slow performance and
> outofmemory issue. The issue is not present if i use anothe imageview
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/chrisbanes/PhotoView/issues/3#issuecomment-10991517.


## httpdispatch (04 Dec 2012)

Opened the issue for this #16. Memory leak really occurs


