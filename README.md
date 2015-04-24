Rounded Activity - An activity decorator
==

A decorator that rounds corners of activities.  
This library is for Android projects.

<img src="https://raw.githubusercontent.com/wiki/gitusp/rounded-activity/images/screenshot_01.png" height="300">


Installation
--

Example for Gradle:
```groovy
repositories {
  maven { url 'https://github.com/gitusp/rounded-activity/raw/master/app/repository' }
}

compile 'ro.tsuku:roundedactivity:0.0.1@aar'
```


Usage
--

Simply call `RoundHelper.round` once for each activity.
```java
RoundHelper.round(activity);
```

