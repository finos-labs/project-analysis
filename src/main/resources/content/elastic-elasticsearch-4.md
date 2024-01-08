#Any plans to add geospatial search?

Owner: elastic

Repo: elasticsearch

Labels: 

## simonw (11 Feb 2010)

It would be incredibly useful to be able to index documents with latitude/longitude positions and run searches that return results ordered by distance from a specific latitude/longitude point.


## kimchy (11 Feb 2010)

Yes, very much. There is a Lucene spatial module that I need to look at. There are other solutions running around. When I support that, I want that to be very closely integrated in terms that you will have a type: location (or something similar).

Not sure it will make it into 0.5, but its certainly on the short list for 0.6.0.


## simonw (11 Feb 2010)

Thanks - it's a very exciting project, thanks for releasing it!


## kimchy (12 Feb 2010)

I will close this for now, I will add an issue that says "Spatial Support" later on 


