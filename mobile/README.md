Hybrid Mobile App 
=====================

A hybrid mobile app that uses ionic/cordova to wrap the webclient into a mobile app for Android and iOS.

## Preparing to run mobile app

```bash
$ npm install -g ionic
$ npm install ncp
$ npm install replace
```

## Running mobile app

To build and run it, follow these steps:

1) First build the webclient (see ../README.md) 
(don't forget to compile the webclient module in IntelliJ to generate lib/kotlin.js)

2) cd into `mobile` and run:

```bash
$ ionic platform add android
$ ionic build android
$ ionic emulate android
```

Substitute "android" with "ios" if on a Mac.  The ios development toolchain is a lot easier to work with until you need to do anything custom to Android.

3) Run the resulting package: platforms/android/ant-build/MainActivity-debug.apk

## Troubleshooting for running mobile app

### If the screen is blank in the mobile app:
Right-click on mobile/www/index.html in IntelliJ and click on "Open in Browser".
Open the developer tools and look for errors in the Network tab or Console.

### If there is still a problem or any other problem:
cd into `mobile` and run:
```bash
$ ionic platform rm android
```

Then follow steps in the "Running mobile app" section above. 

## To deploy to ionic.io:

WARNING: The Ionic View app won't install for me on my phone, so this may not work for you either.  
It says that parental controls won't allow the app, but parental controls are off.

```bash
$ ionic io init
$ ionic add ionic-platform-web-client
$ ionic plugin add ionic-plugin-deploy
$ ionic upload --deploy
```

To view your app, install the Ionic View app: http://view.ionic.io/
