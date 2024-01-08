#JCVideoPlayer有一个这个样的问题“网络慢时，播放进度拖拉太快 只会停留在第一次拖进度哪里，要等加载完成后才能拖拉下一次。 #497”，你这个貌似木有。可以问一下主要是哪里引起的？

Owner: CarGuo

Repo: GSYVideoPlayer

Labels: 

## janecer (22 Nov 2016)



## CarGuo (22 Nov 2016)

主要是拖动进度条的时候做了处理，就是在停止拖动抬起手的时候做处理：
```
@Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /***
     * 拖动进度条
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (GSYVideoManager.instance().getMediaPlayer() != null && GSYVideoManager.instance().getMediaPlayer().isPlaying()) {
            int time = seekBar.getProgress() * getDuration() / 100;
            GSYVideoManager.instance().getMediaPlayer().seekTo(time);
        }

```
老版本的JC我记得是在 
```
 @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
```
做了处理，所以效果不大好。
还在有onTouch里面 
```
if(id == R.id.progress) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE://多加了一个这个
```



## Lsy1992 (22 Nov 2016)

作者很棒棒哦

## janecer (22 Nov 2016)

ok

