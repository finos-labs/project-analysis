#Failed to execute goal org.apache.maven.plugins:maven-enforcer-plugin:1.4.1:enforce

Owner: alibaba

Repo: nacos

Labels: 

## greatqn (27 Jul 2018)

mvn install

[ERROR] Failed to execute goal org.apache.maven.plugins:maven-enforcer-plugin:1.
4.1:enforce (enforce-ban-circular-dependencies) on project nacos-all: Execution
enforce-ban-circular-dependencies of goal org.apache.maven.plugins:maven-enforce
r-plugin:1.4.1:enforce failed: Plugin org.apache.maven.plugins:maven-enforcer-pl
ugin:1.4.1 or one of its dependencies could not be resolved: Failure to find org
.apache.maven.enforcer:enforcer-rules:jar:1.4.1 in http://repo.maven.apache.org/
maven2 was cached in the local repository, resolution will not be reattempted un
til the update interval of central has elapsed or updates are forced -> [Help 1]

## xuechaos (29 Jul 2018)

you can run ‘ mvn -Prelease-nacos -DskipTests clean install -U’ to build it. see it in BUILDING.md file，

(4) Build distribution packages

    Execute the following command in order to build the tar.gz packages and install JAR into local repository:

	#build nacos
    $ mvn -Prelease-nacos -DskipTests clean install -U

