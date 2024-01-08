#为什么我 fork 会少了一些文件呢？

Owner: Snailclimb

Repo: JavaGuide

Labels: question 

## guanguans (07 Jul 2018)

看完操作系统的了，顺便看了下博客，为什么我 fork 会少了呢？

## Snailclimb (08 Jul 2018)

因为在你fork之后，我更新了这个仓库，而你没有同步。也就是说你fork的是之前一段时间的仓库，而现在这个仓库又更新了。你可以重新fork或者同步。

## jiayangchen (25 Sept 2018)

@guanguans 你可以使用 `git remote add upstream` 添加这个仓库作为上游仓库，然后就可以同步更新了

