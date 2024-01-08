#Crashes when matchparent/wrapcontent

Owner: bumptech

Repo: glide

Labels: bug 

## lalith-b (03 Sept 2013)

when imageview width is match_parent
imageview height is wrap_content then the imageview fails to load the image and crashes when we give .into(ImageView)


## sjudd (03 Sept 2013)

Can you provide the Glide.load()... line you're using to load the image?

Although I'm not entirely convinced of my argument, we throw an exception in that case because we can't really figure out what size you want the image to be with those values for width and height. 

If you want the image to be its original size, then you should use wrap_content for both width and height of the view. If you want a background or something, you can always make your view a child of a parent with a background.

If you want the image to match it's parent's width, then you should pick some reasonable height so we know how to crop the image rather than using wrap_content.

Essentially the problem with mixing wrap_content and match_parent for width/height is that we can't obey one without ignoring the other unless we change the image's aspect ratio. If you have any ideas on how we should handle this, I'd love to hear them.


## lalith-b (03 Sept 2013)

well It works if I use the Target() fetch the image and setImageBitmap() to the imageview. when doing .into(imageview) crashes.


## sjudd (03 Sept 2013)

Are you using ImageViewTarget or some other implementation? If you're not using ImageViewTarget, can you provide the implementation you're using to determine the view's dimensions? 

Also a stack trace of the original exception would help if you have it. I did mean to throw a particular exception in that case and I should at least make it consistent if its not being thrown in one case and it is in another. I'll look in to this later today.

Do you think it's appropriate to simply load the image in its original size whenever wrap_content is used for either or both the image's width and height?


## sjudd (03 Sept 2013)

This should be fixed here: https://github.com/bumptech/glide/commit/0815f830ea434137ccc89e35dbee02c4d598c090

Let me know if this is not the behavior you expect or you're still seeing exceptions. Thanks!


