#CityServiceImpl#findCityById() bug

Owner: JeffLi1993

Repo: springboot-learning-example

Labels: 

## microheart (05 May 2017)

`CityServiceImpl#findCityById()`
如果城市不存在，则会抛出NullPointerException。
```
 // 从 DB 中获取城市信息
City city = cityDao.findById(id);

// 插入缓存
operations.set(key, city, 10, TimeUnit.SECONDS);
LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString());
```

建议进行null判断，然后再插入缓存。


## JeffLi1993 (11 May 2017)

赞~ 

## JeffLi1993 (08 Apr 2018)

done

