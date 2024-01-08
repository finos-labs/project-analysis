#ContentNegotiatingViewResolver favorPathExtension is not working

Owner: spring-projects

Repo: spring-framework

Labels: 

## bill2004158 (06 Feb 2013)

https://github.com/SpringSource/spring-framework/blob/3.2.x/spring-webmvc/src/main/java/org/springframework/web/servlet/view/ContentNegotiatingViewResolver.java#L138

public void setFavorPathExtension(boolean favorPathExtension) {
    this.cnManagerFactoryBean.setFavorParameter(favorPathExtension);
}

the method is setting "FavorPathExtension", but the impl calls set "FavorParameter".


## bill2004158 (06 Feb 2013)

fixed in: 
https://github.com/SpringSource/spring-framework/pull/229


