#有些mp4文件无法播放

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## LinForStudy (02 Dec 2016)

Chrome浏览器不能放，safarl可以放，我自己手机播放也是黑屏，ios手机可以播放这个地址，
这是地址：http://panzi.hqdemo.cn/Uploads/Microblog/attach/201612/584138b384974.mp4
有时间看看能不能处理一下呗
![image](https://cloud.githubusercontent.com/assets/18901958/20828718/cd053fc6-b8b3-11e6-8188-b2005e9c0288.png)


## CarGuo (02 Dec 2016)

IJKPlayer对这种视频的播放不支持，视频的编码不是正常的编码状态，如果你用系统的应该就会支持。第二张图片是你的连接的，我直接在ijkplyaer的demo里面跑也是没画面的。Pixel format没有的话是没有画面的，这是FFMPEG在解析上的问题。

![image](https://cloud.githubusercontent.com/assets/10770362/20829605/b424e840-b8b7-11e6-92a4-7d9ea3d9c56d.png)

![image](https://cloud.githubusercontent.com/assets/10770362/20829624/c9d4ad56-b8b7-11e6-92d7-a68e367423e4.png)



## LinForStudy (02 Dec 2016)

那我上传视频的时候应该传什么样的参数的，才能够识别，求大神支持
Pixel format 应该怎么设置

## CarGuo (02 Dec 2016)

至少pixel format要是有的，类似第一张图片的yuv420p这种。你是用什么拍的？系统拍的应该是H264编码，ffmpeg的应该是有–pix_fmt yuv420p 。

## LinForStudy (02 Dec 2016)

recorder = new FFmpegFrameRecorder(new File(videoFile), outputWidth, outputHeight, 1);
        recorder.setFormat(recorderParameters.getVideoOutputFormat());
        recorder.setPixelFormat(PixelFormat.RGBA_8888);
FFmpegFrameRecorde javacv的库， 然后的话这个setPixelFormat 参数怎么设置

## CarGuo (02 Dec 2016)

recorder.setFormat(recorderParameters.getVideoOutputFormat()); //H264
recorder.setPixelFormat(PixelFormat.RGBA_8888);//这个应该是YUV的
这个库的链接是？我没用过这个，你可以试试。

## LinForStudy (02 Dec 2016)

https://github.com/bytedeco/javacv
 我用的是这个，大神留个联系方式呗,然后有没有更好的录视频的库。。

## CarGuo (02 Dec 2016)

你先试试看有没有  PixelFormat.PIX_FMT_YUV420P 哟~我QQ 359369982 先加我吧，我晚上有时间看看哈~~

