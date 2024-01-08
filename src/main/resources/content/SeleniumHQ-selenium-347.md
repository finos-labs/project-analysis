#Provide a way to change root path for tmp files.

Owner: SeleniumHQ

Repo: selenium

Labels: C-py I-papercut 

## JulienPalard (18 Mar 2015)

Selenium typically writes Firefox profile files in /tmp/ as temporary directory (/tmp/tmpXXXXXX).

This is OK as long as everything goes well and `driver.quit()` is called, cleaning the directories.

In my case, selenium is running under Xvfb, managed by a `supervisord`, and it looks like in some cases, my files are not cleaned. It would be cool to store all this tmp files elsewhere, so I can easily `rm -fr` them.

My supervisord config, FYI looks like:

```
[program:tests-example-com]
user=nobody
command=/home/.../test.py -v run-with-browser http://www.example.com/what-to-test/
autostart=true
autorestart=true
stopasgroup=true
killasgroup=true
stopsignal=QUIT
```

My python code, FYI looks like a context manager to be sure to not forgot driver.quit.:

```
[...]
class WhateverTester:
[...]
    def __enter__(self):
        return self

    def __exit__(self, type, value, traceback):
        self.driver.quit()
[...]
    with WhateverTester(endpoint) as tester:
        tester.run()
[...]
```


## JulienPalard (18 Mar 2015)

As ruby correctly looks at the environment variable `TMPDIR`, this may only be documented, no line of code and no parameter may be needed.


## AutomatedTester (18 Mar 2015)

so just to check, we need to update our documentation to say that TMPDIR
will point things in the right direction?

On Wed, Mar 18, 2015 at 12:52 PM, Julien notifications@github.com wrote:

> As ruby correctly looks at the environment variable TMPDIR, this may only
> be documented, no line of code and no parameter may be needed.
> 
> —
> Reply to this email directly or view it on GitHub
> https://github.com/SeleniumHQ/selenium/issues/347#issuecomment-82955511.


## JulienPalard (19 Mar 2015)

Yep I think that's a good idea to document the fact that you're storing profiles in /tmp/ by default, trashing the /tmp/ on some people like in http://stackoverflow.com/questions/13638228/change-the-default-folder-where-temporary-profile-is-created-by-firefox/29122692#29122692

and for non unix experts it's a bit hard to know that TMPDIR even exist.


## andreastt (20 Mar 2015)

We don't create temporary directories in `/tmp` on all OSes.  On Mac OS the default is `/var/folders`, and it feels wrong of us to document what the Python standard library is doing as it's well-documented there.

We could ask people to refer to the tempfile documentation however (https://docs.python.org/2/library/tempfile.html).


## JulienPalard (23 Mar 2015)

It's Ruby, BTW.


## JulienPalard (23 Mar 2015)

And I'm clearly not sure that your users want to check the doc of the languages you use (http://ruby-doc.org/stdlib-1.9.3/libdoc/tempfile/rdoc/Tempfile.html) to find configuration variable names.


