#libDoraemonKit.a

Owner: didi

Repo: DoKit

Labels: 

## S209 (30 Oct 2018)

Undefined symbols for architecture arm64:
  "_OBJC_CLASS_$_CNContactStore", referenced from:
      objc-class-ref in libDoraemonKit.a(DoraemonAppInfoUtil.o)
ld: symbol(s) not found for architecture arm64
clang: error: linker command failed with exit code 1 (use -v to see invocation)

 我 pod 'DoraemonKit/Core', '~> 1.0.2' 版本

## yixiangboy (31 Oct 2018)

加上 添加上Contacts.framework 试试。

