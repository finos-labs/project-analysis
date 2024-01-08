#Default Collapser scope to REQUEST if using Setter

Owner: Netflix

Repo: Hystrix

Labels: 

## benjchristensen (29 Nov 2012)

The default is set to REQUEST via the constructor, but it someone uses the Setter fluent interface it doesn't get defaulted.


## benjchristensen (29 Nov 2012)

This isn't causing anything to fail because there is a fallback in queue() to use REQUEST if scope is null, but that safety-net shouldn't be relied upon here.


## benjchristensen (29 Nov 2012)

Committed in https://github.com/Netflix/Hystrix/commit/38d85c5ac0bca09192720e31bdc6fa1559fdfe71


