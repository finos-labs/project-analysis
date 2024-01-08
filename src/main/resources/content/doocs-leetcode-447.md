#Is it possible to provide a PDF format edition?

Owner: doocs

Repo: leetcode

Labels: 

## asyougo (11 Jun 2021)

Or could you give a guide for generating PDF for myself ? I think it is also useful for many people.

## A-Rivi (19 Jun 2021)

metoo

## maolonglong (10 Sept 2021)

docsify-pdf-converter 不太成熟,我们似乎能通过少量的配置用 gitbook 来生成 pdf,虽然它已经不更新了

[Generating eBooks and PDFs](https://github.com/GitbookIO/gitbook/blob/master/docs/ebook.md)

![](https://user-images.githubusercontent.com/50797868/132800995-f08da4dd-dd9c-4dee-a7be-eaaf62d138fd.png)

## yanglbme (10 Sept 2021)

nice👍，方便的话帮忙配置一下

## Kurdo1998 (28 Jan 2022)

Jack you please send me your 

## KimYangOfCat (02 May 2022)

也许可以在 release 中提供生成好的 pdf

## maolonglong (02 May 2022)

~上次尝试了一下，可能量太大 gitbook 跑不出来，docsify-pdf-converter 不能处理路径中有空格的文件（部分题目的目录名称），去掉空格后，勉强生成的 pdf 因为 tabs 插件，还只能显示一种语言~

现在的主要问题是路径中的 `' '`, `'('`, `')'`

## yanglbme (03 May 2022)

看看要不要统一把空格替换为 `-`，并且去掉 `(` `)` @MaoLongLong 

不过这个改动会很大，基本上是把整个项目目录结构都换了

## maolonglong (03 May 2022)

> 看看要不要统一把空格替换为 `-`，并且去掉 `(` `)` @MaoLongLong
> 
> 不过这个改动会很大，基本上是把整个项目目录结构都换了

确实，我再想想，因为可能有的人收藏了题解的链接，相当于原先的链接全失效了

