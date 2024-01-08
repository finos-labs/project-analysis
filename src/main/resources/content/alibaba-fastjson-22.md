#Split data binding facility from serializer/deserializer

Owner: alibaba

Repo: fastjson

Labels: 

## ikozar (06 Feb 2013)

I propose to split data binding facility from serializer/deserializer. It allows to use project code for various goals, such as data transformation between object and map or objects with different structure (like https://github.com/DozerMapper/dozer)


## wenshao (06 Feb 2013)

you can do this:

```
  A a = ....;
  String json = JSON.toJSONString(a);

  B b = JSON.parseObject(json, B.class);
```


## ikozar (06 Feb 2013)

Of course, this way is possible for conversion of objects, but:
1. This method is slowly
2. I can't control the binding, such as mapping a field 'fieldA' of class 'A' to field 'fieldB' of class 'B'

I'd like to have possibility like this code (for shortly in groovy-style)
class A {String fieldA}
class B {C fieldB}
class C {String fieldC}
ParserConfig.getGlobalInstance().addMapping(
     new ClassMap(from: A.class, to: B.class, fields: [['fieldA', 'fieldB.fieldC'']])
FastBinding.convert(new A(fieldA: '123'), new B())

It implements not very hard if there is a factory of data binding


## ikozar (06 Feb 2013)

P.S.: I want to develop a universal dao find method, which receives result data from DB with JPA Entity and 
converts theirs to a web data service response type.
This facility is possible when using DozerMapper, by mapping web data type to JPA Entity.
But, if I like to receive result as Tuple - transformation impossible.
DozerMapper is a very closed system.
I hope to use fastjson for this purpose, because it's very flexible system.


