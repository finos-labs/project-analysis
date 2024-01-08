#该Json无法解析

Owner: alibaba

Repo: fastjson

Labels: 

## wuyexiong (30 Oct 2012)

{
 "track": [
   {
     "name": "Android",
     "color": "#A6BC40",
     "abstract": "Learn about developing mobile handset and tablet apps for Android."
   },
   {
     "name": "Chrome",
     "color": "#46B0E2",
     "abstract": "Build for the modern web with the Chrome platform."
   },
   {
     "name": "Cloud Platform",
     "color": "#2076BC",
     "abstract": "Learn about Google's cloud offerings for developers."
   },
   {
     "name": "Code Labs",
     "color": "#E4388F",
     "abstract": "Get your hands dirty in longer, classroom-style sessions. Bring your laptop. Write code."
   },
   {
     "name": "Commerce",
     "color": "#4CAA47",
     "abstract": "Learn how to use Google Commerce products to improve monetization on the web, in app, and in store."
   },
   {
     "name": "Entrepreneurship",
     "color": "#97B0DA",
     "abstract": "Talks on topics related to startups, venture capital, and entrepreneurship."
   },
   {
     "name": "Google APIs",
     "color": "#00773F",
     "abstract": "Learn about Google's various developer platforms."
   },
   {
     "name": "Google Drive",
     "color": "#F5851F",
     "abstract": "Learn to develop for Google Drive and Google Apps Script."
   },
   {
     "name": "Google Maps",
     "color": "#4CAA47",
     "abstract": "Leverage the power of Google's geospatial technology in your apps."
   },
   {
     "name": "Google TV",
     "color": "#37505C",
     "abstract": "Build apps for the big screen. Learn about developing for Google TV."
   },
   {
     "name": "Google+",
     "color": "#DC4E30",
     "abstract": "Learn about developing on the Google+ platform."
   },
   {
     "name": "Tech Talk",
     "color": "#A1609D",
     "abstract": "Tech talks on subjects such as computer science problems, programming languages, and more."
   },
   {
     "name": "YouTube",
     "color": "#E72C2E",
     "abstract": "Learn about developing for YouTube."
   }
 ]
}


## wuyexiong (30 Oct 2012)

package entity;

public class Track {
    public String name;
    public String color;
    public String _abstract;
}

public class Tracks {
    Track[] track;

```
public void setTrack(Track[] track) {
    this.track = track;
}

public Track[] getTrack() {
    return track;
}

@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    for(Track track : getTrack())
    {
        sb.append(",name : ").append(track.name);
        sb.append(",color : ").append(track.color);
        sb.append(",abstract : ").append(track._abstract).append("\n");
    }
    return sb.toString();
}
```

}

这样的实体的话, "abstract"节点无法解析出东西,麻烦看看


## wenshao (07 Nov 2012)

我做过了测试，是因为你的Track对象字段命名问题。
这样修改吧：

```
public class Track {
    public String name;
    public String color;
    @JSONField(name="abstract")
    public String _abstract;
}
```


## wuyexiong (08 Nov 2012)

明白。多谢了。


