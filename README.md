# About
Simple To-Do App.

# Modules                 

* common - code shared by backend, webclient, and/or mobile.
* backend - server-side web service usable by webclient and mobile.
* webclient - javascript web application
* mobile - hybrid mobile application using KotlinJS and Cordova to run on Android, iOS, Windows Phones, etc.

# To Personalize

Search for "ZZZ" and replace it with the information for your custom app.

Change the code to your hearts desire.
You can supply initial data in webclient/src/main/web/initial-data.json, 
whether manually or by copying in the output from the backup button in the app 
which exports the current state of the data. 

# How to Prepare to Build webclient

* Before building webclient, first build https://github.com/jean79/yested_fw using "mvn clean install".
* To be able to debug Kotlin in the browser, go to "File->Settings->Kotlin Compiler" and check the box "Generate source maps".
 
# How to Build webclient

First run "./gradlew build" in this top-level directory.

# How to Run webclient from IntelliJ (v. 2017.1)

In IntelliJ, right-click on webclient/src/main/web/index.html and "Open in Browser".  Voila!

Alternative: If that doesn't work, go to webclient/build/public/index.html and "Open in Browser".

# How to Run webclient tests from IntelliJ (v. 2017.1)

In IntelliJ, richt-click on webclient and "Rebuild".
Then, right-click on webclient/src/test/web/runner.html and "Open in Browser".

# How to compile changes from IntelliJ (v. 2017.1)

In IntelliJ, open the settings.gradle file in the top-level directory,
without selecting the "separate module per source set" option.

Set the module output paths to webclient/out/production/classes and webclient/out/test/classes.

# How to deploy to firebase hosting

Prerequisite: Replace ZZZAppIdZZZ in .firebaserc

In the webclient directory, run "firebase deploy".

# How to Build mobile

See mobile/README.md

# How to prepare to commit

Build the webclient (see above)
Run webclient tests and make sure they pass.
Build the mobile app and make sure it has basic functionality.

# Credit
Thank you to the following frameworks, tools, libraries, and languages:
* Yested - https://github.com/jean79/yested_fw
* Twitter Bootstrap - http://getbootstrap.com/
* JQuery - http://jquery.com/
* Moment.js - http://momentjs.com/
* NumeralJS - http://numeraljs.com/
* pickadate.js - http://amsul.ca/pickadate.js/ 
* Javascript - http://javascript.com/
* Kotlin - http://kotlinlang.org/
* Cordova - http://cordova.apache.org/
* Gradle - https://gradle.org/
* Phonegap Build - https://build.phonegap.com/
* QUnit test framework - http://qunitjs.com/
* Spek test framework from JetBrains - http://spekframework.org/
