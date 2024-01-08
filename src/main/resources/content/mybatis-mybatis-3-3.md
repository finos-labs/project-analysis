#"less than" in Select annotations

Owner: mybatis

Repo: mybatis-3

Labels: bug 

## emacarron (25 Feb 2013)

Reported by mgbckr, Today (6 hours ago)
What version of the MyBatis are you using?
MyBatis 3.2.0, MyBatis-Spring 1.2.0

Please describe the problem.  Unit tests are best!
When I use the "less than" sign "<" in a Select annotations, I get an exception is thrown when initializing the application context. If I use "&lt;" it works. In previous MyBatis versions using "<" was working. I think it is counter-intuitive to be forced to use XML entities in Java code if not explicitly documented. 

What is the expected output? What do you see instead?
No exception :)

Can you provide stack trace, logs, error messages that are displayed?
org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'mapper' defined in file [/media/data/Projects/EveryAware/workspaces/development/mybatis-invalid-annotation2/target/classes/de/fstyle/test/mybatis/invalid/annotation/mapper/Mapper.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Could not find value method on SQL annotation.  Cause: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 38; The content of elements must consist of well-formed character data or markup.
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1486)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:524)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:461)
    at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:295)
    at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:223)
    at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
    at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:194)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:608)
    at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:932)
    at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:479)
    at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:139)
    at org.springframework.context.support.ClassPathXmlApplicationContext.<init>(ClassPathXmlApplicationContext.java:83)
    at de.fstyle.test.mybatis.invalid.annotation.InvalidAnnotationTest.test(InvalidAnnotationTest.java:17)
    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
    at java.lang.reflect.Method.invoke(Method.java:616)
    at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:47)
    at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
    at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:44)
    at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
    at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:271)
    at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:70)
    at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:50)
    at org.junit.runners.ParentRunner$3.run(ParentRunner.java:238)
    at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63)
    at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236)
    at org.junit.runners.ParentRunner.access$000(ParentRunner.java:53)
    at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229)
    at org.junit.runners.ParentRunner.run(ParentRunner.java:309)
    at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:50)
    at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
    at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Could not find value method on SQL annotation.  Cause: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 38; The content of elements must consist of well-formed character data or markup.
    at org.mybatis.spring.mapper.MapperFactoryBean.checkDaoConfig(MapperFactoryBean.java:98)
    at org.springframework.dao.support.DaoSupport.afterPropertiesSet(DaoSupport.java:44)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1545)
    at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1483)
    ... 35 more
Caused by: org.apache.ibatis.builder.BuilderException: Could not find value method on SQL annotation.  Cause: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 38; The content of elements must consist of well-formed character data or markup.
    at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.getSqlSourceFromAnnotations(MapperAnnotationBuilder.java:399)
    at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.parseStatement(MapperAnnotationBuilder.java:241)
    at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.parse(MapperAnnotationBuilder.java:120)
    at org.apache.ibatis.binding.MapperRegistry.addMapper(MapperRegistry.java:62)
    at org.apache.ibatis.session.Configuration.addMapper(Configuration.java:628)
    at org.mybatis.spring.mapper.MapperFactoryBean.checkDaoConfig(MapperFactoryBean.java:95)
    ... 38 more
Caused by: org.apache.ibatis.builder.BuilderException: Error creating document instance.  Cause: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 38; The content of elements must consist of well-formed character data or markup.
    at org.apache.ibatis.parsing.XPathParser.createDocument(XPathParser.java:253)
    at org.apache.ibatis.parsing.XPathParser.<init>(XPathParser.java:112)
    at org.apache.ibatis.scripting.xmltags.XMLScriptBuilder.<init>(XMLScriptBuilder.java:44)
    at org.apache.ibatis.scripting.xmltags.XMLLanguageDriver.createSqlSource(XMLLanguageDriver.java:39)
    at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.buildSqlSourceFromStrings(MapperAnnotationBuilder.java:409)
    at org.apache.ibatis.builder.annotation.MapperAnnotationBuilder.getSqlSourceFromAnnotations(MapperAnnotationBuilder.java:392)
    ... 43 more
Caused by: org.xml.sax.SAXParseException; lineNumber: 1; columnNumber: 38; The content of elements must consist of well-formed character data or markup.
    at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.createSAXParseException(ErrorHandlerWrapper.java:198)
    at com.sun.org.apache.xerces.internal.util.ErrorHandlerWrapper.fatalError(ErrorHandlerWrapper.java:177)
    at com.sun.org.apache.xerces.internal.impl.XMLErrorReporter.reportError(XMLErrorReporter.java:391)
    at com.sun.org.apache.xerces.internal.impl.XMLScanner.reportFatalError(XMLScanner.java:1404)
    at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.startOfMarkup(XMLDocumentFragmentScannerImpl.java:2583)
    at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl$FragmentContentDriver.next(XMLDocumentFragmentScannerImpl.java:2680)
    at com.sun.org.apache.xerces.internal.impl.XMLDocumentScannerImpl.next(XMLDocumentScannerImpl.java:625)
    at com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.scanDocument(XMLDocumentFragmentScannerImpl.java:488)
    at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:819)
    at com.sun.org.apache.xerces.internal.parsers.XML11Configuration.parse(XML11Configuration.java:748)
    at com.sun.org.apache.xerces.internal.parsers.XMLParser.parse(XMLParser.java:123)
    at com.sun.org.apache.xerces.internal.parsers.DOMParser.parse(DOMParser.java:239)
    at com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderImpl.parse(DocumentBuilderImpl.java:288)
    at org.apache.ibatis.parsing.XPathParser.createDocument(XPathParser.java:251)
    ... 48 more

Please provide any additional information below.
See: https://code.google.com/p/fstyle-test/source/browse/#git%2Fbug%2Fmybatis-invalid-annotation


## simonetripodi (25 Feb 2013)

which commit closes that issue? it would be better using one of github aliases in commit messages such as the ones below

fixes #xxx
fixed #xxx
fix #xxx
closes #xxx
close #xxx
closed #xxx


## emacarron (25 Feb 2013)

Hi Simo!

I will commit the fix soon (hopefully in some minutes). Github recognizes
the comment and adds it automatically to the issue.


## simonetripodi (25 Feb 2013)

yes, that is what I wrote in my message - but I saw the issue closed before the commit, so I asked :)


