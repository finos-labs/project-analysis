#Data Mapper Pattern

Owner: iluwatar

Repo: java-design-patterns

Labels: epic: pattern 

## jesusvazquez (21 Mar 2015)

Hi, I'm a new user to github and I would like to help to this project. I've been researching about a few patterns and the work you've done and I think it's so cool.

In my college I've developed an application which comunicates with a MySQL Database and I had to build a pattern to make that communication work. I'm talking about the Abstract Mapper.

I have an example here: https://github.com/jesusvazquez/java_examples/tree/master/AdvancedDatabaseSystems/src/modelo/DBMapper

I'm wondering if it would be a good idea to add this pattern to your project.

Best Regards.


## iluwatar (22 Mar 2015)

@jesusvazquez Thank you for the interest in this project.

For the Abstract Mapper pattern you are proposing let's discuss about it on a bit higher level. Can you describe what is the intent of the pattern and when should we use it the same way as we have for the other patterns [here](https://github.com/iluwatar/java-design-patterns/blob/master/README.md)?


## jesusvazquez (22 Mar 2015)

Yes, 

This pattern allows you to map your database tables without using any framework, like Hibernate for example. If your application is small and you want to understand how the connections to the database work, this pattern helps you a lot because it's just an abstract class and a few Java programming concepts.

If you want to map a table you just have to fill 4 or 5 methods and then SELECT, INSERT, UPDATE or DELETE will work perfectly.

I wish my english is good enough for you tu understand what I'm trying to explain ;)


## iluwatar (22 Mar 2015)

Ok, I see this is a persistence solution. Like a mini framework that could be compared to [these](http://java-source.net/open-source/persistence).

What makes a design pattern? A design pattern is a tried and used way to solve a specific problem in an elegant way. I don't know if Abstract Mapper is a brand new first version or is it widely used somewhere? How is this problem solved in the other persistence frameworks and what makes Abstract Mapper better than what they've invented?


## jesusvazquez (22 Mar 2015)

I believe the most common name is Data Mapper Pattern, here you can find some info: http://en.wikipedia.org/wiki/Data_mapper_pattern 
There is another pattern who looks pretty similar named Data Access Object http://en.wikipedia.org/wiki/Data_access_object

Both looks pretty similar because the concept is the same. Between the application model and your database you implement a new layer to manage the whole database thing. DAO uses an interface but I prefer the abstract class choice because for me it's more simple and that's why I said Abstract Mapper. I should change that to Data Mapper because it's best known.
Here you have two UML examples that I find helpful:
DAO UML: https://cdn.tutsplus.com/net/uploads/legacy/1122_zend2/images/dao.jpg
Mapper UML: http://dl.cbsimg.net/i/tr/cms/contentPics/u00320031211dlx02_c.gif

Trying to simplify I would say that DAO and Mapper are both an aproximation of what big frameworks like Hibernate perform. Of course you can start using hibernate from the beginning without never implementing this patterns but I see them as a good academic example so this is the main reason to add them to this repository.


## iluwatar (23 Mar 2015)

Ok, now that description hits the right spot. I see the correspondence with Mr. Fowler's Data Mapper pattern. A simple example of that would be very welcome. Also, an example of DAO pattern is needed.

Do you have an idea how to implement the database connection? Use some kind of mock or in memory database maybe?


## jesusvazquez (23 Mar 2015)

Yep that's my doubt, we can create the main sql and give some instructions...

Any ideas for the easiest solution ?


## iluwatar (24 Mar 2015)

Fowler's pattern description: http://martinfowler.com/eaaCatalog/dataMapper.html

One implementation I found: http://richard.jp.leguen.ca/tutoring/soen343-f2010/tutorials/implementing-data-mapper/

I would probably try the implementation with JDBC and H2 or HyperSQL in-memory database. For those you can just add Maven dependencies and you're good to go.


## jesusvazquez (24 Mar 2015)

Those examples contain hardcoded querys inside the entity mappers. The example I gave you https://github.com/jesusvazquez/java_examples/tree/master/AdvancedDatabaseSystems/src/modelo/DBMapper fix this in the class Abstract Mapper. Would you like to add this or shall we keep the pattern without any modification. 

I have studied both and I find very useful the Abstract Mapper implementation.


## iluwatar (24 Mar 2015)

The implementation link I gave was just for your information. You don't need to use anything from that. But I do find important that we use Data Mapper as the name of the pattern.


## jesusvazquez (26 Mar 2015)

Okey I agree with that name. I'll give it a try as soon as possible. Thanks!


## iluwatar (24 Jun 2015)

@jesusvazquez Do you have any news regarding the implementation of Data Mapper pattern? :)


## jesusvazquez (24 Jun 2015)

Hi,

Not really, I'm sorry but I just finished my Bachelor. I'll try to perform it this summer :)


## npathai (16 Aug 2015)

This issue is related with #79 
Both are persistence solutions. Data mapper is considered alternative to Active record. 
Some links that discuss the difference 
- http://russellscottwalker.blogspot.in/2013/10/active-record-vs-data-mapper.html


## inbravo (28 Mar 2016)

I own this issue now; please assign .... 
- Fowler is father of DMP http://martinfowler.com/eaaCatalog/dataMapper.html
- Referring http://richard.jp.leguen.ca/tutoring/soen343-f2010/tutorials/implementing-data-mapper/


## npathai (28 Mar 2016)

@jesusvazquez Have you worked on this issue or I should assign it to @inbravo ?


## npathai (28 Mar 2016)

Meanwhile @inbravo I think you can proceed with the implementation.


## iluwatar (04 Apr 2016)

@inbravo please make a pull request and we'll review.


## inbravo (06 Apr 2016)

Pull request [#417] is ready for review....
Build report: https://travis-ci.org/iluwatar/java-design-patterns/builds/121084977


