#Refactor PhotosQueue.clean()

Owner: nostra13

Repo: Android-Universal-Image-Loader

Labels: 

## nostra13 (08 Dec 2011)

PhotosQueue#clean(): гораздо красивее и более читабельно писать такие шутки через iterator:
Iterator<ImageView> it = photosToLoad.iterator();
while ( it.hasNext() ) {
   final ImageView imageView = it.next();
   if ( imageView == imageForRemove ) {
      it.remove();
   }
}

В вашем случае, по-моему, возможно падение с ConcurrentModificationException


