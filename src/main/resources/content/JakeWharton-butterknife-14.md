#Moar robustness?

Owner: JakeWharton

Repo: butterknife

Labels: 

## imminent (25 Mar 2013)

What do you think of doing a sort of LRU cache pattern for `INJECTORS`, to improve scalability? I don't know if it is easy to become problematic, but in theory, an app could have tons of things injecting Views. Was just thinking about that.


## JakeWharton (25 Mar 2013)

The reference to the `Class` is free on Dalvik so we only really incur the underlying entry in the `Map`. Unless this becomes a problem I'm inclined to ignore it for now. Even if you have 1000 classes in this map the overhead is still incredibly small.


## imminent (25 Mar 2013)

Nice, I just wanted to make sure.

On Monday, March 25, 2013, Jake Wharton wrote:

> The reference to the Class is free on Dalvik so we only really incur and
> the underlying entry in the Map. Unless this becomes a problem I'm
> inclined to ignore it for now. Even if you have 1000 classes in this map
> the overhead is still incredibly small.
> 
> —
> Reply to this email directly or view it on GitHubhttps://github.com/JakeWharton/butterknife/issues/14#issuecomment-15403132
> .

## 

## 

Dandré Allison                                (323) 823-4456
KeepandShare.com http://www.keepandshare.com

```
          Schedule & Share. Simply. Securely.
```


