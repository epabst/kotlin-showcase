# About
Simple To-Do App.

# Modules                 

* this directory - multiplatform project for browser and JVM backend.
* mobile - hybrid mobile application using KotlinJS and Cordova to run on Android, iOS, Windows Phones, etc.

# To Personalize

Search for "ZZZ" and replace it with the information for your custom app.

Change the code to your hearts desire.
You can supply initial data in src/jsMain/resources/initial-data.json,
whether manually or by copying in the output from the backup button in the app 
which exports the current state of the data. 

# How to build to Run

Run "./gradlew jsBrowserDevelopmentWebpack"

Then open build/distributions/index.html in a browser.

Alternative: If that doesn't work, go to build/public/index.html and "Open in Browser".

# How to do a full Build

Run "./gradlew build" in this top-level directory.

# How to deploy to firebase hosting

Prerequisite: Replace ZZZAppIdZZZ in .firebaserc

Install firebase tools: "npm install -g firebase-tools"

In the top-level directory, run "firebase deploy".

# How to Build mobile

See mobile/README.md

# How to prepare to commit

Build (see above).
Build the mobile app and make sure it has basic functionality.

# Credit
Thank you to the following frameworks, tools, libraries, and languages:
* Kotlin - http://kotlinlang.org/
* React - https://reactjs.org/
* Twitter Bootstrap - http://getbootstrap.com/
* pickadate.js - http://amsul.ca/pickadate.js/ 
* Javascript - http://javascript.com/
* Cordova - http://cordova.apache.org/
* Gradle - https://gradle.org/
* Phonegap Build - https://build.phonegap.com/
