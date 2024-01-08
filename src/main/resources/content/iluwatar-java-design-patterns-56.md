#Better View/Navigation ?

Owner: iluwatar

Repo: java-design-patterns

Labels: type: enhancement epic: web site 

## markusmo3 (28 May 2015)

Heya there,
first of all, i love this resource and use it quite often. the only thing slightly annoying is the layout. Would be really awesome to view this resource in bootstrap with categories or something like that.

If something like that is done "hardcoded" the project will probably loose on contributions, so a converter of sorts should be used.

One last point, i found this Site that looks pretty good, but i dont know if it would be suitable. I also have no idea if it even works, because here everything is one single markdown document, while those at the site are multiple.
http://phpmyadmin.readthedocs.org/en/QA_4_4/
(topic is not important)

Just an idea C:

~Markus


## iluwatar (18 Jul 2015)

Hi @noobxgockel 

Sorry for the delayed answer. I've been travelling for the last few days.

It would be great to improve the user experience by adding a project web site using github pages. My personal priority is currently on developing the content of the project (improve the existing and add more patterns).

Would you be interested to take this issue and start developing the site for the project from scratch? I can provide you push access and will of course help you wherever you need.


## markusmo3 (20 Jul 2015)

No problem, i've been busy too c:

yeah i can take this issue on, and having push access would be pretty nice for that. But dont expect design wonders from me ;)


## iluwatar (20 Jul 2015)

@noobxgockel I've added you as a collaborator to the project. You should be able to push to the repository.

Let me know if there's anything you need to advance this issue.


## markusmo3 (21 Jul 2015)

Nice C: today im still a bit busy, but tomorrow i'll start for real.

Just some things i thought i'd mention beforehand so you can maybe comment on those.
- I'd put everything pattern related into the master branch and everything website related into the gh-pages branch. To link those 2 together i'd use the git module feature and maybe change the travis.yml so the website is automagically updated each build.
- For this to work properly i'd take on the task of splitting the main README.md up. The description of the pattern would be put inside of the folder named something like index.md or pattern.md (because some folders allready contain a README file) The main README.md could be left in the root directory but i'd suggest to replace it with a simple one with links to the page...
- I havent looked around much for a theme for the website but im thinking about using this one: http://jekyllthemes.org/themes/cool-concise-high-end/ Seems quite fitting for such a site

Any comments, suggestions, critic on that ?


## iluwatar (21 Jul 2015)

Thanks for your thoughts. I think it sounds great in general. Here's some comments:
- Branches master & gh-pages are ok (currently there is also cpp branch where we may get C++ ports of the patterns at some point)
- I haven't used git modules before, but for what I've just read it is going to suit us fine
- Automatic updating of the web site is a great feature. Semiautomatic one command publishing might also be good enough for a start at least.
- You're welcome to split and edit the README.md and I think that the plan to put the pattern description into the folder is good.
- The theme you mentioned is ok for me. Is it easy/possible to change it later on?


## markusmo3 (22 Jul 2015)

Sounds good C:

It would also be possible to incorperate the cpp patterns into the website with little effort. But maybe the repository name is silightly confusing then.
And to the theme changing. Well it would be possible and it might be quite easy because it should be possible to just swap out the .css and maybe some .js files. But im not too sure about that. If an issue like that comes up you can assign it to me and i'll look into it :)


## iluwatar (22 Jul 2015)

The cpp branch is currently on hold so let's just ignore it at the moment. Better not design too much ahead, we can change things when the need arises.


## npathai (04 Aug 2015)

@iluwatar Github pages will suffice. IMO we should dedicate a page for applications of patterns.


## npathai (04 Aug 2015)

:+1: 


## bernardosulzbach (05 Aug 2015)

I went through Jekyll tutorials to set up [my website](https://mafagafogigante.github.io/) and would be willing to help if needed.

BTW, where is the branch?

I think that a new repository is also not bad idea, it's up to you. It provides some nice decoupling but may break some Travis magic.

Theme-changing shouldn't be too hard. Jekyll uses static HTML includes and [Liquid](http://liquidmarkup.org/) to generate the pages. Besides that all there is to change is CSS and JS (if used).


## iluwatar (05 Aug 2015)

ping @noobxgockel 


## markusmo3 (11 Aug 2015)

Yeah, sorry about that, i started but never really finished ^^ Kinda busy atm, but if you want i can commit the stuff i have currently and someone else can take it. Site works but is kinda flawed because i dont intend to use patterns as jekyll posts but rather static pages (those who know jekyll know what im talking about) ^^


## bernardosulzbach (11 Aug 2015)

I think that a script that rendered a markdown page for every DP is not so hard to craft.


## iluwatar (11 Aug 2015)

@noobxgockel it would be great if you could commit the current work.


## bernardosulzbach (11 Aug 2015)

Yeah, I also want it. I may even make some progress on it.


## markusmo3 (12 Aug 2015)

@mafagafogigante Yeah its not hard to just display the patterns. But i want to use the theme i posted above and include a tag/category search functionality. Maybe even some overview pages etc... But i will try to make as much progress today and commit it. So others can help C:
Sorry again that i was away for that long...


## markusmo3 (12 Aug 2015)

Just as i update:
Worked on it today and made quite a bit of progress, but its too late now to organize a partial commit. I'll ramp up a commit tomorow, maybe even with some more of site done C:


## bernardosulzbach (12 Aug 2015)

Good job. Waiting to see it, may put some effort into this in the weekend.


## markusmo3 (13 Aug 2015)

Here is the first batch!
Yes its not optimal right now, but i got to get some sleep too, will write TODO's and sorts tomorrow...

PS: the site works mostly (except some css) its just the links that dont, they somehow cut out the "/java-design-patterns/" part...


## iluwatar (14 Aug 2015)

This is great! Now we have something to show. I will report new issues on my findings.


