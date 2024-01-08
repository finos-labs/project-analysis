#建议增加FTPUtil的构造方法

Owner: dromara

Repo: hutool

Labels: question 

## Saharaqie (02 Jun 2017)

因为前段时间受病毒影响，一个外部FTP服务器从被动模式修改为主动模式（个人感觉没有用，FTP又用不到445），导致自己负责的项目完全死掉了，后来将项目中的连接模式设为了主动才修复。所以建议新增一个构造方法或者set方法设置连接模式为主动模式
ftpClient.enterLocalActiveMode();//连接模式设为主动模式
ftpClient.setActivePortRange(int,int);//客户端使用端口范围

## looly (02 Jun 2017)

感谢意见。由于FtpUtil的不完善，新版中并未加入。如果以后有相关工具会考虑。

