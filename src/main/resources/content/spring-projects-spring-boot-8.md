#Fresh `mvn clean install` failures

Owner: spring-projects

Repo: spring-boot

Labels: 

## rstoyanchev (02 Aug 2013)

In a fresh clone, `mvn clean install` leads to failures in the spring-boot module:

```
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[22,34] package org.apache.commons.logging does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[23,34] package org.apache.commons.logging does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[24,33] package org.springframework.beans does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[25,33] package org.springframework.beans does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[26,33] package org.springframework.beans does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[27,41] package org.springframework.beans.factory does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[28,41] package org.springframework.beans.factory does not exist
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[47,59] cannot find symbol
  symbol: class FactoryBean
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[47,8] cannot access org.springframework.beans.factory.Aware
  class file for org.springframework.beans.factory.Aware not found
[ERROR] /home/rossen/dev/sources/github/springsource/spring-boot/spring-boot/src/main/java/org/springframework/boot/bind/PropertiesConfigurationFactory.java:[50,23] cannot find symbol
  symbol:   class Log
  location: class org.springframework.boot.bind.PropertiesConfigurationFactory<T>
```


## dsyer (02 Aug 2013)

Thanks Rossen. Our CI servers are telling us the same thing. Not sure why it stopped working, but a fix is in the pipeline.


## philwebb (03 Aug 2013)

The CI is now fixed and it builds locally for me. Could you let me know if this is still an issue.


