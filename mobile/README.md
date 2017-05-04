Hybrid Mobile App 
=====================

A hybrid mobile app that uses cordova to wrap the webclient into a mobile app for Android and iOS.

## Preparing to run mobile app

```bash
$ sudo npm install -g cordova
```

## Running mobile app

To build and run it, follow these steps:

1) First build the webclient (see ../README.md) 
(don't forget to compile the webclient module in IntelliJ to generate lib/kotlin.js)

2) cd into `mobile` and run:

```bash
$ cordova platform add android --save
$ cordova build android
```                      
Install platforms/android/build/outputs/apk/android-debug.apk onto your Android device. 

Optional:
```bash
$ cordova emulate android
```

Substitute "android" with "ios" if on a Mac.  The ios development toolchain is a lot easier to work with until you need to do anything custom to Android.

3) Run the resulting package: platforms/android/ant-build/MainActivity-debug.apk

## Generating multiple sizes of the app icon

Set up steps (only the first time)

```
$ sudo npm install -g cordova-gen-icon
$ sudo apt-get install imagemagick
```

Steps (after changing the app icon) to run before "cordova build android"
```
$ ../gradlew genIcon
$ cp platforms/ios/*/Resources/icons/* res/icons/
```


## Troubleshooting for running mobile app

### If the screen is blank in the mobile app:
Right-click on mobile/www/index.html in IntelliJ and click on "Open in Browser".
Open the developer tools and look for errors in the Network tab or Console.

### If there is still a problem or any other problem:
cd into `mobile` and run:
```bash
$ cordova platform rm android
```

Then follow steps in the "Running mobile app" section above. 

# Screen sizes
To get an idea of what it will look like on a 12.9in iPad, 
go to chrome and open up the developer tools,
Zoom to 25% and resize the app window to 408x544 (or 544x408).  

Zoom to 33%  538x718 or (718x538)
