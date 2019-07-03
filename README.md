# About
Simple To-Do App.

# Modules                 

* this directory - multiplatform project for browser and JVM backend.
* mobile - hybrid mobile application using KotlinJS and Cordova to run on Android, iOS, Windows Phones, etc.

# To Personalize

Search for "ZZZ" and replace it with the information for your custom app.

Change the code to your hearts desire.
You can supply initial data in src/jsMain/web/initial-data.json,
whether manually or by copying in the output from the backup button in the app 
which exports the current state of the data. 

# How to Prepare to Build

* Before building, first build yested_fw:
```bash
cd ..
git clone https://github.com/jean79/yested_fw
cd yested_fw
git reset --hard origin/improvements 
mvn clean install
```
* To be able to debug Kotlin in the browser, go to "File->Settings->Kotlin Compiler" and check the box "Generate source maps".
 
# How to Build

First run "./gradlew build" in this top-level directory.

# How to Run from IntelliJ (v. 2017.1)

In IntelliJ, right-click on src/jsMain/web/index.html and "Open in Browser".  Voila!
Note: This way works with Source Maps for debugging. 

Alternative: If that doesn't work, go to build/public/index.html and "Open in Browser".
Note: This one works with Source Maps for debugging. 

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
* Yested - https://github.com/jean79/yested_fw
* Twitter Bootstrap - http://getbootstrap.com/
* JQuery - http://jquery.com/
* Moment.js - http://momentjs.com/
* NumeralJS - http://numeraljs.com/
* pickadate.js - http://amsul.ca/pickadate.js/ 
* Javascript - http://javascript.com/
* Cordova - http://cordova.apache.org/
* Gradle - https://gradle.org/
* Phonegap Build - https://build.phonegap.com/
* QUnit test framework - http://qunitjs.com/
* Spek test framework from JetBrains - http://spekframework.org/
