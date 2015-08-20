JazzyListView
-------------

JazzyListView is an extension of ListView designed to animate list item views as
they become visible. There are a number of pre-built, bundled effects that can be used
by setting the effect in code or an XML layout attribute. Also, it is possible to use a
custom effect by implementing a JazzyEffect.

This project was inspired by [stroll.js](http://lab.hakim.se/scroll-effects).

Usage
-----

##### ListView

1) Include a JazzyListView in your layout

```xml
<com.twotoasters.jazzylistview.JazzyListView
    android:id="@+id/list"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

2) Get a reference to your JazzyListView

```java
JazzyListView list = (JazzyListView) findViewById(R.id.list);
```

3) Set your effect

```java
list.setTransitionEffect(new SlideInEffect());
```

##### RecyclerView

1) Include a JazzyListView in your layout

```xml
<android.support.v7.widget.RecyclerView
    android:id="@+id/list"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

2) Get a reference to your RecyclerView

```java
RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
```

3) Set the JazzyScrollListener (you can set an additional scroll listener on the JazzyScrollListener)

```java
JazzyRecyclerViewScrollListener jazzyScrollListener = new JazzyRecyclerViewScrollListener();
recyclerView.setOnScrollListener(jazzyScrollListener);
```

NOTE: On new versions of the support library `setOnScrollListener()` has been deprecated for `addOnScrollListener()`

4) Set your effect

```java
jazzyScrollListener.setTransitionEffect(new SlideInEffect());
```

Sample App
----------

The sample app demonstrates all of the pre-built effects on a list, grid, and recyclerview. You can build it from source or install it from the [Play Store](https://play.google.com/store/apps/details?id=com.twotoasters.jazzylistview.sample).

Download
--------

```groovy
compile 'com.twotoasters.jazzylistview:library:1.2.1'
compile 'com.twotoasters.jazzylistview:library-recyclerview:1.2.1'
```

License
-------

    Copyright 2015 Two Toasters

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
