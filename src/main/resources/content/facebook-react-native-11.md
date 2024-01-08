#Exception in render() propagates to Xcode

Owner: facebook

Repo: react-native

Labels: Resolution: Locked 

## gaearon (30 Jan 2015)

If I throw inside `componentDidMount`, I see RSOD.
If I throw in `render`, I'm being thrown into Xcode and I have to restart (not reload) the app.


## vjeux (30 Jan 2015)

This is bad, need to look into it. cc @frantic 


## robbiemccorkell (31 Jan 2015)

I found the same when I accidentally missed a # symbol on a hex colour in my styles. Looks like the exception raised when trying to convert the colour doesn't get caught.


## brentvatne (11 May 2015)

This is no longer an issue


