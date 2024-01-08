#为什么我项目里的MP4小视频链接在Demo上播放不了。。请楼主指导下。万分感谢！

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## dalelufei (25 Nov 2016)

我在我的工程里用楼主的播放器，播放这个链接： http://www.ettwit.com:8080/file/download/?u_id=41&type=app&id=553
 会抛出：HttpProxyCacheServer error    com.danikula.videocache.ProxyCacheException: Error closing socket output stream这个异常

## dalelufei (25 Nov 2016)

这个url的视频是mp4格式的。 是数据流信息。是HTTP模式的下载的。

## dalelufei (25 Nov 2016)

我在公司内网，拍小视频，上传，播放，能正常播放的了。 可是在外网，拍小视频，然后用播放器播放的时候，就会报这个错误。 

## CarGuo (25 Nov 2016)

要不就是视频的编码格式不支持

## CarGuo (25 Nov 2016)

E/IJKMEDIA: stream 0, offset 0x30: partial file
11-25 15:00:43.786 27860-29664/com.example.gsyvideoplayer W/IJKMEDIA: Could not find codec parameters for stream 0 (Video: h264 (avc1 / 0x31637661), none, 480x360, 227 kb/s): unspecified pixel format
                                                                      Consider increasing the value for the 'analyzeduration' and 'probesize' options

## CarGuo (25 Nov 2016)

这个视频文件是经过特殊处理过的吗？

## dalelufei (25 Nov 2016)

有进行压缩过。是我自己录制的小视频

## dalelufei (25 Nov 2016)

https://github.com/mabeijianxi/small-video-record   我是用这个项目进行录制的视频。

## CarGuo (25 Nov 2016)

https://github.com/Bilibili/ijkplayer/issues/1141  
https://github.com/Bilibili/ijkplayer/issues/2060
对应找到了ijkplayer的issuse，应该是在视频的头部那里没找到对应的信息。我测试了这个项目录制下来的原视频本地是可以播放的。

## dalelufei (25 Nov 2016)

是的。。我在内网上进行测试。都能跑的通。也可以边缓存，边播放。 可是在外网环境下录制下来的视频，进行播放就会抛出异常。我们的外网，目前是用的阿里云。

## CarGuo (25 Nov 2016)

确实很奇怪，如果把视频拉倒本地就可以播放，但是如果是在url的话就播放不了，表现出的是在解码的时候找不到格式···不过同样的视频，我传到我们这边的七牛服务器就可以播放····

```
Could not find codec parameters for stream 0 (Video: h264 (avc1 / 0x31637661), none, 480x360, 227 kb/s):    unspecified pixel format 。

Consider increasing the value for the 'analyzeduration' and 'probesize' options。

```

http://ocgk7i2aj.bkt.clouddn.com/1e9175e4-5f70-419a-81c4-ef1d92ce286f



## CarGuo (25 Nov 2016)

个人觉得是服务器端的问题。

## dalelufei (25 Nov 2016)

我也很费解。。郁闷了老半天，。可是在本地自己搭的那个服务器上又没问题。

## CarGuo (25 Nov 2016)

我把视频传到七牛上也没问题，问题应该是出在了它身上！-> http://www.ettwit.com:8080/file/download/?u_id=41&type=app&id=553

## dalelufei (25 Nov 2016)

那现在的解决方案，只能是先把视频下载到本地，再进行播放了。  在demo里，该怎么取消缓存。播放本地文件。

## CarGuo (25 Nov 2016)

 videoPlayer.setUp(url, false, "");   
 boolean cacheWithPlay字段传false

## dalelufei (25 Nov 2016)

好的。谢谢您！！！！

## CarGuo (25 Nov 2016)

如果可以请关闭issue，(＾－＾)V

## dalelufei (25 Nov 2016)

Ok 我可以加下您QQ吗，以后有问题可以一起交流下。。@.@

## CarGuo (25 Nov 2016)

你用简书吗？我简信发给你。

## dalelufei (25 Nov 2016)

好的。。我简书联系你。。我把issue关了。

