A kickstarted application for web and mobile with a backend all using Kotlin.

# Modules                 

* common - code shared by backend, webclient, and/or mobile.
* backend - server-side web service usable by webclient and mobile.
* webclient - javascript web application
* mobile - hybrid mobile application using KotlinJS and Cordova to run on Android, iOS, Windows Phones, etc.

# How to Build

* The webclient module requires exporting some Kotlin libraries to Javascript.  
Run "./gradlew idea", open project in IntelliJ, at the prompt, 
click "Configure modules as Kotlin (JavaScript) modules", 
select the webclient module and "Use library from plugin", 
edit the webclient module settings and check the export boxes for KotlinJavaScript and Yested, 
and build the project in IntelliJ.
It will generate some files in out/production/webclient/lib

# How to Run from IntelliJ

In IntelliJ, right-click on webclient/src/main/web/index.html and "Open in Browser".  Voila!
