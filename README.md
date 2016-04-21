# GnomeTown

Libraries used in this project:

- Realm , for storing data in app. 
- Volley , to call web service and loading images. I like to use this one because handles cancel request easy and allows to handle image loading without using third party libraries
- ButterKnife, to bind views in activity.
- Gson , to parse json data from service.
- Disklrucache, library from JakeWharton for caching images.
- RecyclerView and CardView for design features.

Some considerations:

Search item in Toolbar searchs info from realm database. Realm does not support case sensitive search.
