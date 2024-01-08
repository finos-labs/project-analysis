#Potential NPE in Cache.get()

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (08 Dec 2011)

Потенциальный NPE в методе get(): не сихронизованы методы clear() и get(). Более подробно: cointaisKey() возвращает true, вызывается метод clear(), get() от Map возвращает null.

 Решение без synchronized: внутри метода Cache#get() использовать сразу Map#get() и проверять результат на null. В таком случае нужно поле Map нужно делать private и запрещать любые доступы к нему извне


## nostra13 (09 Dec 2011)

**Commit a43849557a06e09741b17b047d8ee8a1844b63c8**


