#TracingEnhanceProcessor使用更好的增强逻辑，保证实例内方法的相互调用，可以被追踪到

Owner: apache

Repo: skywalking

Labels: 

## wu-sheng (15 Dec 2015)

TracingEnhanceProcessor的原有增强方式，为引用一个原有的对象。
建议使用新的增强方式，直接生成全新的动态类，实例内方法的相互调用，可以被追踪到


## wu-sheng (21 Dec 2015)

通过super调用上级方法，而非方法拷贝。在类实例内调用能被追踪的同时，保证方法可调试。
PS：静态方法依然无法提供追踪。


## ascrutae (22 Dec 2015)

目前生成的新类，采用新生成的类，并且继承该类的接口和类，新类中包含所有的super的方法定义，注解。并且新生成的方法都是采用super调用上级方法。


