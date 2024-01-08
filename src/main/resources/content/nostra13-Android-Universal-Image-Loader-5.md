#FileUtils.copyStream() exception handling

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (08 Dec 2011)

Я не знаю как у вас используется FileUtils#copyStream(), но, что-то мне подсказывает, что скрывать IoException не правильно. Достаточно задать вопрос: будет ли приложение работать дальше нормально, если потоки не откопировались?


## nostra13 (09 Dec 2011)

Reorganize exception handling. Cached but probably corrupted file will be deleted if exception was occured.

**Commit cfd2ce6213ccff8f273de39dad3cda4b0b05160d.**


