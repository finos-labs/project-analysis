#项目用jetty启动，进入任务的列表总是报错

Owner: xuxueli

Repo: xxl-job

Labels: 

## alonecong (29 May 2016)

java.io.IOException: FULL head
    at org.mortbay.jetty.HttpParser.parseNext(HttpParser.java:276)
    at org.mortbay.jetty.HttpParser.parseAvailable(HttpParser.java:205)
    at org.mortbay.jetty.HttpConnection.handle(HttpConnection.java:380)
    at org.mortbay.io.nio.SelectChannelEndPoint.run(SelectChannelEndPoint.java:395)
    at org.mortbay.thread.BoundedThreadPool$PoolThread.run(BoundedThreadPool.java:450)

导致任务显示不出来。
<plugin>
                <groupId>org.mortbay.jetty</groupId>
                <artifactId>maven-jetty-plugin</artifactId>
                <version>6.1.7</version>
                <configuration>
                    <connectors>
                        <connector implementation="org.mortbay.jetty.nio.SelectChannelConnector">
                            <port>8090</port>
                            <maxIdleTime>30000</maxIdleTime>
                        </connector>
                    </connectors>
                </configuration>
            </plugin>


## xuxueli (29 May 2016)

你好，推荐你使用tomcat进行部署哈，问题可以到下面的群里反馈：
技术交流群(仅作技术交流)：367260654


## s332401890 (10 Aug 2016)

清除一下cookie试试看


