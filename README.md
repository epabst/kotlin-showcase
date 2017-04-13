# About
Simple To-Do App.

# Modules                 

* common - code shared by backend, webclient, and/or mobile.
* backend - server-side web service usable by webclient and mobile.
* webclient - javascript web application
* mobile - hybrid mobile application using KotlinJS and Cordova to run on Android, iOS, Windows Phones, etc.

# To Personalize

Search for "ZZZ" and replace it with the information for your custom app.

# How to Prepare to Build webclient

* Before building webclient, first build https://github.com/jean79/yested_fw using "mvn clean install".
* The webclient_main module requires exporting some Kotlin libraries to Javascript.
In IntelliJ, open the settings.gradle file in the top-level directory,
with the "separate module per source set" option.  
Note: After this, the module output paths should be something like webclient/build/classes/main and webclient/build/classes/test.
* To be able to debug Kotlin in the browser, go to "File->Settings->Kotlin Compiler" and check the box "Generate source maps".
 
# How to Build webclient

First run "./gradlew build" in this top-level directory.
This will copy the common module's source code into the webclient/build/gen/kotlin directory. 

Then compile the webclient_main module in IntelliJ (e.g. right-click on the webclient/src/main directory and click "Build Module 'webclient_main'").
It will generate some .js files in webclient/build/classes/main.

# How to Run webclient from IntelliJ

In IntelliJ, right-click on webclient/src/main/web/index.html and "Open in Browser".  Voila!

# How to Run webclient tests from IntelliJ

Compile the webclient_test module in IntelliJ (e.g. right-click on the webclient_test module and click "Compile").
It will generate some files in webclient/build/classes/test.

In IntelliJ, right-click on webclient/src/test/web/runner.html and "Open in Browser".

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
