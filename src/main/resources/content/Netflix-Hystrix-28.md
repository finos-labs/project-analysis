#Thread pools lost custom names in opensource refactoring

Owner: Netflix

Repo: Hystrix

Labels: bug 

## benjchristensen (30 Nov 2012)

The threadpools lost their custom names which makes debugging thread-dumps difficult.

Bring back proper naming ...


## benjchristensen (30 Nov 2012)

Fixed in https://github.com/Netflix/Hystrix/commit/ef71798fefdefc566c4d02c476d4d9053c4d8151


