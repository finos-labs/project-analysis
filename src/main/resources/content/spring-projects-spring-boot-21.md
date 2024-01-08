#Make Spring Boot compatible with GVM

Owner: spring-projects

Repo: spring-boot

Labels: 

## marc0der (08 Aug 2013)

In order for Spring Boot to be added to GVM, the binary zip file needs to comply with certain conventions. All technology currently supported by GVM is delivered in a zip file named as follows: `${name}-${version}-${freeform}.zip`

For spring boot, it would be `spring-boot-0.5.0.M1-bin.zip`.

Internally, it would contain a folder named `${name}-${version}` as: `spring-boot-0.5.0.M1`.

If these minor changes could be made in the zip distro, I can proceed in adding it to gvm.


## dsyer (09 Aug 2013)

@marcoVermeulen It's already  pretty much in that form (although we use "spring-boot-cli" for the name of this component).  We have an internal debate going on about whether to use "bin" or "dist" as the classifier, but that seems irrelevant. What was the problem exactly?


## marc0der (09 Aug 2013)

I thought that the inconsistency between the archive name and install directory would throw it, but it turned out to work fine. Thanks in any case.


