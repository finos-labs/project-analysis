#Field softMap in Cache visibility

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (08 Dec 2011)

Зачем поле softMap объявлено protected? Даже если нужен доступ у наследников (в данном случае я думаю не нужен) лучше написать модификатор private и добавить protected метод getCache


