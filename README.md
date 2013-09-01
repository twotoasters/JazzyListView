JazzyListView
-------------

JazzyListView is an extension of ListView designed to animate list item views as
they become visible. There are a number of pre-built, bundled effects that can be used
by setting the effect in code or an XML layout attribute. Also, it is possible to use a
custom effect by implementing a JazzyEffect.

This project was inspired by stroll.js at <http://lab.hakim.se/scroll-effects/>.

Sample App
----------

The sample app demonstrates all of the pre-built effects on a list and a grid. You can build it from source or install it from the Play Store: <https://play.google.com/store/apps/details?id=com.twotoasters.jazzylistview.sample>

Download
--------

Grab the .apklib from Maven central

```xml
<dependency>
    <groupId>com.twotoasters.jazzylistview</groupId>
    <artifactId>library</artifactId>
    <version>1.0.0</version>
</dependency>
```

or build the .aar yourself with gradle and install into your local Maven repository.

```
gradle clean install
```

License
-------

    Copyright 2013 Two Toasters

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
