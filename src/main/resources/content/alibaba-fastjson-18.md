#deserialize does not work expected when @type is not the first element.

Owner: alibaba

Repo: fastjson

Labels: 

## bingoohuang (26 Dec 2012)

I have a class with two properties: firstName and lastName (will full getters and setters).

JSON.parse("{\"firstName\":\"first\",\"@type\":\"demo.JsonTest$JSONDemo\",\"lastName\":\"last\"}");

will ignore firstName left it null value.

Is this a bug or designed?


## wenshao (26 Dec 2012)

this is designed feature, not a bug. why you require "@type" is not the first element?


## bingoohuang (26 Dec 2012)

We developed a web app in which depends on your fastjson. But the client program guys use c++. we said @type is needed with some value, so they append @type as the last element of json string. And as a result, the server did response fail. So we checked it and found that @type should be the first element.
It is OK now to move @type to the first.
Tks.


